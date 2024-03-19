package uvg.edu.gt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Clase principal que ejecuta la lógica para traducir un texto del inglés al español
 * utilizando un diccionario cargado desde un archivo.
 */
public class Main {

    /**
     * Punto de entrada principal del programa.
     * 
     * @param args Argumentos de línea de comando (no utilizados).
     */
    public static void main(String[] args) {
        ArbolBinario<String, String> diccionario = new ArbolBinario<>();
    
        // Rutas absolutas a los archivos diccionario y texto.
        String rutaAbsolutaDiccionario = "C:\\Users\\nicol\\OneDrive\\Documents\\UVG\\Tercer Semestre\\Algoritmos y Estructura de Datos\\HT7_AED\\HT7_AED\\hoja7\\src\\main\\java\\uvg\\edu\\gt\\diccionario.txt";
        String rutaAbsolutaTexto = "C:\\\\Users\\\\n" + 
                                "icol\\\\OneDrive\\\\Documents\\\\UVG\\\\Tercer Semestre\\\\Algoritmos y Estructura de Datos\\\\HT7_AED\\\\HT7_AED\\\\hoja7\\\\src\\\\main\\\\java\\\\uvg\\\\edu\\\\gt\\\\texto.txt";
    
        cargarDiccionario(diccionario, rutaAbsolutaDiccionario);
        diccionario.inOrder();
        
        String textoTraducido = traducirTexto(diccionario, rutaAbsolutaTexto);
        
        System.out.println(textoTraducido);
    }
    
    /**
     * Carga el diccionario de traducciones de inglés a español desde un archivo.
     * 
     * @param diccionario El árbol binario donde se almacenarán las traducciones.
     * @param rutaAbsolutaDiccionario La ruta absoluta al archivo de diccionario.
     */
    private static void cargarDiccionario(ArbolBinario<String, String> diccionario, String rutaAbsolutaDiccionario) {
        try {
            Scanner archivo = new Scanner(new File(rutaAbsolutaDiccionario));
            while (archivo.hasNextLine()) {
                String linea = archivo.nextLine().trim();
                String[] partes = linea.substring(1, linea.length() - 1).split(",");
                if (partes.length >= 2) {
                    String ingles = partes[0].trim().toLowerCase();
                    String espanol = partes[1].trim();
                    diccionario.insert(ingles, espanol);
                }
            }
            archivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + rutaAbsolutaDiccionario);
        }
    }
    
    /**
     * Traduce un texto del inglés al español utilizando el diccionario proporcionado.
     * Las palabras no encontradas se marcan con asteriscos.
     * 
     * @param diccionario El árbol binario que contiene las traducciones.
     * @param rutaAbsolutaTexto La ruta absoluta al archivo de texto a traducir.
     * @return El texto traducido.
     */
    private static String traducirTexto(ArbolBinario<String, String> diccionario, String rutaAbsolutaTexto) {
        StringBuilder textoTraducido = new StringBuilder();
        try {
            Scanner archivoTexto = new Scanner(new File(rutaAbsolutaTexto));
            
            while (archivoTexto.hasNext()) {
                String palabraOriginal = archivoTexto.next();
                String palabraParaBuscar = palabraOriginal.toLowerCase().replaceAll("[^a-zA-Z]", "");
                String traduccion = diccionario.search(palabraParaBuscar);
                
                if (traduccion != null) {
                    textoTraducido.append(traduccion);
                } else {
                    textoTraducido.append("*").append(palabraOriginal).append("*");
                }
    
                String signosPuntuacion = palabraOriginal.replaceAll("[a-zA-Z]", "");
                textoTraducido.append(signosPuntuacion).append(" ");
            }
            
            archivoTexto.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + rutaAbsolutaTexto);
            return null;
        }
        
        return textoTraducido.toString().trim();
    }
}
