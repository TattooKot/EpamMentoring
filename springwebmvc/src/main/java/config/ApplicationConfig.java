package config;

import controller.Controller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = "controller")
public class ApplicationConfig {

    @Bean
    public Controller cnt(){
        return new Controller();
    }
}
