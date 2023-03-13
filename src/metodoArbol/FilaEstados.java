/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodoArbol;

/**
 *
 * @author ruben
 */
public class FilaEstados {
    
    private String estado;
    private String identificador;
    private boolean aceptacion;
    private String[][] simbolos;
    
    public FilaEstados(String estado, int cantSimbolos){
        this.estado = estado;
        this.simbolos = new String[cantSimbolos][2];
        this.aceptacion = false;
        this.identificador = "hola";
    }

    public String getEstado() {
        return estado;
    }

    public String[][] getSimbolos() {
        return simbolos;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setSimbolo(String[][] simbolo) {
        this.simbolos = simbolo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public boolean isAceptacion() {
        return aceptacion;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setAceptacion(boolean aceptacion) {
        this.aceptacion = aceptacion;
    }

    public void setSimbolos(String[][] simbolos) {
        this.simbolos = simbolos;
    }
    
}
