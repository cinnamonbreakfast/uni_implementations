package Domain.Containers;

import Domain.Entity;

public class Pair<L, R> extends Entity<Long> {
    private final L left;
    private final R right;

    /**
     * Pair of two elements
     *
     * @param left
     * @param right
     */
    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Getter for left hand element of the pair
     *
     * @return left element of type L
     */
    public L getLeft() {
        return left;
    }

    /**
     * Getter for right hand element of the pair
     *
     * @return left element of type R
     */
    public R getRight() {
        return right;
    }
}
