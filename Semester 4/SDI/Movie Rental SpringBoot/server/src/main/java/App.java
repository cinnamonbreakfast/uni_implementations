import domain.Client;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.remoting.rmi.RmiServiceExporter;
import repository.database.ClientDBRepository;
import service.ClientService;
import service.ClientServiceImpl;

import java.rmi.RemoteException;

public class App {
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "config"
                );

        RmiServiceExporter clientRMI = (RmiServiceExporter) context.getBean("ClientService");
        try {
            clientRMI.afterPropertiesSet();
            System.out.println("Server start");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
