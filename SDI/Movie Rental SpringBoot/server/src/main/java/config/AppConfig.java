package config;

import domain.Client;
import domain.validators.ClientValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import repository.database.ClientDBRepository;

import repository.database.orm.ClientMapper;
import service.ClientService;
import service.ClientServiceImpl;

import java.util.concurrent.Executors;

@Configuration
public class AppConfig {
    @Bean(name = "ClientService")
    RmiServiceExporter rmiClientServiceExporter()
    {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("ClientService");
        rmiServiceExporter.setRegistryPort(1099);
        rmiServiceExporter.setServiceInterface(ClientService.class);
        rmiServiceExporter.setService(clientService());
        return rmiServiceExporter;
    }

    @Bean
    ClientService clientService() {
        return new ClientServiceImpl();
    }

    @Bean
    ClientValidator clientValidator(){
        return new ClientValidator();
    }
    @Bean
    ClientMapper clientMapper()
    {
        return new ClientMapper();
    }

    @Bean
    ClientDBRepository clientRepository(){
        return new ClientDBRepository();
    }
}