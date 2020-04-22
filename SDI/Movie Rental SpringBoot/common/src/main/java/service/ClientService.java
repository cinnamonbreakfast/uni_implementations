package service;

import domain.Client;
import domain.exceptions.ValidatorException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ClientService extends Service<Long, Client> {
    void addClient(Client entity) throws ValidatorException, IOException;
    List<Client> getAllClients(String... sort);
    List<Client> filterClientsByFirstName(String name);
    List<Client> filterClientsByLastName(String name);
    List<Client> filterClientsByAge(int age);
    void removeClient(Long id);
    void updateClient(Client entity);
}
