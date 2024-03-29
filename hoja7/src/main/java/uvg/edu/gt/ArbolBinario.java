package uvg.edu.gt;

public class ArbolBinario<K extends Comparable<K>, V> {

    private Nodo root;

    // Clase interna que representa un nodo en el árbol
    private class Nodo {
        Asociacion<K, V> Asociacion;
        Nodo left;
        Nodo right;

        public Nodo(Asociacion<K, V> Asociacion) {
            this.Asociacion = Asociacion;
            this.left = null;
            this.right = null;
        }
    }

    // Constructor que inicializa la raíz del árbol
    public ArbolBinario() {
        root = null;
    }

    // Método público para insertar una nueva asociación
    public void insert(K key, V value) {
        Asociacion<K, V> newAsociacion = new Asociacion<>(key, value);
        root = insertRec(root, newAsociacion);
    }

    // Método auxiliar para insertar de manera recursiva
    private Nodo insertRec(Nodo current, Asociacion<K, V> Asociacion) {
        if (current == null) {
            return new Nodo(Asociacion);
        }

        int compareResult = Asociacion.getKey().compareTo(current.Asociacion.getKey());

        if (compareResult < 0) {
            current.left = insertRec(current.left, Asociacion);
        } else if (compareResult > 0) {
            current.right = insertRec(current.right, Asociacion);
        } else {
            // La clave ya existe, actualizar este nodo con el nuevo valor
            current.Asociacion = Asociacion;
        }

        return current;
    }

    // Método público para realizar el recorrido in-order
    public void inOrder() {
        inOrderRec(root);
    }

    // Método auxiliar para el recorrido in-order de manera recursiva
    private void inOrderRec(Nodo node) {
        if (node != null) {
            inOrderRec(node.left);
            // Asegúrate de incluir una coma y un espacio entre la palabra en inglés y la traducción al español
            System.out.println("(" + node.Asociacion.getKey() + ", " + node.Asociacion.getValue() + ")");
            inOrderRec(node.right);
        }
    }
    

    // Método público para buscar un valor asociado a una clave
    public V search(K key) {
        return searchRec(root, key);
    }

    // Método auxiliar para buscar de manera recursiva
    private V searchRec(Nodo node, K key) {
        if (node == null) {
            return null; // No encontrado
        }

        int compareResult = key.compareTo(node.Asociacion.getKey());

        if (compareResult < 0) {
            return searchRec(node.left, key);
        } else if (compareResult > 0) {
            return searchRec(node.right, key);
        } else {
            return node.Asociacion.getValue(); // Encontrado
        }
    }
}

