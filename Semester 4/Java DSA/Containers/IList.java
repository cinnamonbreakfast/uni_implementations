package Containers;

public interface IList<T> {
    boolean add(T elem);
    boolean remove(T elem);
    T get(int index);
}
