package de.cofinpro.cars.controller;

import de.cofinpro.cars.io.ConsolePrinter;
import de.cofinpro.cars.persistence.Customer;
import de.cofinpro.cars.service.CarService;
import de.cofinpro.cars.service.CompanyService;
import de.cofinpro.cars.service.CustomerService;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

@Controller
public class CustomerMenu extends AbstractMenuController<CustomerMenu.Choice> {

    public static final String NO_CAR_RENT = "You didn't rent a car!\n";
    private final CustomerService customerService;
    private final CompanyService companyService;
    private final CarService carService;
    @Setter
    private Customer customer;

    protected CustomerMenu(ConsolePrinter consolePrinter,
                           Scanner scanner,
                           CustomerService customerService,
                           CompanyService companyService,
                           CarService carService) {
        super(consolePrinter, scanner);
        this.customerService = customerService;
        this.companyService = companyService;
        this.carService = carService;
    }

    @Override
    protected String getMenuText() {
        return """
        1. Rent a car
        2. Return a rented car
        3. My rented car
        0. Back""";
    }

    @Override
    protected Runnable getMenuAction(Choice choice) {
        return Map.<Choice, Runnable>of(
                Choice.RENT, this::rentCar,
                Choice.RETURN, this::returnCar,
                Choice.SHOW, this::showCar
        ).get(choice);
    }

    @Override
    protected Choice getExitChoice() {
        return Choice.EXIT;
    }

    private void rentCar() {
        if (Objects.nonNull(customer.getRentedCar())) {
            printer.printInfo("You've already rented a car!\n");
            return;
        }
        var companies = companyService.getCompanies();
        var index = chooseFromList(companies, "company");
        var companySelected = companies.get(index - 1);
        var cars = carService.getAvailableCars(companySelected.getId());
        index = chooseFromList(cars, "car");
        if (index > 0) {
            var car = cars.get(index - 1);
            customerService.rentCar(customer, car);
            printer.printInfo("You rented '{}'\n", car.getName());
        }
    }

    private void returnCar() {
        if (Objects.isNull(customer.getRentedCar())) {
            printer.printInfo(NO_CAR_RENT);
            return;
        }
        customerService.returnCar(customer);
        printer.printInfo("You've returned a rented car!\n");
    }

    private void showCar() {
        if (Objects.isNull(customer.getRentedCar())) {
            printer.printInfo(NO_CAR_RENT);
            return;
        }
        printer.printInfo("Your rented car:");
        printer.printInfo(customer.getRentedCar().getName());
        printer.printInfo("Company:");
        printer.printInfo("{}\n", customer.getRentedCar().getCompany().getName());
    }

    protected enum Choice {
        EXIT,
        RENT,
        RETURN,
        SHOW
    }
}
