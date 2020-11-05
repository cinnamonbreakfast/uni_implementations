package service;

import domain.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Service<ID extends Serializable, T extends Entity<ID>> {
    List<T> getAll();
    List<T> getAllSortedAscendingByFields(String... fields);
    List<T> getAllSortedDescendingByFields(String... fields);
    T getByID(ID id);
}
