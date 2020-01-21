package Model.Containers;

import java.util.List;

public interface ISemaphore {
    void setSemaphores(
            IDictionary<Integer, Triplet<Integer, List<Integer>, Integer>> semaphores
    );

    IDictionary<Integer, Triplet<Integer, List<Integer>, Integer>> getSemaphore();

    Integer getSemaphoreAddress();

    void put(Integer foundIndex, Triplet<Integer, List<Integer>, Integer> integerListPair);
}
