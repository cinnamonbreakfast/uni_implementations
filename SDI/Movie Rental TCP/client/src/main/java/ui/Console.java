package ui;

import Domain.Client;
import Domain.Movie;
import Domain.Rental;
import service.ClientService;
import service.MovieService;
import service.RentalService;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Console {
    private ClientService clientService;
    private MovieService movieService;
    private RentalService rentalService;

    private static Scanner keyboard = new Scanner(System.in);

    public Console(ClientService clientService, MovieService movieService, RentalService rentalService) {
        this.clientService = clientService;
        this.movieService = movieService;
        this.rentalService = rentalService;
    }

    public void runConsole()
    {
        loopMenu();
    }

    public void addClient(String[] args) {
        try {
            Client client = new Client(args[3], args[4], args[5], Integer.parseInt(args[6]));
            client.setId(Long.parseLong(args[2]));
            this.clientService.addClient(client).thenAccept(
                    response -> System.out.println("Client added successfully")
            ).exceptionally(
                    error -> {
                        System.out.println(error.getMessage());
                        return null;
                    }
            );
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void addMovie(String[] args)
    {
        try
        {
            Movie movie = new Movie(args[3], args[4], Integer.parseInt(args[5]), Integer.parseInt(args[5]));
            movie.setId(Long.parseLong(args[2]));
            this.movieService.addMovie(movie).thenAccept(
                response -> System.out.println("Movie added successfully")
        ).exceptionally(
                error -> {
                    System.out.println(error.getMessage());
                    return null;
                }
        );

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void removeClient(String[] args)
    {
        try
        {
            this.clientService.removeClient(Long.parseLong(args[2])).thenAccept(
                    response -> System.out.println("Client removed successfully")
            ).exceptionally(
                    error -> {
                        System.out.println(error.getMessage());
                        return null;
                    }
            );
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void removeMovie(String[] args)
    {
        try
        {
            this.movieService.removeMovie(Long.parseLong(args[2])).thenAccept(
                    response -> System.out.println("Movie removed successfully")
            ).exceptionally(
                    error -> {
                        System.out.println(error.getMessage());
                        return null;
                    }
            );
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void updateClient(String[] args)
    {
        try {
            Client client = new Client(args[3], args[4], args[5], Integer.parseInt(args[6]));
            client.setId(Long.parseLong(args[2]));
            this.clientService.updateClient(client).thenAccept(
                    response -> System.out.println("Client updated successfully")
            ).exceptionally(
                    error -> {
                        System.out.println(error.getMessage());
                        return null;
                    }
            );
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void updateMovie(String[] args)
    {
        try {
            Movie movie = new Movie(args[3], args[4], Integer.parseInt(args[5]), Integer.parseInt(args[5]));
            movie.setId(Long.parseLong(args[2]));
            this.movieService.updateMovie(movie).thenAccept(
                    response -> System.out.println("Movie updated successfully")
            ).exceptionally(
                    error -> {
                        System.out.println(error.getMessage());
                        return null;
                    }
            );
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void filterClients(String[] args)
    {
        switch (args[2])
        {
            case "first":
                try
                {
                    this.clientService.filterClientsByFirstName(args[3]).thenAccept(
                        response -> System.out.println("Filter clients by first name successfully")
                    ).exceptionally(
                        error -> {
                            System.out.println(error.getMessage());
                            return null;
                        }
                );

                    this.clientService.filterClientsByFirstName(args[3]).get().forEach(System.out::println);
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "last":
                try {
                    this.clientService.filterClientsByLastName(args[3]).thenAccept(
                            response -> System.out.println("Filter clients by last name successfully")
                    ).exceptionally(
                            error -> {
                                System.out.println(error.getMessage());
                                return null;
                            }
                    );
                    this.clientService.filterClientsByLastName(args[3]).get().forEach(System.out::println);
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "age":
                try {
                    this.clientService.filterClientsByAge(Integer.parseInt(args[3])).thenAccept(
                            response -> System.out.println("Filter clients by age successfully")
                    ).exceptionally(
                            error -> {
                                System.out.println(error.getMessage());
                                return null;
                            }
                    );
                    this.clientService.filterClientsByAge(Integer.parseInt(args[3])).get().forEach(System.out::println);
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Unknown field. Use one of: first, last, age.");
        }
    }

    public void filterMovies(String[] args)
    {
        if(args[2].equals("name"))
        {
            this.movieService.filterMovieByName(args[3]).thenAccept(
                    response -> System.out.println("Filter movies by  name successfully")
            ).exceptionally(
                    error -> {
                        System.out.println(error.getMessage());
                        return null;
                    }
            );
        } else {
            System.out.println("Unknown field. Movies can only be filtered by name.");
        }
    }

    public void reportMovies(String[] args)
    {
        if(args[2].equals("mostRented"))
        {
            //System.out.println("Most rented movie:");
            this.rentalService.mostRentedMovie().thenAccept(
                    response -> System.out.println("Most rented movie computed successfully")
            ).exceptionally(
                    error -> {
                        System.out.println(error.getMessage());
                        return null;
                    }
            );
            System.out.println("Most rented movie:");
            System.out.println(this.rentalService.mostRentedMovie());
        }
    }

    public void reportClients(String[] args)
    {
        this.rentalService.mostLoyalCustomer().thenAccept(
                response -> System.out.println("Most loyal customer computed successfully")
        ).exceptionally(
                error -> {
                    System.out.println(error.getMessage());
                    return null;
                }
        );
        System.out.println("Most loyal customer:");
        System.out.println(this.rentalService.mostLoyalCustomer());
    }

    public  void rentMovie(String[] args)
    {
        try
        {
            this.rentalService.addRental(new Rental(Long.parseLong(args[1]), Long.parseLong(args[2]))).thenAccept(
                    response -> System.out.println("Rental action computed successfully")
            ).exceptionally(
                    error -> {
                        System.out.println(error.getMessage());
                        return null;
                    }
            );
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void returnMovie(String[] args)
    {
        try {
            this.rentalService.removeRental(Long.parseLong(args[1])).thenAccept(
                    response -> System.out.println("Remove rental successfully")
            ).exceptionally(
                    error -> {
                        System.out.println(error.getMessage());
                        return null;
                    }
            );

        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
        try
        {
            this.rentalService.removeRental(Long.parseLong(args[1])).thenAccept(
                    response -> System.out.println("Remove rental successfully")
            ).exceptionally(
                    error -> {
                        System.out.println(error.getMessage());
                        return null;
                    }
            );
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void displayClients(String[] args)
    {
        this.clientService.getAll().thenAccept(
                result -> {
                    result.forEach(System.out::println);
                }
        ).exceptionally(
                error -> {
                    System.out.println(error.getMessage());
                    return null;
                }
        );
    }


    public void displayMovies(String[] args)
    {
        this.movieService.getAll().thenAccept(
                result -> {
                    result.forEach(System.out::println);
                }
        ).exceptionally(
                error -> {
                    System.out.println(error.getMessage());
                    return null;
                }
        );
    }

    public void displayRentals(String[] args)
    {
        this.rentalService.getAll().thenAccept(
                result -> {
                    result.forEach(System.out::println);
                }
        ).exceptionally(
                error -> {
                    System.out.println(error.getMessage());
                    return null;
                }
        );
    }

    public void displayMoviesAsc(String[] args)
    {
        this.movieService.getAllSortedAscendingByFields(Arrays.copyOfRange(args, 3, args.length)).thenAccept(
                result -> {
                    result.forEach(System.out::println);
                }
        ).exceptionally(
                error -> {
                    System.out.println(error.getMessage());
                    return null;
                }
        );
    }

    public void displayRentalsAsc(String[] args)
    {
        this.rentalService.getAllSortedAscendingByFields(Arrays.copyOfRange(args, 3, args.length)).thenAccept(
                result -> {
                    result.forEach(System.out::println);
                }
        ).exceptionally(
                error -> {
                    System.out.println(error.getMessage());
                    return null;
                }
        );
    }

    public void displayClientsAsc(String[] args)
    {
        this.clientService.getAllSortedAscendingByFields(Arrays.copyOfRange(args, 3, args.length)).thenAccept(
                result -> {
                    result.forEach(System.out::println);
                }
        ).exceptionally(
                error -> {
                    System.out.println(error.getMessage());
                    return null;
                }
        );
    }

    public void displayMoviesDesc(String[] args)
    {
        this.movieService.getAllSortedDescendingByFields(Arrays.copyOfRange(args, 3, args.length)).thenAccept(
                result -> {
                    result.forEach(System.out::println);
                }
        ).exceptionally(
                error -> {
                    System.out.println(error.getMessage());
                    return null;
                }
        );
    }

    public void displayRentalDesc(String[] args)
    {
        this.rentalService.getAllSortedDescendingByFields(Arrays.copyOfRange(args, 3, args.length)).thenAccept(
                result -> {
                    result.forEach(System.out::println);
                }
        ).exceptionally(
                error -> {
                    System.out.println(error.getMessage());
                    return null;
                }
        );
    }



    public void displayClientsDesc(String[] args)
    {
        this.clientService.getAllSortedDescendingByFields(Arrays.copyOfRange(args, 3, args.length)).thenAccept(
                result -> {
                    result.forEach(System.out::println);
                }
        ).exceptionally(
                error -> {
                    System.out.println(error.getMessage());
                    return null;
                }
        );
    }




    public void loopMenu()
    {
        String cmd;

        while(true)
        {
            System.out.println("--- Movie rental cmd:");
            cmd = keyboard.nextLine();

            String[] cmdArgs = Arrays.stream( cmd.split( " " ) ).toArray(String[]::new);

            switch (cmdArgs[0])
            {
                case "add":
                    if(cmdArgs.length != 7)
                    {
                        System.out.println("Not enough arguments. Use: add [ID] [client/movie] [First] [Last] [Job] [Age].");
                        continue;
                    }
                    if(cmdArgs[1].equals("client"))
                    {
                        addClient(cmdArgs);
                    }
                    else if(cmdArgs[1].equals("movie")) {
                        addMovie(cmdArgs);
                    }
                    else {
                        System.out.println("Unknown entity '"+cmdArgs[1]+"'.");
                    }
                    break;
                case "remove":
                    if(cmdArgs.length != 3)
                    {
                        System.out.println("Not enough arguments. Use: remove client [ID].");
                        continue;
                    }
                    if(cmdArgs[1].equals("client")) {
                        removeClient(cmdArgs);
                    }
                    else if(cmdArgs[1].equals("movie")) {
                        removeMovie(cmdArgs);
                    }
                    else {
                        System.out.println("Unknown entity '"+cmdArgs[1]+"'.");
                    }
                    break;
                case "update":
                    if(cmdArgs.length != 7)
                    {
                        System.out.println("Not enough arguments. Use: update [client/movie] [First] [Last] [Job] [Age].");
                        continue;
                    }
                    if(cmdArgs[1].equals("client"))
                    {
                        updateClient(cmdArgs);
                    }
                    else if(cmdArgs[1].equals("movie")) {
                        updateMovie(cmdArgs);
                    }
                    else {
                        System.out.println("Unknown entity '"+cmdArgs[1]+"'.");
                    }

                    break;
                case "display":
                    if(!(cmdArgs.length % 2 == 0))
                    {
                        System.out.println("Not enough parameters. Use: display [clients/movies].");
                        continue;
                    }

                    if(cmdArgs[1].equals("clients"))
                    {
                        if(cmdArgs.length<3)
                            this.displayClients(cmdArgs);
                        else{
                        if(cmdArgs[2].equals("asc"))
                        {
                            this.displayClientsAsc(cmdArgs);
                        }
                        else if(cmdArgs[2].equals("desc")){
                            this.displayClientsDesc(cmdArgs);
                        }
                        else{
                            System.out.println("The direction param must be asc or desc");
                            continue;
                        }}


                    }
                    else if(cmdArgs[1].equals("movies"))
                    {
                        if(cmdArgs.length<3)
                            this.displayMovies(cmdArgs);
                        else {
                            if (cmdArgs[2].equals("asc")) {
                                this.displayMoviesAsc(cmdArgs);
                            } else if (cmdArgs[2].equals("desc")) {
                                this.displayMoviesDesc(cmdArgs);
                            } else {
                                System.out.println("The direction param must be asc or desc");
                                continue;
                            }
                        }

                    }
                    else if(cmdArgs[1].equals("rentals"))
                    {
                        if(cmdArgs.length<3)
                            this.displayRentals(cmdArgs);
                        else {
                        if(cmdArgs[2].equals("asc"))
                        {
                            this.displayRentalsAsc(cmdArgs);
                        }
                        else if(cmdArgs[2].equals("desc")){
                            this.displayRentalDesc(cmdArgs);
                        }
                        else{
                            System.out.println("The direction param must be asc or desc");
                            continue;
                        }}


                    }
                    else {
                        System.out.println("Unknown entity '"+cmdArgs[1]+"'.");
                    }

                    break;
                case "filter":
                    if(cmdArgs.length != 4)
                    {
                        System.out.println("Not enough parameters. Use: filter [client/movie] [first|last|age/name] [value].");
                        continue;
                    }
                    if(cmdArgs[1].equals("client"))
                    {
                        filterClients(cmdArgs);
                    }
                    else if(cmdArgs[1].equals("movie"))
                    {
                        filterMovies(cmdArgs);
                    }
                    else {
                        System.out.println("Unknown entity '"+cmdArgs[1]+"'.");
                    }

                    break;
                case "rent":
                    if(cmdArgs.length != 4)
                    {
                        System.out.println("Not enough parameters. Use: rent [Client ID] [Movie ID] [Days].");
                        continue;
                    }
                    else {
                        rentMovie(cmdArgs);
                    }

                    break;

                case "return":
                    if(cmdArgs.length != 2)
                    {
                        System.out.println("Not enough parameters. Use: return [rent ID].");
                        continue;
                    }
                    else {
                        returnMovie(cmdArgs);
                    }

                    break;
                case "report":
                    if(cmdArgs.length != 4)
                    {
                        System.out.println("Not enough parameters. Use: report [client/movie] [|mostRented]");
                        continue;
                    }
                    if(cmdArgs[1].equals("client"))
                    {
                        reportMovies(cmdArgs);
                    }
                    else if(cmdArgs[1].equals("movies"))
                    {
                        reportClients(cmdArgs);
                    }
                    else {
                        System.out.println("Unknown entity '"+cmdArgs[1]+"'.");
                    }

                    break;
                case "exit":
                    System.out.println("Exit application.");
                    return;
                default:
                    System.out.print("Unknown command.");
            }
        }
    }
}
