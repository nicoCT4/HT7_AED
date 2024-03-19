package uvg.edu.gt;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArbolBinario<String, String> diccionario = new ArbolBinario<>();
    
        String rutaAbsolutaDiccionario = "C:\\Users\\nicol\\OneDrive\\Documents\\UVG\\Tercer Semestre\\Algoritmos y Estructura de Datos\\HT7_AED\\HT7_AED\\hoja7\\src\\main\\java\\uvg\\edu\\gt\\diccionario.txt";
        String rutaAbsolutaTexto = "C:\\\\Users\\\\n" + //
                        "icol\\\\OneDrive\\\\Documents\\\\UVG\\\\Tercer Semestre\\\\Algoritmos y Estructura de Datos\\\\HT7_AED\\\\HT7_AED\\\\hoja7\\\\src\\\\main\\\\java\\\\uvg\\\\edu\\\\gt\\\\texto.txt";
    
        cargarDiccionario(diccionario, rutaAbsolutaDiccionario);
        diccionario.inOrder();
        
        String textoTraducido = traducirTexto(diccionario, rutaAbsolutaTexto);
        
        System.out.println(textoTraducido);
    }
    

    private static void cargarDiccionario(ArbolBinario<String, String> diccionario, String rutaAbsolutaDiccionario) {
        try {
            Scanner archivo = new Scanner(new File(rutaAbsolutaDiccionario));
            while (archivo.hasNextLine()) {
                String linea = archivo.nextLine();
                String[] partes = linea.substring(1, linea.length() - 1).split(" ");
                if (partes.length >= 2) {
                    String ingles = partes[0].toLowerCase(); // Convertir a minúsculas para la consistencia
                    String espanol = partes[1];
                    diccionario.insert(ingles, espanol);
                }
            }
            archivo.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + rutaAbsolutaDiccionario);
        }
    }
    
    
    private static String traducirTexto(ArbolBinario<String, String> diccionario, String rutaAbsolutaTexto) {
        StringBuilder textoTraducido = new StringBuilder();
        try {
            Scanner archivoTexto = new Scanner(new File(rutaAbsolutaTexto));
            
            while (archivoTexto.hasNext()) {
                String palabraOriginal = archivoTexto.next();
                // Conservamos la palabra original para añadir los signos de puntuación luego de la traducción
                String palabraParaBuscar = palabraOriginal.toLowerCase().replaceAll("[^a-zA-Z]", ""); // Limpiar palabra
    
                // Depuración: Imprimir la palabra buscada
                System.out.println("Buscando: " + palabraParaBuscar);
                
                String traduccion = diccionario.search(palabraParaBuscar);
                
                if (traduccion != null) {
                    // Palabra encontrada y traducida
                    textoTraducido.append(traduccion);
                } else {
                    // Palabra no encontrada, se mantiene la original con asteriscos
                    textoTraducido.append("*").append(palabraOriginal).append("*");
                }
    
                // Manejo de signos de puntuación y espacios
                String signosPuntuacion = palabraOriginal.replaceAll("[a-zA-Z]", "");
                textoTraducido.append(signosPuntuacion).append(" "); // Añadir signos de puntuación al final y espacio
            }
            
            archivoTexto.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + rutaAbsolutaTexto);
            return null;
        }
        
        return textoTraducido.toString().trim(); // Eliminar espacio extra al final
    }
    
}

