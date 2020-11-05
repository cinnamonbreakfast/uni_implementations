package service;

import Domain.Movie;
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

public class MovieServiceImpl implements MovieService {

    private ExecutorService executorService;
    private TcpClient tcpClient;

    public MovieServiceImpl(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Void> addMovie(Movie movie) throws ValidatorException, IOException {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("entity", movie));

            Message request = new Message(MessageHeader.MOVIE_ADD, body);
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
    public CompletableFuture<List<Movie>> getAllMovies(String... sort) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("fields", sort));

            Message request = new Message(MessageHeader.MOVIE_GET_ALL_SORTBY, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Movie>)response.getBody().stream()
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
    public CompletableFuture<List<Movie>> filterMovieByName(String s) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("name", s));

            Message request = new Message(MessageHeader.MOVIE_FILTER_NAME, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Movie>)response.getBody().stream()
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
    public CompletableFuture<List<Movie>> filterMovieByDescription(String d) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("description", d));

            Message request = new Message(MessageHeader.MOVIE_FILTER_DESCRIPTION, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Movie>)response.getBody().stream()
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
    public CompletableFuture<List<Movie>> filterMovieByPrice(int p) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("price", p));

            Message request = new Message(MessageHeader.MOVIE_FILTER_PRICE, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Movie>)response.getBody().stream()
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
    public CompletableFuture<List<Movie>> filterMovieByRating(int r) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("rating", r));

            Message request = new Message(MessageHeader.MOVIE_FILTER_RATING, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Movie>)response.getBody().stream()
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
    public CompletableFuture<Void> removeMovie(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("id", id));

            Message request = new Message(MessageHeader.MOVIE_REMOVE, body);
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
    public CompletableFuture<Void> updateMovie(Movie entity) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("entity", entity));

            Message request = new Message(MessageHeader.MOVIE_UPDATE, body);
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
    public CompletableFuture<List<Movie>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MessageHeader.MOVIE_GET_ALL, null);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Movie>)response.getBody().stream()
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
    public CompletableFuture<List<Movie>> getAllSortedAscendingByFields(String... fields) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("fields", fields));

            Message request = new Message(MessageHeader.MOVIE_SORT_ASC, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Movie>)response.getBody().stream()
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
    public CompletableFuture<List<Movie>> getAllSortedDescendingByFields(String... fields) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("fields", fields));

            Message request = new Message(MessageHeader.CLIENT_SORT_DESC, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (List<Movie>)response.getBody().stream()
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
    public CompletableFuture<Movie> getByID(Long aLong) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("id", aLong));

            Message request = new Message(MessageHeader.MOVIE_GET_BY_ID, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                try {
                    return (Movie)response.getBody().stream()
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
