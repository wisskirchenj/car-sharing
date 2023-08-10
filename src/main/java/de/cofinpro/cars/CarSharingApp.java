package de.cofinpro.cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

import java.util.Properties;

@SpringBootApplication
public class CarSharingApp {

    public static void main(String[] args) {
        var app = new SpringApplication(CarSharingApp.class);
        Assert.state(args.length == 2 && "-databaseFileName".equals(args[0]),
                "Please provide option -databaseFileName <filename>");
        Properties properties = new Properties();
        properties.put("spring.datasource.url", "jdbc:h2:file:./src/main/resources/db/" + args[1]);

        app.setDefaultProperties(properties);
        app.run(args);
    }
}