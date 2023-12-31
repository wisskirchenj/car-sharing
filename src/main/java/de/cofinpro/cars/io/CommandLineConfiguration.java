package de.cofinpro.cars.io;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Configuration class, offering (singleton) beans as the stdin-scanner or date-formatter.
 */
@Configuration
public class CommandLineConfiguration {

    @Bean
    public Scanner getStdinScanner() {
        return new Scanner(System.in);
    }

    @Bean
    public DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("yyyy.MM.dd");
    }
}
