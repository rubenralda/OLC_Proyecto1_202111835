/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Errores;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author ruben
 */
public class ReporteErrores {

    private static String encabezado = "<!DOCTYPE html>\n"
            + "<html lang=\"en\">\n"
            + "<head>\n"
            + "    <meta charset=\"UTF-8\">\n"
            + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
            + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
            + "    <title>Errores</title>\n"
            + "</head>\n"
            + "<body> <style>\n"
            + "        table {\n"
            + "          border: 1px solid black;\n"
            + "          border-collapse: collapse;\n"
            + "        }\n"
            + "        th, td {\n"
            + "          border: 1px solid black;\n"
            + "          padding: 10px;\n"
            + "          text-align: left;\n"
            + "        }\n"
            + "      </style>\n<h1>Errores</h1>\n"
            + "<table>\n"
            + "        <tr>\n"
            + "            <th>#</th>\n"
            + "            <th>Tipo de error</th>\n"
            + "            <th>Descripción</th>\n"
            + "            <th>Línea</th>\n"
            + "            <th>Columna</th>\n"
            + "        </tr>";
    private static String cuerpo = "";
    private static int conteo = 1;

    public static int getConteo() {
        return conteo;
    }
    
    public static void crear(String tipo, String descripcion, int linea, int columna) {
        cuerpo += " <tr>\n"
                + "    <td>"+String.valueOf(conteo)+"</td>\n"
                + "    <td>"+tipo+"</td>\n"
                + "    <td>"+descripcion+"</td>\n"
                + "    <td>"+String.valueOf(linea)+"</td>\n"
                + "    <td>"+String.valueOf(columna)+"</td>\n"
                + "  </tr>";
        FileWriter fichero = null;
        try {
            File directorio = new File("ERRORES_202111835");
            if (!directorio.exists()) {
                if (directorio.mkdirs()) {
                    System.out.println("Directorio creado");
                } else {
                    System.out.println("Error al crear directorio");
                    return;
                }
            }
            fichero = new FileWriter("ERRORES_202111835\\Errores" + ".html");
            PrintWriter pw = null;
            pw = new PrintWriter(fichero);
            pw.println(encabezado + cuerpo + "</table>\n</body>\n</html>");
            pw.close();
            conteo ++;

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

    public static void vaciar() {
        cuerpo = "";
        conteo = 1;
    }
}
