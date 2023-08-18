package de.cofinpro.cars.controller;

import de.cofinpro.cars.io.ConsolePrinter;
import de.cofinpro.cars.persistence.Company;
import de.cofinpro.cars.service.CarService;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Scanner;

@Controller
public class CompanyMenu extends AbstractMenuController<CompanyMenu.Choice> {

    private final CarService carService;
    @Setter
    private Company company;

    public CompanyMenu(Scanner scanner,
                       ConsolePrinter printer,
                       CarService carService) {
        super(printer, scanner);
        this.carService = carService;
    }

    @Override
    protected String getMenuText() {
        return """
                1. Car list
                2. Create a car
                0. Back""";
    }

    @Override
    protected Runnable getMenuAction(Choice choice) {
        return Map.<Choice, Runnable>of(
                Choice.LIST_CARS, this::listCars,
                Choice.CREATE_CAR, this::createCar
        ).get(choice);
    }

    @Override
    protected Choice getExitChoice() {
        return Choice.EXIT;
    }

    private void createCar() {
        printer.printInfo("Enter the car name:");
        var name = scanner.nextLine();
        carService.createCar(name, company);
        printer.printInfo("The car was added!\n");
    }

    private void listCars() {
        var cars = carService.getCars(company.getId());
        printer.printCarList(cars);
        printer.printInfo("");
    }

    protected enum Choice {
        EXIT,
        LIST_CARS,
        CREATE_CAR
    }
}
