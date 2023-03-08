/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodo.arbol;

import java.util.Stack;
import java.util.Vector;

/**
 *
 * @author ruben
 */
public class Arbol {
    
    private Nodo raiz = null;
    private String nombre;
    private static Vector<String> expresion;
    
    public Arbol(Vector<String> expresion, String nombre) {
        this.expresion = expresion;
        this.nombre = nombre;
    }
    
    public void crearArbol() {
        Stack<Nodo> pila = new Stack<Nodo>();
        int ide = 0;
        Nodo hoja = null;
        Nodo hoja2 = null;
        Nodo resultado = null;
        boolean anulable = false;
        for (int i = expresion.size() - 1; i >= 0; i--) {
            switch (expresion.elementAt(i)) {
                case "*":
                    hoja = pila.pop();
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
                    resultado = new Nodo(0, ".", anulable, primera, ultimo, hoja, hoja2);
                    pila.add(resultado);
                    break;
                default:
                    ide++;
                    Nodo nuevo = new Nodo(ide, expresion.elementAt(i), false, String.valueOf(ide), String.valueOf(ide), null, null);
                    raiz = nuevo;
                    pila.add(nuevo);
            }
        }
        raiz = pila.pop();
        if (!pila.empty()) {
            System.out.println("Error");
        }
    }
    
    public String postOrden(Nodo nodo) {
        String padre = "";
        if (nodo != null) {
            padre += nodo.hashCode() + " [label=\"" + nodo.getTipo() 
                    + "\nanulable: " + String.valueOf(nodo.isAnulable())
                    + "\nIde: " + String.valueOf(nodo.getIdentificador())
                    + " " + "\"]; \n";
            if (nodo.getIzquierda() != null) {
                padre += nodo.hashCode() + " -- " + nodo.getIzquierda().hashCode() +";\n";
                padre += postOrden(nodo.getIzquierda());
            }
            if (nodo.getDerecha() != null) {
                padre += nodo.hashCode() + " -- " + nodo.getDerecha().hashCode() +";\n";
                padre += postOrden(nodo.getDerecha());
            }
        }
        return padre;
    }
    
    public void mostrar() {
        String cabeza = "graph{\n"
                + "    fontname=\"Helvetica,Arial,sans-serif\"\n"
                + "    node [fontname=\"Helvetica,Arial,sans-serif\"]\n"
                + "    edge [fontname=\"Helvetica,Arial,sans-serif\"]\n";
        cabeza += postOrden(raiz);
        cabeza += "}";
        System.out.println(cabeza);
    }
}
