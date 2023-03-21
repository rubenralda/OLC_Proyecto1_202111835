/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package olc1.proyecto1;

import Analizadores.Lexico;
import Analizadores.parser;
import ER.ExpresionesRegulares;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ruben
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    private String datos = "";
    private File archivo = null;
    private Operaciones operaciones = new Operaciones();
    private int imagenActual = 0;
    private File[] listaArchivos = null;

    public VentanaPrincipal() {
        initComponents();
        jLabel2.setBackground(Color.WHITE);
        jLabel2.setOpaque(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        texto = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        consola = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        comboBoxImagenes = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("Generar Autómata");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Analizar entrada");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        texto.setColumns(20);
        texto.setRows(5);
        jScrollPane1.setViewportView(texto);

        consola.setColumns(20);
        consola.setRows(5);
        jScrollPane2.setViewportView(consola);

        jLabel1.setText("Consola");

        nombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nombre.setText("Archivo");

        comboBoxImagenes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Árboles", "Siguientes", "Transiciones", "Autómata FD", "Autómata FND" }));
        comboBoxImagenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxImagenesActionPerformed(evt);
            }
        });

        jButton3.setText("Siguiente");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Anterior");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jMenuItem2.setText("Nuevo archivo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Abrir archivo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Guardar");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Guardar como");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nombre)
                                .addGap(418, 418, 418)
                                .addComponent(comboBoxImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 904, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(671, 671, 671))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre)
                    .addComponent(comboBoxImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        file.setFileFilter(new FileNameExtensionFilter("Archivos de texto", "olc"));
        int resultado = file.showOpenDialog(this);
        if (resultado != JFileChooser.APPROVE_OPTION) {
            JOptionPane.showMessageDialog(this, "Escoge un archivo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String linea = "";
        try {
            archivo = file.getSelectedFile();
            datos = "";
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            while ((linea = br.readLine()) != null) {
                datos += linea + "\n";
            }
            texto.setText(datos);
            nombre.setText(file.getName(archivo));
            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo leer el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (texto.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "El archivo esta vacío", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Errores.ReporteErrores.vaciar();
        operaciones.Vaciar();
        interpretar(texto.getText());
        if (Errores.ReporteErrores.getConteo() > 1){
            JOptionPane.showMessageDialog(this, "Hubo un error en el analisis", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        consola.setText(consola.getText()+operaciones.generarMetodoDeArbol(ExpresionesRegulares.getExpresiones()));
        comboBoxImagenes.actionPerformed(evt);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        texto.setText("");
        datos = "";
        nuevoArchivo();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        datos = texto.getText();
        if (archivo == null) {
            nuevoArchivo();
            return;
        }
        try {
            FileWriter save = new FileWriter(archivo);
            save.write(datos);
            save.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo guardar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        datos = texto.getText();
        nuevoArchivo();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (listaArchivos == null) {
            return;
        }
        if (listaArchivos.length > imagenActual + 1) {
            imagenActual++;
        } else {
            imagenActual = 0;
        }
        cargarImagen(imagenActual);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void comboBoxImagenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxImagenesActionPerformed
        // TODO add your handling code here:
        if (operaciones.vacio()) {
            JOptionPane.showMessageDialog(this, "Genere los automatas2", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        File folder;
        imagenActual = 0;
        switch (comboBoxImagenes.getSelectedIndex()) {
            case 0://arboles
                folder = new File("ARBOLES_202111835");
                listaArchivos = folder.listFiles();
                cargarImagen(0);
                break;
            case 1://siguientes
                folder = new File("SIGUIENTES_202111835");
                listaArchivos = folder.listFiles();
                cargarImagen(0);
                break;
            case 2://transiones
                folder = new File("TRANSICIONES_202111835");
                listaArchivos = folder.listFiles();
                cargarImagen(0);
                break;
            case 3://automatas
                folder = new File("AFD_202111835");
                listaArchivos = folder.listFiles();
                cargarImagen(0);
                break;
            case 4://automatas
                folder = new File("AFND_202111835");
                listaArchivos = folder.listFiles();
                cargarImagen(0);
                break;
            default:
                listaArchivos = null;
        }
    }//GEN-LAST:event_comboBoxImagenesActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (listaArchivos == null) {
            return;
        }
        if (0 <= imagenActual - 1) {
            imagenActual--;
        } else {
            imagenActual = listaArchivos.length - 1;
        }
        cargarImagen(imagenActual);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (operaciones.vacio()) {
            JOptionPane.showMessageDialog(this, "Genere los automatas", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String nombre = archivo.getName();
        if (nombre.endsWith(".olc")){
            nombre = nombre.substring(0, nombre.length()-4);
        }
        consola.setText(consola.getText()+"\n------------------------------------------\n"+operaciones.validarCadenas(ExpresionesRegulares.getExpresiones(),nombre));
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nuevoArchivo() {
        JFileChooser file = new JFileChooser();
        file.setFileFilter(new FileNameExtensionFilter("Archivos olc", "olc"));
        int resultado = file.showSaveDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            archivo = file.getSelectedFile();
            String fileName = archivo.getName();
            // Agregar extensión si no existe
            if (!fileName.endsWith(".olc")) {
                archivo = new File(archivo.getAbsolutePath() + ".olc");
            }
            try {
                FileWriter save = new FileWriter(archivo);
                save.write(datos);
                nombre.setText(archivo.getName());
                save.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "No se pudo crear el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Escoge un archivo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    private void cargarImagen(int i) {
        if (listaArchivos == null) {
            return;
        }
        if (listaArchivos[i].isFile()) {
            try {
                ImageIcon imageIcon = new ImageIcon(ImageIO.read(listaArchivos[i]));
                Image imagenEscalada = imageIcon.getImage().getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_SMOOTH);
                jLabel2.setIcon(new ImageIcon(imagenEscalada));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void interpretar(String entrada) {
        try {
            Lexico scanner = new Lexico(new java.io.StringReader(entrada));
            parser parser = new parser(scanner);
            parser.parse();
            consola.setText("Analísis finalizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxImagenes;
    private javax.swing.JTextArea consola;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nombre;
    private javax.swing.JTextArea texto;
    // End of variables declaration//GEN-END:variables
}
