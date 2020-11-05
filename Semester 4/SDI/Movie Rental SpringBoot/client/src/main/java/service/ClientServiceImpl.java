package service;

import domain.Client;
import domain.exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientService clientService;

    @Override
    public void addClient(Client entity) throws ValidatorException, IOException {
        clientService.addClient(entity);
    }

    @Override
    public List<Client> getAllClients(String... sort) {
        return clientService.getAllClients(sort);
    }

    @Override
    public List<Client> filterClientsByFirstName(String name) {
        return clientService.filterClientsByFirstName(name);
    }

    @Override
    public List<Client> filterClientsByLastName(String name) {
        return clientService.filterClientsByLastName(name);
    }

    @Override
    public List<Client> filterClientsByAge(int age) {
        return clientService.filterClientsByAge(age);
    }

    @Override
    public void removeClient(Long id) {
        clientService.removeClient(id);
    }

    @Override
    public void updateClient(Client entity) {
        clientService.updateClient(entity);
    }

    @Override
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @Override
    public List<Client> getAllSortedAscendingByFields(String... fields) {
        return clientService.getAllSortedAscendingByFields(fields);
    }

    @Override
    public List<Client> getAllSortedDescendingByFields(String... fields) {
        return clientService.getAllSortedDescendingByFields(fields);
    }

    @Override
    public Client getByID(Long aLong) {
        return clientService.getByID(aLong);
    }
}
