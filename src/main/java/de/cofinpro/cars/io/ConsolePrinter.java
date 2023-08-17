package de.cofinpro.cars.io;

import de.cofinpro.cars.persistence.Car;
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
        companies.forEach(company -> printInfo("%d. %s %s".formatted(company.getId(), company.getName(),
                company.hashCode())));
    }

    public void printCarList(List<Car> cars) {
        if (cars.isEmpty()) {
            printInfo("The car list is empty!");
            return;
        }
        for (int i = 0; i < cars.size(); i++) {
            printInfo("%d. %s %s".formatted(i + 1, cars.get(i).getName(), cars.get(i).getCompany().hashCode()));
        }
        printInfo("");
    }
}
