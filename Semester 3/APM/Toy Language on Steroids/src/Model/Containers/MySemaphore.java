package Model.Containers;

import java.util.List;

public class MySemaphore implements ISemaphore {
    private IDictionary<Integer, Triplet<Integer, List<Integer>, Integer>> semaphore;
    private int IDs;

    public MySemaphore() {
        semaphore = new MyDictionary<>();
        IDs = 0;
    }

    @Override
    public void setSemaphores(IDictionary<Integer, Triplet<Integer, List<Integer>, Integer>> semaphores) {
        this.semaphore = semaphores;
    }

    public boolean exists(int index)
    {
        return semaphore.contains(index);
    }

    @Override
    public IDictionary<Integer, Triplet<Integer, List<Integer>, Integer>> getSemaphore() {
        return semaphore;
    }

    @Override
    public Integer getSemaphoreAddress() {
        return IDs++;
    }

    @Override
    public void put(Integer foundIndex, Triplet<Integer, List<Integer>, Integer> integerListPair) {
        semaphore.put(foundIndex, integerListPair);
    }
}
