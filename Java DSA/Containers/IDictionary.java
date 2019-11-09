package Containers;

import java.util.Collection;
import java.util.Map;

public interface IDictionary<K, V> {

    void put(K key, V value);
    void remove(K key);
    boolean contains(K key);
    V get(K key);

    Collection<V> values();
    Collection<K> keys();

    IDictionary<K, V> copy();

    Map<K, V> getMap();

    String toString();
}
