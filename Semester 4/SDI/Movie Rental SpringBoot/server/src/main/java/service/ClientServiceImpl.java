package service;


import domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import repository.Repository;
import repository.SortingRepository;
import repository.sorting.Sort;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientServiceImpl implements ClientService {

    @Autowired
    private Repository<Long, Client> clientRepository;

    @Override
    public void addClient(Client entity) {
        try {
            Optional<Client> clients = clientRepository.save(entity);
            if(clients.isEmpty())
                throw new Exception("Could not memorise the client.");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Client> getAllClients(String... sort) {
        Sort sorted = new Sort(sort);
        Iterable<Client> clients = ((SortingRepository<Long, Client>)clientRepository).findAll(sorted);
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> filterClientsByFirstName(String name) {
        Sort sort = new Sort("firstName");
        Iterable<Client> clients = ((SortingRepository<Long, Client>)clientRepository).findAll(sort);
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .filter((e)-> e.getFirstName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> filterClientsByLastName(String name) {
        Sort sort = new Sort("secondName");
        Iterable<Client> clients = ((SortingRepository<Long, Client>)clientRepository).findAll(sort);
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .filter((e)-> e.getSecondName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> filterClientsByAge(int age) {
        Sort sort = new Sort("age");
        Iterable<Client> clients = ((SortingRepository<Long, Client>)clientRepository).findAll(sort);
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .filter((e)->e.getAge()==age)
                .collect(Collectors.toList());
    }

    @Override
    public void removeClient(Long id) {
        Optional<Client> client = ((SortingRepository<Long, Client>)clientRepository).delete(id);
        if(client.isEmpty())
            throw new RuntimeException("No client to delete.");
    }

    @Override
    public void updateClient(Client entity) {
        Optional<Client> client = ((SortingRepository<Long, Client>)clientRepository).update(entity);
        if(client.isEmpty())
            throw new RuntimeException("No client to update.");
    }

    @Override
    public List<Client> getAll() {
        Iterable<Client> clients = clientRepository.findAll();
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> getAllSortedAscendingByFields(String... fields) {
        Sort sort = new Sort(Sort.Direction.ASC, fields);
        Iterable<Client> clients = ((SortingRepository<Long, Client>)clientRepository).findAll(sort);
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Client> getAllSortedDescendingByFields(String... fields) {
        Sort sort = new Sort(Sort.Direction.DESC, fields);
        Iterable<Client> clients = ((SortingRepository<Long, Client>)clientRepository).findAll(sort);
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public Client getByID(Long aLong) {
        Optional<Client> client = ((SortingRepository<Long, Client>)clientRepository).findOne(aLong);
        if(client.isPresent())
            return client.get();
        throw new RuntimeException("Could not find client by given ID.");
    }
}
