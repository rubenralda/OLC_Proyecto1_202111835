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
    private String[] simbolos;
    
    public FilaEstados(String estado, int cantSimbolos){
        this.estado = estado;
        this.simbolos = new String[cantSimbolos];
    }

    public String getEstado() {
        return estado;
    }

    public String[] getSimbolos() {
        return simbolos;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setSimbolo(String[] simbolo) {
        this.simbolos = simbolo;
    }
    
}
