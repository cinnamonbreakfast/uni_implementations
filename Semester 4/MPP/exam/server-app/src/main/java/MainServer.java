import config.Config;
import config.CoreCFG;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import service.ServerController;

@ComponentScan({"service", "tcp", "converter", "domain", "repository"})
@EnableJpaRepositories("repository")
@EntityScan("domain")
@Import({Config.class})
public class MainServer {
    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("config");

        ServerController serverController = context.getBean(ServerController.class);
        serverController.linkHandlersToHeader();
        serverController.runServer();
    }
}