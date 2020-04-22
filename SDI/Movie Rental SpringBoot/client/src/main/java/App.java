import domain.Client;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import service.ClientService;
import service.ClientServiceImpl;
import ui.Console;

import java.rmi.RemoteException;

public class App {
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "config"
                );

        Console console = (Console) context.getBean("appConsole");
        console.runConsole();

    }
}
