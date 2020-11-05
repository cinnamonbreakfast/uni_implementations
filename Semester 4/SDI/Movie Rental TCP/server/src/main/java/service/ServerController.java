package service;


import Domain.Client;
import Domain.Entity;
import Domain.Movie;
import Domain.Rental;
import Message.Message;
import Message.MessageHeader;
import tcp.TcpServer;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServerController {
    TcpServer tcpServer;
    ClientService clientService;
    RentalService rentalService;
    MovieService movieService;

    public ServerController(TcpServer tcpServer, ClientService clientService, RentalService rentalService, MovieService movieService) {
        this.tcpServer = tcpServer;
        this.clientService = clientService;
        this.rentalService = rentalService;
        this.movieService = movieService;
    }

    public Entity<Long> handleMessageBody(List<Map.Entry<String, Object>> perks)
    {
        return null;
    }

    public Message ok(String field, String msg)
    {
        Message response = new Message(MessageHeader.OK, new ArrayList<>());
        response.getBody().add(new AbstractMap.SimpleEntry<>(field, msg));
        return response;
    }

    public Message error(String field, String msg)
    {
        Message response = new Message(MessageHeader.ERROR, new ArrayList<>());
        response.getBody().add(new AbstractMap.SimpleEntry<>(field, msg));
        return response;
    }

    public void linkHandlersToHeader()
    {
        this.tcpServer.addHandler(MessageHeader.CLIENT_ADD,
            request -> {
                try {
                    return this.clientService.addClient(
                            (Client)request.getBody().get(0).getValue()
                    ).thenApply(
                            client -> ok("message", "Client added.")
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (IOException e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.CLIENT_GET_BY_ID,
            request -> this.clientService.getByID(
                    (Long)request.getBody().get(0).getValue()
            ).thenApply(
                    client -> {
                        Message response = new Message(MessageHeader.OK, new ArrayList<>());
                        response.getBody().add(new AbstractMap.SimpleEntry<>("client", client));
                        return response;
                    }
            ).exceptionally(
                    err -> error("message", err.getMessage())
            ).join()
        );

        this.tcpServer.addHandler(MessageHeader.CLIENT_GET_ALL,
                request ->
                this.clientService.getAll()
                .thenApply(
                        clients -> {
                            Message response = new Message(MessageHeader.OK, new ArrayList<>());
                            response.getBody().add(new AbstractMap.SimpleEntry<>("clients", clients));
                            return response;
                        }
                )
                .exceptionally(
                        e -> error("message", e.getMessage())
                ).join()
            );

        this.tcpServer.addHandler(MessageHeader.CLIENT_GET_ALL_SORTBY,
            request -> this.clientService.getAllClients(
                        (String[])request.getBody().get(0).getValue()
            ).thenApply(
                    clients -> {
                        Message response = new Message(MessageHeader.OK, new ArrayList<>());
                        response.getBody().add(new AbstractMap.SimpleEntry<>("clients", clients));
                        return response;
                    }
            ).exceptionally(
                    e -> error("message", e.getMessage())
            ).join()
        );

        this.tcpServer.addHandler(MessageHeader.CLIENT_FILTER_FIRSTNAME,
            request -> this.clientService.filterClientsByFirstName(
                    (String)request.getBody().get(0).getValue()
            ).thenApply(
                    clients -> {
                        Message response = new Message(MessageHeader.OK, new ArrayList<>());
                        response.getBody().add(new AbstractMap.SimpleEntry<>("clients", clients));
                        return response;
                    }
            ).exceptionally(
                    e -> error("message", e.getMessage())
            ).join()
        );

        this.tcpServer.addHandler(MessageHeader.CLIENT_FILTER_LASTNAME,
            request -> this.clientService.filterClientsByLastName(
                    (String)request.getBody().get(0).getValue()
            ).thenApply(
                    clients -> {
                        Message response = new Message(MessageHeader.OK, new ArrayList<>());
                        response.getBody().add(new AbstractMap.SimpleEntry<>("clients", clients));
                        return response;
                    }
            ).exceptionally(
                    e -> error("message", e.getMessage())
            ).join()
        );

        this.tcpServer.addHandler(MessageHeader.CLIENT_FILTER_AGE,
            request -> this.clientService.filterClientsByAge(
                    (int)request.getBody().get(0).getValue()
            ).thenApply(
                    clients -> {
                        Message response = new Message(MessageHeader.OK, new ArrayList<>());
                        response.getBody().add(new AbstractMap.SimpleEntry<>("clients", clients));
                        return response;
                    }
            ).exceptionally(
                    e -> error("message", e.getMessage())
            ).join()
        );

        this.tcpServer.addHandler(MessageHeader.CLIENT_SORT_ASC,
            request -> this.clientService.getAllSortedAscendingByFields(
                    (String[])request.getBody().get(0).getValue()
            ).thenApply(
                    clients -> {
                        Message response = new Message(MessageHeader.OK, new ArrayList<>());
                        response.getBody().add(new AbstractMap.SimpleEntry<>("clients", clients));
                        return response;
                    }
            ).exceptionally(
                    e -> error("message", e.getMessage())
            ).join()
        );

        this.tcpServer.addHandler(MessageHeader.CLIENT_SORT_DESC,
            request -> this.clientService.getAllSortedDescendingByFields(
                    (String[])request.getBody().get(0).getValue()
            ).thenApply(
                    clients -> {
                        Message response = new Message(MessageHeader.OK, new ArrayList<>());
                        response.getBody().add(new AbstractMap.SimpleEntry<>("clients", clients));
                        return response;
                    }
            ).exceptionally(
                    e -> error("message", e.getMessage())
            ).join()
        );

        this.tcpServer.addHandler(MessageHeader.CLIENT_REMOVE,
            request -> {
                try {
                    return this.clientService.removeClient(
                            (Long)request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> ok("message", "Client removed.")
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.CLIENT_UPDATE,
            request -> {
                try {
                    return this.clientService.updateClient(
                            (Client)request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> ok("message", "Client updated.")
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.MOVIE_ADD,
            request -> {
                try {
                    return this.movieService.addMovie(
                            (Movie)request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> ok("message", "Movie added.")
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.MOVIE_GET_BY_ID,
            request -> {
                try {
                    return this.movieService.getByID(
                            (Long)request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> {
                                Message resp = new Message(MessageHeader.OK, new ArrayList<>());
                                resp.getBody().add(new AbstractMap.SimpleEntry<>("movie", response));
                                return resp;
                            }
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.MOVIE_GET_ALL,
            request -> {
                try {
                    return this.movieService.getAll()
                    .thenApply(
                            response -> {
                                Message resp = new Message(MessageHeader.OK, new ArrayList<>());
                                resp.getBody().add(new AbstractMap.SimpleEntry<>("movies", response));
                                return resp;
                            }
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.MOVIE_GET_ALL_SORTBY,
            request -> {
                try {
                    return this.movieService.getAllMovies(
                            (String[])request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> {
                                Message resp = new Message(MessageHeader.OK, new ArrayList<>());
                                resp.getBody().add(new AbstractMap.SimpleEntry<>("movies", response));
                                return resp;
                            }
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.MOVIE_FILTER_NAME,
            request -> {
                try {
                    return this.movieService.filterMovieByName(
                            (String)request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> {
                                Message resp = new Message(MessageHeader.OK, new ArrayList<>());
                                resp.getBody().add(new AbstractMap.SimpleEntry<>("movies", response));
                                return resp;
                            }
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.MOVIE_FILTER_DESCRIPTION,
            request -> {
                try {
                    return this.movieService.filterMovieByDescription(
                            (String)request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> {
                                Message resp = new Message(MessageHeader.OK, new ArrayList<>());
                                resp.getBody().add(new AbstractMap.SimpleEntry<>("movies", response));
                                return resp;
                            }
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );
        this.tcpServer.addHandler(MessageHeader.MOVIE_FILTER_RATING,
            request -> {
                try {
                    return this.movieService.filterMovieByRating(
                            (Integer)request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> {
                                Message resp = new Message(MessageHeader.OK, new ArrayList<>());
                                resp.getBody().add(new AbstractMap.SimpleEntry<>("movies", response));
                                return resp;
                            }
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.MOVIE_FILTER_PRICE,
            request -> {
                try {
                    return this.movieService.filterMovieByPrice(
                            (Integer) request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> {
                                Message resp = new Message(MessageHeader.OK, new ArrayList<>());
                                resp.getBody().add(new AbstractMap.SimpleEntry<>("movies", response));
                                return resp;
                            }
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.MOVIE_SORT_ASC,
            request -> {
                try {
                    return this.movieService.getAllSortedAscendingByFields(
                            (String[]) request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> {
                                Message resp = new Message(MessageHeader.OK, new ArrayList<>());
                                resp.getBody().add(new AbstractMap.SimpleEntry<>("movies", response));
                                return resp;
                            }
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.MOVIE_SORT_DESC,
            request -> {
                try {
                    return this.movieService.getAllSortedDescendingByFields(
                            (String[]) request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> {
                                Message resp = new Message(MessageHeader.OK, new ArrayList<>());
                                resp.getBody().add(new AbstractMap.SimpleEntry<>("movies", response));
                                return resp;
                            }
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.MOVIE_REMOVE,
            request -> {
                try {
                    return this.movieService.removeMovie(
                            (Long)request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> ok("message", "Movie removed.")
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.MOVIE_UPDATE,
            request -> {
                try {
                    return this.movieService.updateMovie(
                            (Movie)request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> ok("message", "Movie updated.")
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.RENTAL_ADD,
            request -> {
                try {
                    return this.rentalService.addRental(
                            (Rental) request.getBody().get(0).getValue()
                    ).thenApply(
                            client -> ok("message", "Rental added.")
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (IOException e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.RENTAL_GET_BY_ID,
            request -> this.rentalService.getByID(
                    (Long)request.getBody().get(0).getValue()
            ).thenApply(
                    client -> {
                        Message response = new Message(MessageHeader.OK, new ArrayList<>());
                        response.getBody().add(new AbstractMap.SimpleEntry<>("rental", client));
                        return response;
                    }
            ).exceptionally(
                    err -> error("message", err.getMessage())
            ).join()
        );

        this.tcpServer.addHandler(MessageHeader.RENTAL_GET_ALL,
            request ->
                this.rentalService.getAll()
                    .thenApply(
                        rentals -> {
                            Message response = new Message(MessageHeader.OK, new ArrayList<>());
                            response.getBody().add(new AbstractMap.SimpleEntry<>("rentals", rentals));
                            return response;
                        }
                    )
                    .exceptionally(
                            e -> error("message", e.getMessage())
                    ).join()
        );

        this.tcpServer.addHandler(MessageHeader.RENTAL_REMOVE,
            request -> {
                try {
                    return this.rentalService.removeRental(
                            (Long)request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> ok("message", "Rental removed.")
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.RENTAL_MOST_RENTED,
            request -> {
                try {
                    return this.rentalService.mostRentedMovie()
                    .thenApply(
                            response -> ok("message", response.toString())
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.RENTAL_MOST_LOYAL_CUSTOMER,
            request -> {
                try {
                    return this.rentalService.mostLoyalCustomer()
                            .thenApply(
                                    response -> ok("message", response.toString())
                            ).exceptionally(
                                    err -> error("message", err.getMessage())
                            ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.RENTAL_SORT_ASC,
            request -> {
                try {
                    return this.rentalService.getAllSortedAscendingByFields(
                            (String[]) request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> {
                                Message resp = new Message(MessageHeader.OK, new ArrayList<>());
                                resp.getBody().add(new AbstractMap.SimpleEntry<>("rentals", response));
                                return resp;
                            }
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );

        this.tcpServer.addHandler(MessageHeader.RENTAL_SORT_DESC,
            request -> {
                try {
                    return this.rentalService.getAllSortedDescendingByFields(
                            (String[]) request.getBody().get(0).getValue()
                    ).thenApply(
                            response -> {
                                Message resp = new Message(MessageHeader.OK, new ArrayList<>());
                                resp.getBody().add(new AbstractMap.SimpleEntry<>("rentals", response));
                                return resp;
                            }
                    ).exceptionally(
                            err -> error("message", err.getMessage())
                    ).join();
                } catch (Exception e) {
                    return error("message", e.getMessage());
                }
            }
        );
    }

    public void runServer()
    {
        linkHandlersToHeader();
        try
        {
            System.out.println("Server started.");
            tcpServer.startServer();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
