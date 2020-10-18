import config.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import ui.Console;

@Import(Config.class)
public class MainClient {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("config");

        Console consoleUI = context.getBean(Console.class);
        consoleUI.addSensor();
    }
}
