package service;

import Domain.Client;
import Domain.Validators.ValidatorException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ClientService extends Service<Long, Client> {
    CompletableFuture<Void> addClient(Client entity) throws ValidatorException, IOException;
    CompletableFuture<List<Client>> getAllClients(String... sort);
    CompletableFuture<List<Client>> filterClientsByFirstName(String name);
    CompletableFuture<List<Client>> filterClientsByLastName(String name);
    CompletableFuture<List<Client>> filterClientsByAge(int age);
    CompletableFuture<Void> removeClient(Long id);
    CompletableFuture<Void> updateClient(Client entity);
}
