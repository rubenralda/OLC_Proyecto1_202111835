/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analizadores;

/**
 *
 * @author ruben
 */
public class Generador {
     public static void main(String[] args) {
        generarCompilador();
    }
    
    private static void generarCompilador(){
        try {
            
            String ruta = "src/Analizadores/";    
            //ruta donde tenemos los archivos con extension .jflex y .cup
            String opcFlex[] = { ruta + "lexico.jflex", "-d", ruta };
            jflex.Main.generate(opcFlex);
            String opcCUP[] = { "-destdir", ruta, "-parser", "parser", ruta + "parser.cup" };
            java_cup.Main.main(opcCUP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
