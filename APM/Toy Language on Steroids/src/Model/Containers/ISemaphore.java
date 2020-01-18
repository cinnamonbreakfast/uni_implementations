package Model.Containers;

import javafx.util.Pair;

import java.util.List;

public interface ISemaphore {
    void setSemaphores(
            IDictionary<Integer, Triplet<Integer, List<Integer>, Integer>> semaphores
    );

    public IDictionary<Integer, Triplet<Integer, List<Integer>, Integer>> getSemaphore();

    public Integer getSemaphoreAddress();

    void put(Integer foundIndex, Triplet<Integer, List<Integer>, Integer> integerListPair);
}
