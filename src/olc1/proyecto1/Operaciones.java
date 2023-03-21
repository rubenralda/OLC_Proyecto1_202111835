/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto1;

import ER.Expresion;
import ER.ExpresionesRegulares;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Vector;
import metodoArbol.Arbol;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

/**
 *
 * @author ruben
 */
public class Operaciones {

    private Vector<Arbol> arboles = new Vector<Arbol>();
    private JsonArray jsonArray = new JsonArray();

    public String generarMetodoDeArbol(Vector<Expresion> expresiones) {
        String reporte = "";
        for (int i = 0; i < expresiones.size(); i++) {
            expresiones.elementAt(i).getValor().add("#");
            expresiones.elementAt(i).getValor().add(0, ".");
            expresiones.elementAt(i).sumarUno();
            Arbol nuevo = new Arbol(expresiones.elementAt(i).getValor(), expresiones.elementAt(i).getNombre(), expresiones.elementAt(i).getCantCaracteres());
            nuevo.crearArbol();
            reporte += "\nArbol y tabla siguiente creado:" + expresiones.elementAt(i).getNombre();
            nuevo.mostrarArbol();
            reporte += "\nArbol Graficado: " + expresiones.elementAt(i).getNombre();
            nuevo.mostrarTablaSiguientes();
            reporte += "\nTabla siguiente graficada: " + expresiones.elementAt(i).getNombre();
            nuevo.CrearTablaTransicion();
            reporte += "\nTabla de transicion Creada: " + expresiones.elementAt(i).getNombre();
            nuevo.mostrarTablaTransicion();
            reporte += "\nTabla de transicion graficada: " + expresiones.elementAt(i).getNombre();
            nuevo.mostrarAutomata();
            reporte += "\nAutomata finito determinista creado: " + expresiones.elementAt(i).getNombre();
            ExpresionesRegulares.conteoAFND = 0;
            nuevo.mostrarAFND();
            reporte += "\nAutomata finito no determinista creado: " + expresiones.elementAt(i).getNombre();
            arboles.add(nuevo);
        }
        //ExpresionesRegulares.mostrarConjuntos();
        //ExpresionesRegulares.mostrarExpresiones();
        return reporte;
    }

    public String validarCadenas(Vector<Expresion> expresiones ,String nombreArchivo) {
        String mensaje = "";
        // Crear un objeto JSON utilizando la librería Jackson
        
        for (int i = 0; i < arboles.size(); i++) {
            for (int j = 0; j < expresiones.size(); j++) {
                if (arboles.elementAt(i).getNombre().equals(expresiones.elementAt(j).getNombre())) {
                    for (int k = 0; k < expresiones.elementAt(j).getEntradas().size(); k++) {
                        JsonObject jsonObject = new JsonObject();
                        String entrada = expresiones.elementAt(j).getEntradas().elementAt(k).substring(1, expresiones.elementAt(j).getEntradas().elementAt(k).length() - 1);
                        System.out.println(entrada);
                        jsonObject.addProperty("Valor", entrada);
                        jsonObject.addProperty("ExpresionRegular", expresiones.elementAt(j).getNombre());
                        if (arboles.elementAt(i).validarCadenas(entrada)) {
                            jsonObject.addProperty("Resultado", "Cadena Valida");
                            mensaje += "La expresión: \"" + entrada + "\" SI es válida con la expresión regular " + arboles.elementAt(i).getNombre() + ".\n";
                        } else {
                            jsonObject.addProperty("Resultado", "Cadena No Valida");
                            mensaje += "La expresión: \"" + entrada + "\" NO es válida con la expresión regular " + arboles.elementAt(i).getNombre() + ".\n";
                        }
                        jsonArray.add(jsonObject);
                    }
                }
            }
        }
        crearArchivo(nombreArchivo, jsonArray.toString());
        return mensaje;
    }

    public void crearArchivo(String nombre, String cuerpo) {
        FileWriter fichero = null;
        try {
            File directorio = new File("SALIDAS_202111835");
            if (!directorio.exists()) {
                if (directorio.mkdirs()) {
                    System.out.println("Directorio creado");
                } else {
                    System.out.println("Error al crear directorio");
                    return;
                }
            }
            fichero = new FileWriter("SALIDAS_202111835\\" + nombre + ".json");
            PrintWriter pw = null;
            pw = new PrintWriter(fichero);
            pw.println(cuerpo);
            pw.close();
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

    public boolean vacio() {
        return arboles.isEmpty();
    }

    public void Vaciar() {
        arboles.clear();
        jsonArray = new JsonArray();
        ExpresionesRegulares.getExpresiones().clear();
        ExpresionesRegulares.getConjuntos().clear();
        ExpresionesRegulares.getConjunto().clear();
        ExpresionesRegulares.getExpresion().clear();
    }

    public Vector<Arbol> getArboles() {
        return arboles;
    }

}
