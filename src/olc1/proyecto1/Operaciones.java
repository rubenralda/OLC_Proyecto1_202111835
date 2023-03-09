/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc1.proyecto1;

import ER.Expresion;
import ER.ExpresionesRegulares;
import java.util.Vector;
import metodoArbol.Arbol;

/**
 *
 * @author ruben
 */
public class Operaciones {
    private Vector<Arbol> arboles = new Vector<Arbol>();
    
    public void crearArboles(Vector<Expresion> expresiones){
         for (int i = 0; i < expresiones.size(); i++) {
            expresiones.elementAt(i).getValor().add("#");
            expresiones.elementAt(i).getValor().add(0, ".");
            expresiones.elementAt(i).sumarUno();
            Arbol nuevo = new Arbol(expresiones.elementAt(i).getValor(), expresiones.elementAt(i).getNombre(), expresiones.elementAt(i).getCantCaracteres());
            nuevo.crearArbol();
            System.out.println("Arbol y tabla siguiente creado: " + expresiones.elementAt(i).getNombre());
            nuevo.mostrarArbol();
            System.out.println("Arbol Graficado: " + expresiones.elementAt(i).getNombre());
            nuevo.mostrarTablaSiguientes();
            System.out.println("Tabla siguiente graficada: " + expresiones.elementAt(i).getNombre());
            nuevo.CrearTablaTransicion();
            System.out.println("Tabla de transicion Creada: " + expresiones.elementAt(i).getNombre());
            nuevo.mostrarTablaTransicion();
            System.out.println("Tabla de transicion graficada: " + expresiones.elementAt(i).getNombre());
            arboles.add(nuevo);
        }
        ExpresionesRegulares.mostrarConjuntos();
        ExpresionesRegulares.mostrarExpresiones();
    }

    public Vector<Arbol> getArboles() {
        return arboles;
    }
    
}
