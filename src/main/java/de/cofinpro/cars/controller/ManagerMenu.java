package de.cofinpro.cars.controller;

import de.cofinpro.cars.io.ConsolePrinter;
import de.cofinpro.cars.service.CompanyService;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class ManagerMenu {

    private final CompanyService companyService;
    private final ConsolePrinter printer;
    private final Scanner scanner;

    public ManagerMenu(CompanyService companyService, ConsolePrinter printer, Scanner scanner) {
        this.companyService = companyService;
        this.printer = printer;
        this.scanner = scanner;
    }

    public void run() {
        printMenu();
        var choice = Integer.parseInt(scanner.nextLine());
        while (choice != 0) {
            switch (choice) {
                case 1 -> listCompanies();
                case 2 -> createCompany();
                default -> throw new IllegalStateException("invalid choice");
            }
            printMenu();
            choice = Integer.parseInt(scanner.nextLine());
        }
    }

    private void createCompany() {
        printer.printInfo("Enter the company name:");
        var name = scanner.nextLine();
        companyService.createCompany(name);
        printer.printInfo("The company was created!");
    }

    private void listCompanies() {
        printer.printCompanyList(companyService.listCompanies());
    }

    private void printMenu() {
        printer.printInfo("""
                1. Company list
                2. Create a company
                0. Back
                """);
    }
}
