package uvg.edu.gt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArbolBinarioTest {

    private ArbolBinario<String, String> diccionario;

    @BeforeEach
    void setUp() {
        diccionario = new ArbolBinario<>();
        diccionario.insert("house", "casa");
        diccionario.insert("dog", "perro");
        diccionario.insert("homework", "tarea");
    }

    @Test
    void testInsertAndSearch() {
        // Prueba buscando una palabra que sí existe en el diccionario
        assertEquals("casa", diccionario.search("house"), "La traducción de 'house' debería ser 'casa'");
        assertEquals("perro", diccionario.search("dog"), "La traducción de 'dog' debería ser 'perro'");
        
        // Prueba buscando una palabra que no existe en el diccionario
        assertNull(diccionario.search("cat"), "La búsqueda de una palabra no existente debería retornar null");
    }

    @Test
    void testInsertOverwrite() {
        // Prueba sobreescribiendo una traducción existente
        diccionario.insert("dog", "can");
        assertEquals("can", diccionario.search("dog"), "La traducción de 'dog' debería haber sido sobrescrita a 'can'");
    }
}
