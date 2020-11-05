package Model.Containers;

import javafx.util.Pair;

import java.util.List;

public class MyBarrier implements IBarrier {
    private IDictionary<Integer, Pair<Integer, List<Integer>>> barriers;
    private int IDs;

    public MyBarrier()
    {
        barriers = new MyDictionary<>();
        IDs = 0;
    }

    public boolean exists(int index)
    {
        return barriers.contains(index);
    }

    @Override
    public void setBarriers(IDictionary<Integer, Pair<Integer, List<Integer>>> barriers) {
        this.barriers = barriers;
    }

    @Override
    public IDictionary<Integer, Pair<Integer, List<Integer>>> getBarriers() {
        return barriers;
    }

    @Override
    public int getBarrierAddress() {
        return IDs++;
    }

    @Override
    public void put(int index, Pair<Integer, List<Integer>> pair) {
        barriers.put(index, pair);
    }
}
