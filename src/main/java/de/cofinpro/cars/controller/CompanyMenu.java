package de.cofinpro.cars.controller;

import de.cofinpro.cars.io.ConsolePrinter;
import de.cofinpro.cars.persistence.Company;
import de.cofinpro.cars.service.CarService;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class CompanyMenu {

    private final CarService carService;
    private final Scanner scanner;
    private final ConsolePrinter printer;
    @Setter
    private Company company;

    public CompanyMenu(CarService carService,
                       Scanner scanner, 
                       ConsolePrinter printer) {
        this.carService = carService;
        this.scanner = scanner;
        this.printer = printer;
    }

    public void run() {
        printMenu();
        var choice = Integer.parseInt(scanner.nextLine());
        while (choice != 0) {
            switch (choice) {
                case 1 -> listCars();
                case 2 -> createCar();
                default -> throw new IllegalStateException("invalid choice");
            }
            printMenu();
            choice = Integer.parseInt(scanner.nextLine());
        }
    }

    private void createCar() {
        printer.printInfo("Enter the car name:");
        var name = scanner.nextLine();
        carService.createCar(name, company);
        printer.printInfo("The car was added!\n");
    }

    private void listCars() {
        var cars = carService.listCars(company.getId());
        printer.printCarList(cars);
    }

    private void printMenu() {
        printer.printInfo("""
                1. Car list
                2. Create a car
                0. Back
                """);
    }
}
