/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto1;

import ER.Expresion;
import java.util.Vector;
import metodo.arbol.Arbol;

/**
 *
 * @author ruben
 */
public class Operaciones {
    private Vector<Arbol> arboles = new Vector<Arbol>();
    
    public void crearArboles(Vector<Expresion> expresiones){
         for (int i = 0; i < expresiones.size(); i++) {
            Arbol nuevo = new Arbol(expresiones.elementAt(i).getValor(), expresiones.elementAt(i).getNombre());
            nuevo.crearArbol();
            nuevo.mostrar();
            arboles.add(nuevo);
            System.out.println("Arbol creado: " + expresiones.elementAt(i).getNombre());
        }
    }

    public Vector<Arbol> getArboles() {
        return arboles;
    }
    
}
