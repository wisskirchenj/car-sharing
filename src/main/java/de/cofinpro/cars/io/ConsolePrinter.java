package de.cofinpro.cars.io;

import de.cofinpro.cars.persistence.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Slf4J wrapping printer class, accepting input to print to the console (stdout)
 */
@Slf4j
@Component
public class ConsolePrinter {

    public void printInfo(String message) {
        log.info(message);
    }

    public void printCompanyList(List<Company> companies) {
        if (companies.isEmpty()) {
            printInfo("The company list is empty!");
        } else {
            companies.forEach(company -> printInfo("%d. %s".formatted(company.getId(), company.getName())));
        }
        printInfo("");
    }
}
