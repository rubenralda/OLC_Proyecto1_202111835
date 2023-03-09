/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodoArbol;

/**
 *
 * @author ruben
 */

public class Nodo {
    private int identificador;
    private String tipo;
    private boolean anulable;
    private String primeros;
    private String ultimos;
    private Nodo izquierda;
    private Nodo derecha;

    public Nodo(int identificador, String tipo, boolean anulable, String primeros, String ultimos, Nodo izquierda, Nodo derecha) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.anulable = anulable;
        this.primeros = primeros;
        this.ultimos = ultimos;
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isAnulable() {
        return anulable;
    }

    public String getPrimeros() {
        return primeros;
    }

    public String getUltimos() {
        return ultimos;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }

    public void setPrimeros(String primeros) {
        this.primeros = primeros;
    }

    public void setUltimos(String ultimos) {
        this.ultimos = ultimos;
    }

    public void setIzquierda(Nodo izquierda) {
        this.izquierda = izquierda;
    }

    public void setDerecha(Nodo derecha) {
        this.derecha = derecha;
    }
    
}
