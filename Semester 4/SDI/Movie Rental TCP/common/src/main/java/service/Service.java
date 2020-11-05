package service;

import Domain.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Service<ID extends Serializable, T extends Entity<ID>> {
    CompletableFuture<List<T>> getAll();
    CompletableFuture<List<T>> getAllSortedAscendingByFields(String... fields);
    CompletableFuture<List<T>> getAllSortedDescendingByFields(String... fields);
    CompletableFuture<T> getByID(ID id);
}
