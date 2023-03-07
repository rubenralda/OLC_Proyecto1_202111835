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
public class Expresion {

    private String nombre;
    private Vector<String> valor;
    private Vector<String> entradas = new Vector<String>();
    
    public Expresion(String nombre, Vector<String> valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public Vector<String> getValor() {
        return valor;
    }

    public Vector<String> getEntradas() {
        return entradas;
    }

    public void agregarEntrada(String entrada) {
        entradas.add(entrada);
    }

}
