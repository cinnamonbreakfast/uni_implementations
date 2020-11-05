package service;

import Domain.Client;
import repository.Repository;
import repository.SortingRepository;
import repository.sorting.Sort;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientServiceImpl implements ClientService {

    private Repository<Long, Client> repository;
    private ExecutorService executorService;
    // Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()) in main

    public ClientServiceImpl(Repository<Long, Client> repository, ExecutorService service) {
        this.repository = repository;
        executorService = service;
    }

    @Override
    public CompletableFuture<Void> addClient(Client entity) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Optional<Client> clients = repository.save(entity);
                if(clients.isPresent())
                    return null;
                throw new Exception("Could not memorise the client.");
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Client>> getAllClients(String... sort) {
        return CompletableFuture.supplyAsync(() -> {
            Sort sorted = new Sort(sort);
            Iterable<Client> clients = ((SortingRepository<Long, Client>)repository).findAll(sorted);
            return StreamSupport.stream(
                    clients.spliterator(),
                    false)
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Client>> filterClientsByFirstName(String name) {
        return CompletableFuture.supplyAsync(() -> {
            Sort sort = new Sort("firstName");
            Iterable<Client> clients = ((SortingRepository<Long, Client>)repository).findAll(sort);
            return StreamSupport.stream(
                    clients.spliterator(),
                    false)
                    .filter((e)->e.getFirstName()==name)
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Client>> filterClientsByLastName(String name) {
        return CompletableFuture.supplyAsync(() -> {
            Sort sort = new Sort("secondName");
            Iterable<Client> clients = ((SortingRepository<Long, Client>)repository).findAll(sort);
            return StreamSupport.stream(
                    clients.spliterator(),
                    false)
                    .filter((e)->e.getSecondName()==name)
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Client>> filterClientsByAge(int age) {
        return CompletableFuture.supplyAsync(() -> {
            Sort sort = new Sort("age");
            Iterable<Client> clients = ((SortingRepository<Long, Client>)repository).findAll(sort);
            return StreamSupport.stream(
                    clients.spliterator(),
                    false)
                    .filter((e)->e.getAge()==age)
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<Void> removeClient(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<Client> client = ((SortingRepository<Long, Client>)repository).delete(id);
            if(client.isPresent())
                return null;
            throw new RuntimeException("No client to delete.");
        }, executorService);
    }

    @Override
    public CompletableFuture<Void> updateClient(Client entity) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<Client> client = ((SortingRepository<Long, Client>)repository).update(entity);
            if(client.isPresent())
                return null;
            throw new RuntimeException("No client to update.");
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Client>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            Iterable<Client> clients = repository.findAll();
            return StreamSupport.stream(
                    clients.spliterator(),
                    false)
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Client>> getAllSortedAscendingByFields(String... fields) {
        return CompletableFuture.supplyAsync(() -> {
            Sort sort = new Sort(Sort.Direction.ASC, fields);
            Iterable<Client> clients = ((SortingRepository<Long, Client>)repository).findAll(sort);
            return StreamSupport.stream(
                    clients.spliterator(),
                    false)
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Client>> getAllSortedDescendingByFields(String... fields) {
        return CompletableFuture.supplyAsync(() -> {
            Sort sort = new Sort(Sort.Direction.DESC, fields);
            Iterable<Client> clients = ((SortingRepository<Long, Client>)repository).findAll(sort);
            return StreamSupport.stream(
                    clients.spliterator(),
                    false)
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<Client> getByID(Long aLong) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<Client> client = ((SortingRepository<Long, Client>)repository).findOne(aLong);
            if(client.isPresent())
                return client.get();
            throw new RuntimeException("Could not find client by given ID.");
        }, executorService);
    }
}
