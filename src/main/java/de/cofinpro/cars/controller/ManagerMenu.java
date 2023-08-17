package de.cofinpro.cars.controller;

import de.cofinpro.cars.io.ConsolePrinter;
import de.cofinpro.cars.persistence.Company;
import de.cofinpro.cars.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Scanner;

@Controller
public class ManagerMenu {

    private final CompanyService companyService;
    private final CompanyMenu companyMenu;
    private final ConsolePrinter printer;
    private final Scanner scanner;

    public ManagerMenu(CompanyService companyService,
                       CompanyMenu companyMenu,
                       ConsolePrinter printer,
                       Scanner scanner) {
        this.companyService = companyService;
        this.companyMenu = companyMenu;
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
        printer.printInfo("The company was created!\n");
    }

    private void listCompanies() {
        var companies = companyService.listCompanies();
        if (companies.isEmpty()) {
            printer.printInfo("The company list is empty!");
            printer.printInfo("");
            return;
        }
        printCompaniesMenu(companies);
        var choice = Integer.parseInt(scanner.nextLine());
        if (choice > 0) {
            Assert.state(choice <= companies.size(), "Invalid choice.");
            var selected = companies.get(choice - 1);
            printer.printInfo("'%s' company".formatted(selected.getName()));
            companyMenu.setCompany(selected);
            companyMenu.run();
        }
    }

    private void printCompaniesMenu(List<Company> companies) {
        printer.printInfo("Choose a company:");
        printer.printCompanyList(companies);
        printer.printInfo("0. Back\n");
    }

    private void printMenu() {
        printer.printInfo("""
                1. Company list
                2. Create a company
                0. Back
                """);
    }
}
