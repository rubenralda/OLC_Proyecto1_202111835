/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ER;

import java.util.Vector;

/**
 *
 * @author ruben
 */
public class ExpresionesRegulares {
    //falta agregar los conjuntos a la expresion regular grande 
    private static Vector<String> expresion = new Vector<String>();
    private static Vector<Expresion> expresiones = new Vector<Expresion>();
    private static Vector<Expresion> conjuntos = new Vector<Expresion>();
    private static Vector<String> conjunto = new Vector<String>();

    
    public static Vector<Expresion> getExpresiones() {
        return expresiones;
    }
 
    /*
        agregarExpresion
        crea un objeto con el identificador que 
        envie el cup y por el momento con valor null
     */
    public static void agregarExpresion(String nombre) {
        Vector<String> valor = new Vector<String>();
        expresiones.add(new Expresion(nombre, valor));
        expresion = valor;
    }

    /*
        agregarLexema
        como el expresion equivale a el valor de la clase
        se añade un string con el lexema
     */
    public static void agregarLexema(String lexema) {
        expresion.add(lexema);
    }

    public static void agregarEntrada(String nombre, String entrada) {
        for (int i = 0; i < expresiones.size(); i++) {
            if (expresiones.elementAt(i).getNombre().equals(nombre)) {
                expresiones.elementAt(i).agregarEntrada(entrada);
                break;
            }
        }
    }

    public static void agregarLexemaConjunto(String lexema) {
        conjunto.add(lexema);
    }

    public static void agregarLexemaConjunto(String inicio, String ultimo) {
        //codigo para crear un conjunto de lexemas con el inicio y el final
    }

    public static void agregarConjunto(String nombre) {
        Vector<String> valor = new Vector<String>();
        conjuntos.add(new Expresion(nombre, valor));
        conjunto = valor;
    }
    
    public static void sumarAlUltimo(){
        expresiones.elementAt(expresiones.size()-1).sumarUno();
    }
    public static void mostrarConjuntos() {
        for (int i = 0; i < conjuntos.size(); i++) {
            System.out.print(String.valueOf(i) + conjuntos.elementAt(i).getNombre() + "\t");
            for (int j = 0; j < conjuntos.elementAt(i).getValor().size(); j++) {
                System.out.print(String.valueOf(i) + conjuntos.elementAt(i).getValor().elementAt(j) + String.valueOf(j) + "\t");
            }
            System.out.println();
        }
    }

    public static void mostrarExpresiones() {
        for (int i = 0; i < expresiones.size(); i++) {
            System.out.print(String.valueOf(i) + expresiones.elementAt(i).getNombre() + "\t");
            for (int j = 0; j < expresiones.elementAt(i).getValor().size(); j++) {
                System.out.print(String.valueOf(i) + expresiones.elementAt(i).getValor().elementAt(j) + String.valueOf(j) + "\t");
            }
            System.out.println();
        }
    }
}
