package Model.Containers;

public class MyLatch implements ILatch {
    IDictionary<Integer, Integer> table;
    private int IDs;

    public MyLatch() {
        table = new MyDictionary<>();
        IDs = 0;
    }

    @Override
    public void setLatchTable(IDictionary<Integer, Integer> table) {
        this.table = table;
    }

    @Override
    public IDictionary<Integer, Integer> getLatchTable() {
        return table;
    }

    @Override
    public Integer getLatchAddress() {
        return IDs++;
    }

    @Override
    public void put(Integer a, Integer b) {
        table.put(a, b);
    }
}
