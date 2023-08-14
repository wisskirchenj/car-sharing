package de.cofinpro.cars.controller;

import de.cofinpro.cars.io.ConsolePrinter;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class MenuRunner {
    private final ConsolePrinter printer;
    private final ManagerMenu managerMenu;
    private final Scanner scanner;

    public MenuRunner(ConsolePrinter printer,
                      ManagerMenu managerMenu,
                      Scanner stdinScanner) {
        this.printer = printer;
        this.managerMenu = managerMenu;
        this.scanner = stdinScanner;
    }

    public void run() {
        printMenu();
        var choice = Integer.parseInt(scanner.nextLine());
        while (choice != 0) {
            managerMenu.run();
            printMenu();
            choice = Integer.parseInt(scanner.nextLine());
        }
    }

    private void printMenu() {
        printer.printInfo("""
                1. Log in as a manager
                0. Exit
                """);
    }
}
