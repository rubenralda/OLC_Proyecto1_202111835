/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodoArbol;

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
            if (!directorio.mkdirs()){
                System.out.println("Error al crear directorio");
                return;
            }
        }
        crearArchivo(nombre + "_arbol" , cabeza, "ARBOLES_202111835\\");
    }

    public void mostrarTablaSiguientes() {
        String cuerpo = "digraph tabla {\n"
                + "    node [shape=plaintext]\n"
                + "\n"
                + "    tbl [\n"
                + "        label=<\n"
                + "            <table border=\"1\" cellborder=\"0\" cellspacing=\"0\">"
                + "<tr><td><b>Hoja</b></td><td><b>ID</b></td><td><b>Siguiente</b></td></tr>";
        for (int i = 0; i < tablaSiguiente.length; i++) {
            cuerpo += "<tr><td>" + tablaSiguiente[i][0] + "</td><td>" + String.valueOf(i + 1) + "</td><td>" + tablaSiguiente[i][1] + "</td></tr>";
        }
        cuerpo += "   </table>\n"
                + "        >\n"
                + "    ];\n"
                + "}";
        File directorio = new File("SIGUIENTES_202111835");
         if (!directorio.exists()) {
            if (!directorio.mkdirs()){
                System.out.println("Error al crear directorio");
                return;
            }
        }
        crearArchivo(nombre + "_follow", cuerpo, "SIGUIENTES_202111835\\");
    }

    public void CrearTablaTransicion() { //Funcion con codigo spaguetti
        FilaEstados inicial = new FilaEstados(raiz.getPrimeros(), encabezadoSimbolos.size());
        Stack<String> restantes = new Stack<String>();
        String[] siguientes = inicial.getEstado().split(",");
        for (String siguiente : siguientes) {
            int indice = encabezadoSimbolos.indexOf(tablaSiguiente[Integer.parseInt(siguiente) - 1][0]);
            if (encabezadoSimbolos.elementAt(indice) == "#") {
                continue;
            }
            if (indice != -1) {
                if (inicial.getSimbolos()[indice] == null) {
                    inicial.getSimbolos()[indice] = tablaSiguiente[Integer.parseInt(siguiente) - 1][1];
                } else {
                    inicial.getSimbolos()[indice] += "," + tablaSiguiente[Integer.parseInt(siguiente) - 1][1];
                }
            }
        }
        tablaTransicion.add(inicial);
        for (int i = 0; i < inicial.getSimbolos().length; i++) {
            boolean existe = false;
            for (int j = 0; j < tablaTransicion.size(); j++) {
                if (tablaTransicion.elementAt(j).getEstado().equals(inicial.getSimbolos()[i])) {
                    existe = true;
                    break;
                }
            }
            if (inicial.getSimbolos()[i] != null && existe == false) {
                restantes.add(inicial.getSimbolos()[i]);
            }
        }

        while (!restantes.empty()) {
            String estado = restantes.pop();
            //System.out.println("------" + estado);
            FilaEstados nuevo = new FilaEstados(estado, encabezadoSimbolos.size());
            siguientes = nuevo.getEstado().split(",");
            for (String siguiente : siguientes) {
                int indice = encabezadoSimbolos.indexOf(tablaSiguiente[Integer.parseInt(siguiente) - 1][0]);
                if (encabezadoSimbolos.elementAt(indice) == "#") {
                    continue;
                }
                if (indice != -1) {
                    if (nuevo.getSimbolos()[indice] == null) {
                        nuevo.getSimbolos()[indice] = tablaSiguiente[Integer.parseInt(siguiente) - 1][1];
                    } else {
                        nuevo.getSimbolos()[indice] += "," + tablaSiguiente[Integer.parseInt(siguiente) - 1][1];
                    }
                }
            }
            tablaTransicion.add(nuevo);
            for (int i = 0; i < nuevo.getSimbolos().length; i++) {
                boolean existe = false;
                for (int j = 0; j < tablaTransicion.size(); j++) {
                    if (tablaTransicion.elementAt(j).getEstado().equals(nuevo.getSimbolos()[i])) {
                        existe = true;
                        //System.out.println("Entro!!!" + tablaTransicion.elementAt(j).getEstado());
                        break;
                    }
                }
                if (!restantes.contains(nuevo.getSimbolos()[i]) && nuevo.getSimbolos()[i] != null && existe == false) {
                    //System.out.println("entro2222222--"+nuevo.getSimbolos()[i]);
                    restantes.add(nuevo.getSimbolos()[i]);
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
                + "            <table border=\"1\" cellborder=\"0\" cellspacing=\"0\">"
                + "<tr><td><b>Estado</b></td>";
        for (int i = 0; i < encabezadoSimbolos.size(); i++) {
            cuerpo += "<td><b>" + encabezadoSimbolos.elementAt(i) + "</b></td>";
        }
        cuerpo += "</tr>";
        for (int i = 0; i < tablaTransicion.size(); i++) {
            cuerpo += "<tr><td>" + tablaTransicion.elementAt(i).getEstado() + "</td>";
            for (int j = 0; j < tablaTransicion.elementAt(i).getSimbolos().length; j++) {
                cuerpo += "<td>" + tablaTransicion.elementAt(i).getSimbolos()[j] + "</td>";
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

    public void crearArchivo(String nombre, String cuerpo, String carpeta) {
        FileWriter fichero = null;
        try {
            File directorio = new File("textosDot");
            if (!directorio.exists()){
                if (directorio.mkdirs()) {
                    System.out.println("Directorio creado");
                } else {
                    System.out.println("Error al crear directorio");
                    return;
                }
            }
            fichero = new FileWriter("textosDot\\"+nombre + ".dot");
            PrintWriter pw = null;
            pw = new PrintWriter(fichero);
            pw.println(cuerpo);
            pw.close();
            try {
                ProcessBuilder proceso;
                proceso = new ProcessBuilder("dot", "-Tjpg", "-o", carpeta +nombre + "_hecho.jpg","textosDot\\"+ nombre + ".dot");
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
}
