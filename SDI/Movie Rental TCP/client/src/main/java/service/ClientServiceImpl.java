package service;

import Domain.Client;
import Domain.Validators.ValidatorException;
import Message.Message;
import Message.MessageHeader;
import tcp.TcpClient;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class ClientServiceImpl implements ClientService {
    private ExecutorService executorService;
    private TcpClient tcpClient;

    public ClientServiceImpl(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }


    @Override
    public CompletableFuture<Void> addClient(Client entity) throws ValidatorException, IOException {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("entity", entity));

            Message request = new Message(MessageHeader.CLIENT_ADD, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                return null;
            }
            else if(response.getHeader().equals(MessageHeader.ERROR))
            {
                if(response.getBody().get(0).getKey().equals("message"))
                {
                    throw new ValidatorException((String)response.getBody().get(0).getValue());
                }
                else
                {
                    throw new ValidatorException("Unknown error occurred.");
                }

            } else {
                throw new ValidatorException("Transmission error. Received message is of unknown type.");
            }
        }, executorService);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CompletableFuture<List<Client>> getAllClients(String... sort) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("fields", sort));

            Message request = new Message(MessageHeader.CLIENT_SORT_ASC, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Client>)response.getBody().stream()
                            .findFirst().orElseThrow(()->new Exception("No data to display.")).getValue();
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            else if(response.getHeader().equals(MessageHeader.ERROR))
            {
                if(response.getBody().get(0).getKey().equals("message"))
                {
                    throw new RuntimeException((String)response.getBody().get(0).getValue());
                }
                else
                {
                    throw new RuntimeException("Unknown error occurred.");
                }

            } else {
                throw new RuntimeException("Transmission error. Received message is of unknown type.");
            }
        }, executorService);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CompletableFuture<List<Client>> filterClientsByFirstName(String name) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("firstName", name));

            Message request = new Message(MessageHeader.CLIENT_FILTER_FIRSTNAME, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Client>)response.getBody().stream()
                            .findFirst().orElseThrow(()->new Exception("No data to display.")).getValue();
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            else if(response.getHeader().equals(MessageHeader.ERROR))
            {
                throw new RuntimeException((String)response.getBody().get(0).getValue());

            } else {
                throw new RuntimeException("Transmission error. Received message is of unknown type.");
            }
        }, executorService);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CompletableFuture<List<Client>> filterClientsByLastName(String name) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("lastName", name));

            Message request = new Message(MessageHeader.CLIENT_FILTER_LASTNAME, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Client>)response.getBody().stream()
                            .findFirst().orElseThrow(()->new Exception("No data to display.")).getValue();
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            else if(response.getHeader().equals(MessageHeader.ERROR))
            {
                if(response.getBody().get(0).getKey().equals("message"))
                {
                    throw new RuntimeException((String)response.getBody().get(0).getValue());
                }
                else
                {
                    throw new RuntimeException("Unknown error occurred.");
                }

            } else {
                throw new RuntimeException("Transmission error. Received message is of unknown type.");
            }
        }, executorService);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CompletableFuture<List<Client>> filterClientsByAge(int age) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("age", age));

            Message request = new Message(MessageHeader.CLIENT_FILTER_AGE, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Client>)response.getBody().stream()
                            .findFirst().orElseThrow(()->new Exception("No data to display.")).getValue();
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            else if(response.getHeader().equals(MessageHeader.ERROR))
            {
                if(response.getBody().get(0).getKey().equals("message"))
                {
                    throw new RuntimeException((String)response.getBody().get(0).getValue());
                }
                else
                {
                    throw new RuntimeException("Unknown error occurred.");
                }

            } else {
                throw new RuntimeException("Transmission error. Received message is of unknown type.");
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<Void> removeClient(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("id", id));

            Message request = new Message(MessageHeader.CLIENT_REMOVE, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                return null;
            }
            else if(response.getHeader().equals(MessageHeader.ERROR))
            {
                if(response.getBody().get(0).getKey().equals("message"))
                {
                    throw new RuntimeException((String)response.getBody().get(0).getValue());
                }
                else
                {
                    throw new RuntimeException("Unknown error occurred.");
                }

            } else {
                throw new RuntimeException("Transmission error. Received message is of unknown type.");
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<Void> updateClient(Client entity) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("entity", entity));

            Message request = new Message(MessageHeader.CLIENT_UPDATE, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                return null;
            }
            else if(response.getHeader().equals(MessageHeader.ERROR))
            {
                if(response.getBody().get(0).getKey().equals("message"))
                {
                    throw new RuntimeException((String)response.getBody().get(0).getValue());
                }
                else
                {
                    throw new RuntimeException("Unknown error occurred.");
                }

            } else {
                throw new RuntimeException("Transmission error. Received message is of unknown type.");
            }
        }, executorService);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CompletableFuture<List<Client>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MessageHeader.CLIENT_GET_ALL, null);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Client>)(response.getBody().stream()
                            .findFirst()
                            .orElseThrow(()->new Exception("No data to display."))
                            .getValue());
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            else if(response.getHeader().equals(MessageHeader.ERROR))
            {
                throw new RuntimeException((String)response.getBody().get(0).getValue());
            } else {
                throw new RuntimeException("Transmission error. Received message is of unknown type.");
            }
        }, executorService);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CompletableFuture<List<Client>> getAllSortedAscendingByFields(String... fields) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("fields", fields));

            Message request = new Message(MessageHeader.CLIENT_SORT_ASC, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Client>)response.getBody().stream()
                            .findFirst().orElseThrow(()->new Exception("No data to display.")).getValue();
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            else if(response.getHeader().equals(MessageHeader.ERROR))
            {
                if(response.getBody().get(0).getKey().equals("message"))
                {
                    throw new RuntimeException((String)response.getBody().get(0).getValue());
                }
                else
                {
                    throw new RuntimeException("Unknown error occurred.");
                }

            } else {
                throw new RuntimeException("Transmission error. Received message is of unknown type.");
            }
        }, executorService);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CompletableFuture<List<Client>> getAllSortedDescendingByFields(String... fields) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("fields", fields));

            Message request = new Message(MessageHeader.CLIENT_SORT_DESC, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Client>)response.getBody().stream()
                            .findFirst().orElseThrow(()->new Exception("No data to display.")).getValue();
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            else if(response.getHeader().equals(MessageHeader.ERROR))
            {
                if(response.getBody().get(0).getKey().equals("message"))
                {
                    throw new RuntimeException((String)response.getBody().get(0).getValue());
                }
                else
                {
                    throw new RuntimeException("Unknown error occurred.");
                }

            } else {
                throw new RuntimeException("Transmission error. Received message is of unknown type.");
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<Client> getByID(Long aLong) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("id", aLong));

            Message request = new Message(MessageHeader.CLIENT_GET_BY_ID, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (Client)response.getBody().stream()
                            .findFirst().orElseThrow(()->new Exception("No data to display.")).getValue();
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            else if(response.getHeader().equals(MessageHeader.ERROR))
            {
                if(response.getBody().get(0).getKey().equals("message"))
                {
                    throw new RuntimeException((String)response.getBody().get(0).getValue());
                }
                else
                {
                    throw new RuntimeException("Unknown error occurred.");
                }

            } else {
                throw new RuntimeException("Transmission error. Received message is of unknown type.");
            }
        }, executorService);
    }
}
