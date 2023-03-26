/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodoArbol;

import ER.ExpresionesRegulares;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author ruben
 */
public class Arbol {

    private Nodo raiz = null;
    private String nombre;
    private Vector<String> expresion;
    private int cantidad;
    private String[][] tablaSiguiente;
    private Vector<FilaEstados> tablaTransicion;
    private int cantidadSimbolos;
    private Vector<String> encabezadoSimbolos;

    public Arbol(Vector<String> expresion, String nombre, int cantidad) {
        this.expresion = expresion;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tablaSiguiente = new String[cantidad][2];
        this.tablaTransicion = new Vector<FilaEstados>();
        this.encabezadoSimbolos = new Vector<String>();
        this.cantidadSimbolos = cantidad;
    }

    public void crearArbol() {
        Stack<Nodo> pila = new Stack<Nodo>();
        Nodo hoja = null;
        Nodo hoja2 = null;
        Nodo resultado = null;
        boolean anulable = false;
        String[] ultimos = null;
        for (int i = expresion.size() - 1; i >= 0; i--) {
            switch (expresion.elementAt(i)) {
                case "*":
                    hoja = pila.pop();
                    ultimos = hoja.getUltimos().split(",");
                    for (String ultimo1 : ultimos) {
                        if (tablaSiguiente[Integer.parseInt(ultimo1) - 1][1] == null) {
                            tablaSiguiente[Integer.parseInt(ultimo1) - 1][1] = hoja.getPrimeros();
                        } else {
                            tablaSiguiente[Integer.parseInt(ultimo1) - 1][1] += "," + hoja.getPrimeros();
                        }
                    }
                    resultado = new Nodo(0, "*", true, hoja.getPrimeros(), hoja.getUltimos(), hoja, null);
                    pila.add(resultado);
                    break;
                case "?":
                    hoja = pila.pop();
                    resultado = new Nodo(0, "?", true, hoja.getPrimeros(), hoja.getUltimos(), hoja, null);
                    pila.add(resultado);
                    break;
                case "|":
                    hoja = pila.pop();
                    hoja2 = pila.pop();
                    anulable = false;
                    if (hoja.isAnulable() || hoja2.isAnulable()) {
                        anulable = true;
                    }
                    resultado = new Nodo(0, "|", anulable, hoja.getPrimeros() + "," + hoja2.getPrimeros(), hoja.getUltimos() + "," + hoja2.getUltimos(), hoja, hoja2);
                    pila.add(resultado);
                    break;
                case "+":
                    hoja = pila.pop();
                    anulable = false;
                    if (hoja.isAnulable()) {
                        anulable = true;
                    }
                    ultimos = hoja.getUltimos().split(",");
                    for (String ultimo1 : ultimos) {
                        if (tablaSiguiente[Integer.parseInt(ultimo1) - 1][1] == null) {
                            tablaSiguiente[Integer.parseInt(ultimo1) - 1][1] = hoja.getPrimeros();
                        } else {
                            tablaSiguiente[Integer.parseInt(ultimo1) - 1][1] += "," + hoja.getPrimeros();
                        }
                    }
                    resultado = new Nodo(0, "+", anulable, hoja.getPrimeros(), hoja.getUltimos(), hoja, null);
                    pila.add(resultado);
                    break;
                case ".":
                    hoja = pila.pop();
                    hoja2 = pila.pop();
                    anulable = false;
                    if (hoja.isAnulable() && hoja2.isAnulable()) {
                        anulable = true;
                    }
                    String primera = hoja.getPrimeros();
                    if (hoja.isAnulable()) {
                        primera = hoja.getPrimeros() + "," + hoja2.getPrimeros();
                    }
                    String ultimo = hoja2.getUltimos();
                    if (hoja2.isAnulable()) {
                        ultimo = hoja.getUltimos() + "," + hoja2.getUltimos();
                    }
                    ultimos = hoja.getUltimos().split(",");
                    for (String ultimo1 : ultimos) {
                        if (tablaSiguiente[Integer.parseInt(ultimo1) - 1][1] == null) {
                            tablaSiguiente[Integer.parseInt(ultimo1) - 1][1] = hoja2.getPrimeros();
                        } else {
                            tablaSiguiente[Integer.parseInt(ultimo1) - 1][1] += "," + hoja2.getPrimeros();
                        }
                    }
                    resultado = new Nodo(0, ".", anulable, primera, ultimo, hoja, hoja2);
                    pila.add(resultado);
                    break;
                default:
                    Nodo nuevo = new Nodo(cantidad, expresion.elementAt(i), false, String.valueOf(cantidad), String.valueOf(cantidad), null, null);
                    tablaSiguiente[cantidad - 1][0] = expresion.elementAt(i);
                    if (!encabezadoSimbolos.contains(expresion.elementAt(i))) {
                        encabezadoSimbolos.add(expresion.elementAt(i));
                    }
                    raiz = nuevo;
                    cantidad--;
                    pila.add(nuevo);
            }
        }
        raiz = pila.pop();
        if (!pila.empty()) {
            System.out.println("Error");
        }
    }

    public String postOrden(Nodo nodo) {
        String cuerpo = "";
        if (nodo != null) {
            String tipo = nodo.getTipo().startsWith("\"") ? nodo.getTipo().substring(1, nodo.getTipo().length() - 1) : nodo.getTipo();
            String ide = nodo.getIdentificador() == 0 ? "" : "\\nIdentificador: " + String.valueOf(nodo.getIdentificador());
            cuerpo += nodo.hashCode() + " [label=\"" + tipo
                    + "\\nAnulable: " + String.valueOf(nodo.isAnulable())
                    + ide
                    + "\\nPrimeros: " + nodo.getPrimeros()
                    + "\\nUltimos: " + nodo.getUltimos() + "\"]; \n";
            if (nodo.getIzquierda() != null) {
                cuerpo += nodo.hashCode() + " -- " + nodo.getIzquierda().hashCode() + ";\n";
                cuerpo += postOrden(nodo.getIzquierda());
            }
            if (nodo.getDerecha() != null) {
                cuerpo += nodo.hashCode() + " -- " + nodo.getDerecha().hashCode() + ";\n";
                cuerpo += postOrden(nodo.getDerecha());
            }
        }
        return cuerpo;
    }

    public void mostrarArbol() {
        String cabeza = "graph " + nombre + "{\n"
                + "    fontname=\"Helvetica,Arial,sans-serif\"\n"
                + "    node [fontname=\"Helvetica,Arial,sans-serif\"]\n"
                + "    edge [fontname=\"Helvetica,Arial,sans-serif\"]\n";
        cabeza += postOrden(raiz);
        cabeza += "}";
        File directorio = new File("ARBOLES_202111835");
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                System.out.println("Error al crear directorio");
                return;
            }
        }
        crearArchivo(nombre + "_arbol", cabeza, "ARBOLES_202111835\\");
    }

    public void mostrarTablaSiguientes() {
        String cuerpo = "digraph tabla {\n"
                + "    node [shape=plaintext]\n"
                + "\n"
                + "    tbl [\n"
                + "        label=<\n"
                + "            <table border=\"0\" cellborder=\"1\" cellspacing=\"0\">"
                + "<tr><td bgcolor=\"/rdylgn11/5:/rdylgn11/5\"><b>Hoja</b></td>"
                + "<td bgcolor=\"/rdylgn11/5:/rdylgn11/5\"><b>ID</b></td>"
                + "<td bgcolor=\"/rdylgn11/5:/rdylgn11/5\"><b>Siguiente</b></td>"
                + "</tr>";
        for (int i = 0; i < tablaSiguiente.length; i++) {
            String simbolo = tablaSiguiente[i][0].startsWith("\"") ? tablaSiguiente[i][0].substring(1, tablaSiguiente[i][0].length() - 1) : tablaSiguiente[i][0];
            cuerpo += "<tr><td>" + simbolo + "</td><td>" + String.valueOf(i + 1) + "</td><td>";
            if (tablaSiguiente[i][1] != null) {
                cuerpo += tablaSiguiente[i][1] + "</td></tr>";
            } else {
                cuerpo += "----</td></tr>";
            }
        }
        cuerpo += "   </table>\n"
                + "        >\n"
                + "    ];\n"
                + "}";
        File directorio = new File("SIGUIENTES_202111835");
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                System.out.println("Error al crear directorio");
                return;
            }
        }
        crearArchivo(nombre + "_follow", cuerpo, "SIGUIENTES_202111835\\");
    }

    public void CrearTablaTransicion() { //metodo con codigo spaguetti
        FilaEstados inicial = new FilaEstados(raiz.getPrimeros(), encabezadoSimbolos.size());
        inicial.setIdentificador("S(" + raiz.getPrimeros() + ")");//poner un buen identificador
        Stack<String> restantes = new Stack<String>();
        String[] siguientes = inicial.getEstado().split(",");
        for (String siguiente : siguientes) {
            int indice = encabezadoSimbolos.indexOf(tablaSiguiente[Integer.parseInt(siguiente) - 1][0]);
            if (encabezadoSimbolos.elementAt(indice) == "#") {
                inicial.setAceptacion(true);
                continue;
            }
            if (indice != -1) {
                if (inicial.getSimbolos()[indice][0] == null) {
                    inicial.getSimbolos()[indice][0] = tablaSiguiente[Integer.parseInt(siguiente) - 1][1];
                    inicial.getSimbolos()[indice][1] = "S(" + tablaSiguiente[Integer.parseInt(siguiente) - 1][1] + ")";//poner un buen identificador
                } else {
                    inicial.getSimbolos()[indice][0] += "," + tablaSiguiente[Integer.parseInt(siguiente) - 1][1];
                    inicial.getSimbolos()[indice][1] += inicial.getSimbolos()[indice][1].substring(0, inicial.getSimbolos()[indice][1].length() - 1) + "," + tablaSiguiente[Integer.parseInt(siguiente) - 1][1] + ")";//poner un buen identificador
                }
            }
        }
        tablaTransicion.add(inicial);
        for (int i = 0; i < inicial.getSimbolos().length; i++) {
            boolean existe = false;
            for (int j = 0; j < tablaTransicion.size(); j++) {
                if (tablaTransicion.elementAt(j).getEstado().equals(inicial.getSimbolos()[i][0])) {
                    existe = true;
                    break;
                }
            }
            if (!restantes.contains(inicial.getSimbolos()[i][0]) && inicial.getSimbolos()[i][0] != null && existe == false) {
                restantes.add(inicial.getSimbolos()[i][0]);
            }
        }

        while (!restantes.empty()) {
            String estado = restantes.pop();
            //System.out.println("------" + estado);
            FilaEstados nuevo = new FilaEstados(estado, encabezadoSimbolos.size());
            siguientes = nuevo.getEstado().split(",");
            nuevo.setIdentificador("S(" + estado + ")");//poner un buen identificador
            for (String siguiente : siguientes) {
                int indice = encabezadoSimbolos.indexOf(tablaSiguiente[Integer.parseInt(siguiente) - 1][0]);
                if (encabezadoSimbolos.elementAt(indice) == "#") {
                    nuevo.setAceptacion(true);
                    continue;
                }
                if (indice != -1) {
                    if (nuevo.getSimbolos()[indice][0] == null) {
                        nuevo.getSimbolos()[indice][0] = tablaSiguiente[Integer.parseInt(siguiente) - 1][1];
                        nuevo.getSimbolos()[indice][1] = "S(" + tablaSiguiente[Integer.parseInt(siguiente) - 1][1] + ")";//poner un buen identificador
                    } else {
                        nuevo.getSimbolos()[indice][0] += "," + tablaSiguiente[Integer.parseInt(siguiente) - 1][1];
                        nuevo.getSimbolos()[indice][1] += nuevo.getSimbolos()[indice][1].substring(0, nuevo.getSimbolos()[indice][1].length() - 1) + "," + tablaSiguiente[Integer.parseInt(siguiente) - 1][1] + ")";//poner un buen identificador
                    }
                }
            }
            tablaTransicion.add(nuevo);
            for (int i = 0; i < nuevo.getSimbolos().length; i++) {
                boolean existe = false;
                for (int j = 0; j < tablaTransicion.size(); j++) {
                    if (tablaTransicion.elementAt(j).getEstado().equals(nuevo.getSimbolos()[i][0])) {
                        existe = true;
                        //System.out.println("Entro!!!" + tablaTransicion.elementAt(j).getEstado());
                        break;
                    }
                }
                if (!restantes.contains(nuevo.getSimbolos()[i][0]) && nuevo.getSimbolos()[i][0] != null && existe == false) {
                    //System.out.println("entro2222222--"+nuevo.getSimbolos()[i]);
                    restantes.add(nuevo.getSimbolos()[i][0]);
                }
            }
        }

    }

    public void mostrarTablaTransicion() {
        String cuerpo = "digraph tabla {\n"
                + "    node [shape=plaintext]\n"
                + "\n"
                + "    tbl [\n"
                + "        label=<\n"
                + "            <table border=\"0\" cellborder=\"1\" cellspacing=\"0\">"
                + "<tr><td bgcolor=\"/rdylgn11/5:/rdylgn11/5\"><b>Estado</b></td>";
        for (int i = 1; i < encabezadoSimbolos.size(); i++) {
            String simbolo = encabezadoSimbolos.elementAt(i).startsWith("\"") ? encabezadoSimbolos.elementAt(i).substring(1, encabezadoSimbolos.elementAt(i).length() - 1) : encabezadoSimbolos.elementAt(i);
            cuerpo += "<td bgcolor=\"/rdylgn11/5:/rdylgn11/5\"><b>" + simbolo + "</b></td>";
        }
        cuerpo += "</tr>";
        for (int i = 0; i < tablaTransicion.size(); i++) {
            if (tablaTransicion.elementAt(i).isAceptacion()) {
                cuerpo += "<tr><td><b>" + tablaTransicion.elementAt(i).getIdentificador() + "*</b></td>";
            } else {
                cuerpo += "<tr><td>" + tablaTransicion.elementAt(i).getIdentificador() + "</td>";
            }
            for (int j = 1; j < tablaTransicion.elementAt(i).getSimbolos().length; j++) {
                if (tablaTransicion.elementAt(i).getSimbolos()[j][1] != null) {
                    cuerpo += "<td>" + tablaTransicion.elementAt(i).getSimbolos()[j][1] + "</td>";
                } else {
                    cuerpo += "<td>--------</td>";
                }
            }
            cuerpo += "</tr>";
        }
        cuerpo += "   </table>\n"
                + "        >\n"
                + "    ];\n"
                + "}";
        File directorio = new File("TRANSICIONES_202111835");
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                System.out.println("Error al crear directorio");
                return;
            }
        }
        crearArchivo(nombre + "_transicion", cuerpo, "TRANSICIONES_202111835\\");
    }

    public void mostrarAutomata() {
        String cuerpo = "digraph " + nombre + " {\n"
                + "	fontname=\"Helvetica,Arial,sans-serif\"\n"
                + "	node [fontname=\"Helvetica,Arial,sans-serif\"]\n"
                + "	edge [fontname=\"Helvetica,Arial,sans-serif\"]\n"
                + "	rankdir=LR;\n";
        String anulable = "node [shape = doublecircle]; ";
        String conexiones = "\nnode [shape = circle];\n"
                + "    inicio[label=\"\" shape=\"rectangule\" color=\"white\"];\n"
                + "    inicio -> \"" + tablaTransicion.elementAt(0).getIdentificador() + "\" [label = \"Inicio\"];";
        for (int i = 0; i < tablaTransicion.size(); i++) {
            if (tablaTransicion.elementAt(i).isAceptacion()) {
                anulable += "\"" + tablaTransicion.elementAt(i).getIdentificador() + "\"";
            }
            for (int j = 1; j < tablaTransicion.elementAt(i).getSimbolos().length; j++) {

                if (tablaTransicion.elementAt(i).getSimbolos()[j][1] != null) {
                    conexiones += "\"" + tablaTransicion.elementAt(i).getIdentificador() + "\" -> ";
                    String simbolo = encabezadoSimbolos.elementAt(j).startsWith("\"") ? encabezadoSimbolos.elementAt(j).substring(1, encabezadoSimbolos.elementAt(j).length() - 1) : encabezadoSimbolos.elementAt(j);
                    conexiones += "\"" + tablaTransicion.elementAt(i).getSimbolos()[j][1] + "\" [label = \"" + simbolo + "\"];\n";
                }
            }
        }
        cuerpo += anulable;
        cuerpo += conexiones;
        cuerpo += "}";
        File directorio = new File("AFD_202111835");
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                System.out.println("Error al crear directorio");
                return;
            }
        }
        crearArchivo(nombre + "_AFD", cuerpo, "AFD_202111835\\");
    }

    /*  
        CrearArchivo(nombre,cuerpo,carpeta)
        nombre: nombre del archivo de entrada
        cuerpo: todo lo que lleva el archivo dentro
        carpeta: nombre de la carpeta que contendra el archivo
     */
    public void crearArchivo(String nombre, String cuerpo, String carpeta) {
        FileWriter fichero = null;
        try {
            File directorio = new File("textosDot");
            if (!directorio.exists()) {
                if (directorio.mkdirs()) {
                    System.out.println("Directorio creado");
                } else {
                    System.out.println("Error al crear directorio");
                    return;
                }
            }
            fichero = new FileWriter("textosDot\\" + nombre + ".dot");
            PrintWriter pw = null;
            pw = new PrintWriter(fichero);
            pw.println(cuerpo);
            pw.close();
            try {
                ProcessBuilder proceso;
                proceso = new ProcessBuilder("dot", "-Tjpg", "-o", carpeta + nombre + "_hecho.jpg", "textosDot\\" + nombre + ".dot");
                proceso.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    //Validar cadena
    public boolean validarCadenas(String entrada) {
        FilaEstados estadoActual = tablaTransicion.elementAt(0);
        for (int i = 0; i < entrada.length(); i++) {
            char caracter = entrada.charAt(i);
            String lexema = String.valueOf(caracter);
            if (caracter == '\\') {
                if (i + 1 < entrada.length()) {
                    if (entrada.charAt(i + 1) == '\'' || entrada.charAt(i + 1) == '\"' || entrada.charAt(i + 1) == 'n') {
                        lexema += entrada.charAt(i + 1);
                        i++;
                    }
                }
            }
            //System.out.println(lexema);
            int posicion = -1;
            for (int j = 1; j < encabezadoSimbolos.size(); j++) {//busco la posición del simbolo
                if (estadoActual.getSimbolos()[j][0] == null) {
                    continue;
                }
                if (encabezadoSimbolos.elementAt(j).startsWith("\"")) {//si es un caracter de un string
                    if (encabezadoSimbolos.elementAt(j).substring(1, encabezadoSimbolos.elementAt(j).length() - 1).equals(lexema)) {
                        posicion = j;
                        break;
                    }
                } else if (encabezadoSimbolos.elementAt(j).equals(lexema)) {// si es un caracter de escape
                    posicion = j;
                    break;
                } else {//si es un caracter de un conjunto
                    for (int k = 0; k < ExpresionesRegulares.getConjuntos().size(); k++) {
                        if (ExpresionesRegulares.getConjuntos().elementAt(k).getNombre().equals(encabezadoSimbolos.elementAt(j))) {
                            for (int l = 0; l < ExpresionesRegulares.getConjuntos().elementAt(k).getValor().size(); l++) {
                                if (ExpresionesRegulares.getConjuntos().elementAt(k).getValor().elementAt(l).equals(lexema)) {

                                    posicion = j;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
            }
            //System.out.println("****" + String.valueOf(posicion));
            if (posicion == -1) {
                return false;//el simbolo no existe
            }

            for (int j = 0; j < tablaTransicion.size(); j++) {
                if (tablaTransicion.elementAt(j).getEstado().equals(estadoActual.getSimbolos()[posicion][0])) {
                    estadoActual = tablaTransicion.elementAt(j);
                    break;
                }
            }
        }
        if (estadoActual.isAceptacion()) {
            return true;
        }
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public void mostrarAFND() {
        String cuerpo = raiz.getIzquierda().graphviz();
        String cabeza = "digraph " + nombre + " {\n"
                + "	fontname=\"Helvetica,Arial,sans-serif\"\n"
                + "	node [fontname=\"Helvetica,Arial,sans-serif\"]\n"
                + "	edge [fontname=\"Helvetica,Arial,sans-serif\"]\n"
                + "	rankdir=LR;\n"
                + "node [shape = doublecircle];" + "S" + ExpresionesRegulares.conteoAFND + ";\n"
                + "    node [shape = circle];\n"
                + "    inicio[label=\"\" shape=\"rectangule\" color=\"white\"];\n"
                + "    inicio -> S0 [label = \"Inicio\"];";
        cabeza += cuerpo;
        cabeza += "}";
        File directorio = new File("AFND_202111835");
        if (!directorio.exists()) {
            if (!directorio.mkdirs()) {
                System.out.println("Error al crear directorio");
                return;
            }
        }
        crearArchivo(nombre + "_afnd", cabeza, "AFND_202111835\\");
    }

}
