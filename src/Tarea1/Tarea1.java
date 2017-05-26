package Tarea1;

import GUI.VentanaPrincipal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class Tarea1 {

    public static JFileChooser jfcAbrir;
    public static File archivoSelec;
    public static int filas;
    public static int columnas;
    public static int[][] matriz;

    public static void main(String[] args) {
        jfcAbrir = new JFileChooser();
        jfcAbrir.showOpenDialog(null);

        archivoSelec = jfcAbrir.getSelectedFile();

        if (archivoSelec != null) {
            tamaño();
            matriz = new int[filas][columnas];
            cargarMatriz();
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.myPanel.setMatriz(matriz);
            ventana.setVisible(true);
        }//if archivo
    }//main
    
    public static void tamaño() {
        FileReader fileReader;
        try {
            fileReader = new FileReader(archivoSelec);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                if (filas == 0) {
                    String[] colum = linea.split(",");
                    columnas = colum.length;
                }
                filas++;
            }//while
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }//try-catch
    }//tamaño

    public static void cargarMatriz() {
        FileReader fileReader;
        try {
            fileReader = new FileReader(archivoSelec);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;
            int fila = 0;
            while ((linea = bufferedReader.readLine()) != null) {
                String[] colum = linea.split(",");
                for (int i = 0; i < colum.length; i++) {
                    matriz[fila][i] = Integer.parseInt(colum[i]);
                }//for para llenar fila
                fila++;
            }//while
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }//try-catch
    }//tamaño
}//class
