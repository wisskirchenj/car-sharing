package de.cofinpro.cars.controller;

import de.cofinpro.cars.io.ConsolePrinter;
import de.cofinpro.cars.service.CompanyService;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Scanner;

@Controller
public class ManagerMenu extends AbstractMenuController<ManagerMenu.Choice> {

    private final CompanyMenu companyMenu;
    private final CompanyService companyService;

    public ManagerMenu(ConsolePrinter printer,
                       Scanner scanner,
                       CompanyMenu companyMenu,
                       CompanyService companyService) {
        super(printer, scanner);
        this.companyService = companyService;
        this.companyMenu = companyMenu;
    }

    private void createCompany() {
        printer.printInfo("Enter the company name:");
        var name = scanner.nextLine();
        companyService.createCompany(name);
        printer.printInfo("The company was created!\n");
    }

    private void listCompanies() {
        var companies = companyService.getCompanies();
        var choice = chooseFromList(companies, "company");
        if (choice > 0) {
            var selected = companies.get(choice - 1);
            printer.printInfo("\n'{}' company", selected.getName());
            companyMenu.setCompany(selected);
            companyMenu.run();
        }
    }

    @Override
    protected Runnable getMenuAction(Choice choice) {
        return Map.<Choice, Runnable>of(
                Choice.COMPANY_MENU, this::listCompanies,
                Choice.CREATE_COMPANY, this::createCompany
        ).get(choice);
    }

    @Override
    protected Choice getExitChoice() {
        return Choice.EXIT;
    }

    @Override
    protected String getMenuText() {
        return """
                1. Company list
                2. Create a company
                0. Back""";
    }

    protected enum Choice {
        EXIT,
        COMPANY_MENU,
        CREATE_COMPANY
    }
}
