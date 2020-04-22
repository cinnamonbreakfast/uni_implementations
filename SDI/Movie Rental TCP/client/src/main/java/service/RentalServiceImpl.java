package service;

import Domain.Rental;
import Domain.Validators.ValidatorException;
import Message.Message;
import Message.MessageHeader;
import tcp.TcpClient;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class RentalServiceImpl implements RentalService {

    private ExecutorService executorService;
    private TcpClient tcpClient;

    public RentalServiceImpl(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Void> addRental(Rental entity) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("entity", entity));

            Message request = new Message(MessageHeader.RENTAL_ADD, body);
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
    public CompletableFuture<Void> removeRental(Long ID) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("id", ID));

            Message request = new Message(MessageHeader.RENTAL_REMOVE, body);
            Message response = tcpClient.sendAndReceive(request);
            if (response.getHeader().equals(MessageHeader.OK)) {
                return null;
            } else if (response.getHeader().equals(MessageHeader.ERROR)) {
                if (response.getBody().get(0).getKey().equals("message")) {
                    throw new RuntimeException((String) response.getBody().get(0).getValue());
                } else {
                    throw new RuntimeException("Unknown error occurred.");
                }

            } else {
                throw new RuntimeException("Transmission error. Received message is of unknown type.");
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<Long> mostRentedMovie() {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();

            Message request = new Message(MessageHeader.RENTAL_MOST_RENTED, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (Long)response.getBody().stream()
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
    public CompletableFuture<Long> mostLoyalCustomer() {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();

            Message request = new Message(MessageHeader.RENTAL_MOST_LOYAL_CUSTOMER, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (Long)response.getBody().stream()
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
    public CompletableFuture<List<Rental>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MessageHeader.RENTAL_GET_ALL, null);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Rental>)response.getBody().stream()
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
    public CompletableFuture<List<Rental>> getAllSortedAscendingByFields(String... fields) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("fields", fields));

            Message request = new Message(MessageHeader.RENTAL_SORT_ASC, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Rental>)response.getBody().stream()
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
    public CompletableFuture<List<Rental>> getAllSortedDescendingByFields(String... fields) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("fields", fields));

            Message request = new Message(MessageHeader.RENTAL_SORT_DESC, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Rental>)response.getBody().stream()
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
    public CompletableFuture<Rental> getByID(Long aLong) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("id", aLong));

            Message request = new Message(MessageHeader.RENTAL_GET_BY_ID, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (Rental)response.getBody().stream()
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
