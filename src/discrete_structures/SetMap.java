package discrete_structures;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class SetMap<K, V> extends AbstractMap<K, V> {

    static final int DEFAULT_INITIAL_SIZE = 1 << 4; 
    static final int DEFAULT_MAXIMUM_SIZE = 1 << 30; 
    
    
    static class Node<K, V> implements Map.Entry<K, V> {
        final K key; 
        V value; 
        Node<K, V> next; 

        public Node(K key, V value, Node<K, V> next) {
            this.key = key; 
            this.value = value; 
            this.next = next; 
        }

        @Override
        public K getKey() {
            return key; 
        }

        @Override
        public V getValue() {
            return value; 
        }

        @Override
        public V setValue(V value) {
            this.value = value; 
            return value; 
        }
        
        public final boolean equals(Object o) {
            if (o == this) 
                return true; 

            if (o instanceof Map.Entry<?, ?>) {
                Map.Entry<?,?> entry = (Map.Entry<?,?>)o; 
                return Objects.equals(entry.getKey(), getKey()) && Objects.equals(entry.getValue(), getValue()); 
            }

            return false; 
        }
    }

    private Node<K, V>[] table; 

    public SetMap(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity " + initialCapacity); 
        } 

        initialCapacity = initialCapacity > DEFAULT_MAXIMUM_SIZE ? DEFAULT_MAXIMUM_SIZE : initialCapacity; 
        resize(initialCapacity); 
    }

    @SuppressWarnings({"rawtypes","unchecked"})
    private void resize(int capacity) {
        Node<K,V>[] newTable = (Node<K,V>[]) new Node[capacity]; 
        int i = 0; 
        if (capacity > DEFAULT_MAXIMUM_SIZE)
            return; 
        
        for (i = 0; i < capacity; i++) {
            if (i >= table.length) {
                break; 
            }
            newTable[i] = table[i]; 
        } 
        table = newTable; 
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        // TODO Auto-generated method stub
        return null;
    }
}