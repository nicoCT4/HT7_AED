package uvg.edu.gt;

public class Asociacion<K, V> {
    private K key;
    private V value;

    // Constructor que inicializa la clave y el valor de la asociación
    public Asociacion(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // Getter para la clave
    public K getKey() {
        return key;
    }

    // Getter para el valor
    public V getValue() {
        return value;
    }

    // Setter para la clave, en caso de que necesites modificarla
    public void setKey(K key) {
        this.key = key;
    }

    // Setter para el valor, en caso de que necesites modificarlo
    public void setValue(V value) {
        this.value = value;
    }

    // Método toString para representar la asociación como una cadena de texto
    @Override
    public String toString() {
        return "(" + key + " - " + value + ")";
    }
}

