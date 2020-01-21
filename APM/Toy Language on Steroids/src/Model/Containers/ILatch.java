package Model.Containers;

public interface ILatch {
    void setLatchTable(IDictionary<Integer, Integer> table);

    IDictionary<Integer, Integer> getLatchTable();

    Integer getLatchAddress();

    void put(Integer a, Integer b);
}
