package de.cofinpro.cars.controller;

import de.cofinpro.cars.io.ConsolePrinter;
import de.cofinpro.cars.service.CustomerService;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Scanner;

@Controller
public class MenuRunner extends AbstractMenuController<MenuRunner.Choice> {
    private final ManagerMenu managerMenu;
    private final CustomerMenu customerMenu;
    private final CustomerService customerService;

    public MenuRunner(ConsolePrinter printer,
                      Scanner stdinScanner,
                      ManagerMenu managerMenu,
                      CustomerMenu customerMenu,
                      CustomerService customerService) {
        super(printer, stdinScanner);
        this.managerMenu = managerMenu;
        this.customerMenu = customerMenu;
        this.customerService = customerService;
    }

    @Override
    protected String getMenuText() {
        return """
                1. Log in as a manager
                2. Log in as a customer
                3. Create a customer
                0. Exit""";
    }

    @Override
    protected Runnable getMenuAction(Choice choice) {
        return Map.<Choice, Runnable>of(
                Choice.MANAGER_MENU, managerMenu::run,
                Choice.CUSTOMER_MENU, this::listCustomers,
                Choice.CREATE_CUSTOMER, this::createCustomer
        ).get(choice);
    }

    @Override
    protected Choice getExitChoice() {
        return Choice.EXIT;
    }

    private void createCustomer() {
        printer.printInfo("Enter the customer name:");
        var name = scanner.nextLine();
        customerService.createCustomer(name);
        printer.printInfo("The customer was added!\n");
    }

    private void listCustomers() {
        var customers = customerService.getCustomers();
        var choice = chooseFromList(customers, "customer");
        if (choice > 0) {
            var selected = customers.get(choice - 1);
            printer.printInfo("\n'{}' company", selected.getName());
            customerMenu.setCustomer(selected);
            customerMenu.run();
        }
    }

    protected enum Choice {
        EXIT,
        MANAGER_MENU,
        CUSTOMER_MENU,
        CREATE_CUSTOMER
    }
}
