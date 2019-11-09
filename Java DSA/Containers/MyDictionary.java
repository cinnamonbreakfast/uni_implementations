package Containers;

import Exception.DictionaryException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements IDictionary<K, V> {
    private HashMap<K, V> dict;

    public MyDictionary()
    {
        dict = new HashMap<K, V>();
    }

    @Override
    public void put(K key, V value)
    {
        dict.put(key, value);
    }

    @Override
    public void remove(K key)
    {
        dict.remove(key);
    }

    @Override
    public boolean contains(K key)
    {
        return dict.containsKey(key);
    }

    @Override
    public V get(K key)
    {
        if (dict.get(key) == null) {
            throw new DictionaryException("Invalid key " + key + ".");
        }
        return dict.get(key);
    }

    @Override
    public Collection<V> values()
    {
        return dict.values();
    }

    @Override
    public Collection<K> keys()
    {
        return dict.keySet();
    }

    @Override
    public String toString()
    {
        String s = "";
        for(K key : dict.keySet())
            s += key.toString() + " -> " + dict.get(key).toString() + "\n";
        return s;
    }

    @Override
    public IDictionary<K, V> copy()
    {
        IDictionary<K, V> clonedDict = new MyDictionary<>();
        for (K key : this.keys())
            clonedDict.put(key, dict.get(key));
        return clonedDict;
    }

    @Override
    public Map<K, V> getMap() {
        return this.dict;
    }
}
