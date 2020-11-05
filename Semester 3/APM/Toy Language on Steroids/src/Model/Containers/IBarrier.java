package Model.Containers;

import javafx.util.Pair;

import java.util.List;

public interface IBarrier {
    void setBarriers(IDictionary<Integer, Pair<Integer, List<Integer>>> barriers);
    IDictionary<Integer, Pair<Integer, List<Integer>>> getBarriers();

    int getBarrierAddress();

    public void put(int index, Pair<Integer, List<Integer>> pair);
}
