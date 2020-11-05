import Domain.Validators.ClientValidator;
import Domain.Validators.MovieValidator;
import Domain.Validators.RentalValidator;
import repository.database.*;
import service.*;
import tcp.TcpServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String args[])
    {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        ClientValidator clientValidator = new ClientValidator();
        ClientDBRepository clientRepo = new ClientDBRepository(clientValidator);
        ClientService clientService = new ClientServiceImpl(clientRepo, executorService);

        MovieValidator movieValidator = new MovieValidator();
        MovieDBRepository movieRepo = new MovieDBRepository(movieValidator);
        MovieService movieService = new MovieServiceImpl(movieRepo, executorService);

        RentalValidator rentalValidator = new RentalValidator();
        RentalDBRepository rentalRepo = new RentalDBRepository(rentalValidator);
        RentalService rentalService = new RentalServiceImpl(rentalRepo, executorService);

        TcpServer tcpServer = new TcpServer(executorService);

        ServerController serverController = new ServerController(tcpServer, clientService, rentalService, movieService);
        serverController.linkHandlersToHeader();
        serverController.runServer();
    }
}
