import service.*;
import tcp.TcpClient;
import ui.Console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String args[])
    {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpClient tcpClient = new TcpClient();

        ClientService clientService = new ClientServiceImpl(executorService, tcpClient);
        MovieService movieService = new MovieServiceImpl(executorService, tcpClient);
        RentalService rentalService = new RentalServiceImpl(executorService, tcpClient);
        Console console = new Console(clientService, movieService, rentalService);

        console.runConsole();
    }
}
