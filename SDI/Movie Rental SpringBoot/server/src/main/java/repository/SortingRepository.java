package repository;

import domain.Entity;
import repository.sorting.Sort;

import java.io.Serializable;

public interface SortingRepository<
        ID extends Serializable,
        T extends Entity<ID>
        > extends Repository<ID, T> {

    Iterable<T> findAll(Sort sort);
}