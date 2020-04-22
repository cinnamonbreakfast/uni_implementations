package ui;

import domain.Client;
import domain.Movie;
import domain.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.ClientService;
import service.MovieService;
import service.RentalService;

import java.util.Arrays;
import java.util.Scanner;

@Component
public class Console {
    @Autowired
    private ClientService clientService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private RentalService rentalService;

    private static Scanner keyboard = new Scanner(System.in);

    public void runConsole()
    {
        loopMenu();
    }

    public void addClient(String[] args) {
        try {
            Client client = new Client(args[3], args[4], args[5], Integer.parseInt(args[6]));
            client.setId(Long.parseLong(args[2]));
            this.clientService.addClient(client);

            System.out.println("Client added.");
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
            this.movieService.addMovie(movie);

            System.out.println("Movie added.");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void removeClient(String[] args)
    {
        try
        {
            this.clientService.removeClient(Long.parseLong(args[2]));

            System.out.println("Client with id "+args[2]+" successfully removed.");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void removeMovie(String[] args)
    {
        try
        {
            this.movieService.removeMovie(Long.parseLong(args[2]));

            System.out.println("Movie with id "+args[2]+" successfully removed.");
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
            this.clientService.updateClient(client);

            System.out.println("Client updated.");
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
            this.movieService.updateMovie(movie);

            System.out.println("Movie updated.");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void filterClients(String[] args)
    {
        switch (args[2])
        {
            case "firstName":
                this.clientService.filterClientsByFirstName(args[3]).forEach(System.out::println);
                break;
            case "secondName":
                this.clientService.filterClientsByLastName(args[3]).forEach(System.out::println);
                break;
            case "age":
                this.clientService.filterClientsByAge(Integer.parseInt(args[3])).forEach(System.out::println);
                break;
            default:
                System.out.println("Unknown field. Use one of: first, last, age.");
        }
    }

    public void filterMovies(String[] args)
    {
        if(args[2].equals("name"))
        {
            this.movieService.filterMovieByName(args[3]);
        } else {
            System.out.println("Unknown field. Movies can only be filtered by name.");
        }
    }

    public void reportMovies(String[] args)
    {
        if(args[2].equals("mostRented"))
        {
            System.out.println("Most rented movie:");
            System.out.println(this.rentalService.mostRentedMovie());
        }
    }

    public void reportClients(String[] args)
    {
        System.out.println("Most loyal customer:");
        System.out.println(this.rentalService.mostLoyalCustomer());
    }

    public  void rentMovie(String[] args)
    {
        try
        {
            this.rentalService.addRental(new Rental(Long.parseLong(args[1]), Long.parseLong(args[2])));
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void returnMovie(String[] args)
    {
        try
        {
            this.rentalService.removeRental(Long.parseLong(args[1]));
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void displayClients()
    {
        try
        {
            this.clientService.getAll().forEach(System.out::println);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void displayClientsAsc(String[] args)
    {
        try
        {
            this.clientService.getAllSortedAscendingByFields(Arrays.copyOfRange(args, 3, args.length)).forEach(System.out::println);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void displayClientsDesc(String[] args)
    {
        try
        {
            this.clientService.getAllSortedDescendingByFields(Arrays.copyOfRange(args, 3, args.length)).forEach(System.out::println);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void displayMovies()
    {
        try
        {
            this.movieService.getAll().forEach(System.out::println);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void displayMoviesAsc(String[] args)
    {
        try
        {
            this.movieService.getAllSortedAscendingByFields(Arrays.copyOfRange(args, 3, args.length)).forEach(System.out::println);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void displayMoviesDesc(String[] args)
    {
        try
        {
            this.movieService.getAllSortedDescendingByFields(Arrays.copyOfRange(args, 3, args.length)).forEach(System.out::println);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void displayRentals()
    {
        try
        {
            this.rentalService.getAll().forEach(System.out::println);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void displayRentalsAsc(String[] args)
    {
        try
        {
            this.rentalService.getAllSortedAscendingByFields(Arrays.copyOfRange(args, 3, args.length)).forEach(System.out::println);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void displayRentalsDesc(String[] args)
    {
        try
        {
            this.rentalService.getAllSortedDescendingByFields(Arrays.copyOfRange(args, 3, args.length)).forEach(System.out::println);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void loopMenu()
    {
        String cmd;
        System.out.println("--- Movie rental cmd:");
        while(true)
        {
            cmd = keyboard.nextLine();

            String[] cmdArgs = Arrays.stream( cmd.split( " " ) ).toArray(String[]::new);

            switch (cmdArgs[0])
            {
                case "add":
                    if(cmdArgs.length != 7)
                    {
                        System.out.println("Not enough arguments. Use: add [client/movie] [First] [Last] [Job] [Age].");
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
                    System.out.println(cmdArgs.length);
                    if(!(cmdArgs.length >= 2))
                    {
                        System.out.println("Not enough parameters. Use: display [clients/movies].");
                        continue;
                    }

                    if(cmdArgs[1].equals("clients"))
                    {
                        if(cmdArgs.length == 2)
                        {
                            this.displayClients();
                        } else
                        if(cmdArgs[2].equals("asc"))
                        {
                            this.displayClientsAsc(cmdArgs);
                        } else if(cmdArgs[2].equals("desc"))
                        {
                            this.displayClientsDesc(cmdArgs);
                        } else {
                            System.out.println("Direction not specified.");
                            continue;
                        }
                    }
                    else if(cmdArgs[1].equals("movies"))
                    {
                        if(cmdArgs.length == 2)
                        {
                            this.displayMovies();
                        } else
                        if(cmdArgs[2].equals("asc"))
                        {
                            this.displayMoviesAsc(cmdArgs);
                        } else if(cmdArgs[2].equals("desc"))
                        {
                            this.displayMoviesDesc(cmdArgs);
                        } else {
                            System.out.println("Direction not specified.");
                            continue;
                        }
                    }
                    else if(cmdArgs[1].equals("rentals"))
                    {
                        if(cmdArgs.length == 2)
                        {
                            this.displayRentals();
                        } else
                        if(cmdArgs[2].equals("asc"))
                        {
                            this.displayRentalsAsc(cmdArgs);
                        } else if(cmdArgs[2].equals("desc"))
                        {
                            this.displayRentalsDesc(cmdArgs);
                        } else {
                            System.out.println("Direction not specified.");
                            continue;
                        }
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
