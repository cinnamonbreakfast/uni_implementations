package Model.Containers;

public interface IList<T> {
    boolean add(T elem);
    boolean remove(T elem);
    int size();
}
