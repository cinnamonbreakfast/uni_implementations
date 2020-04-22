package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import service.*;
import ui.Console;

@Configuration
@PropertySource("classpath:client.properties")
public class AppConfig {
    @Value("${rmi.url}") private String rmiURL;
    @Value("${rmi.port}") private String rmiPort;

    @Bean(name="ClientService")
    RmiProxyFactoryBean rmiPFBClient() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(ClientService.class);
        rmiProxyFactoryBean.setServiceUrl(rmiURL+":"+rmiPort+"/ClientService");
        return rmiProxyFactoryBean;
    }

    @Bean(name="MovieService")
    RmiProxyFactoryBean rmiPFBMovie() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(MovieService.class);
        rmiProxyFactoryBean.setServiceUrl(rmiURL+":"+rmiPort+"/MovieService");
        return rmiProxyFactoryBean;
    }

    @Bean(name="RentalService")
    RmiProxyFactoryBean rmiPFBRental() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(RentalService.class);
        rmiProxyFactoryBean.setServiceUrl(rmiURL+":"+rmiPort+"/RentalService");
        return rmiProxyFactoryBean;
    }

    @Bean
    ClientServiceImpl clientService(){
        return new ClientServiceImpl();
    }

    @Bean
    MovieServiceImpl movieService(){
        return new MovieServiceImpl();
    }

    @Bean
    RentalServiceImpl rentalService(){
        return new RentalServiceImpl();
    }

    @Bean
    Console appConsole() { return new Console(); }
}
