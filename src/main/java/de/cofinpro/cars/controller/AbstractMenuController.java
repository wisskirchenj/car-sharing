package de.cofinpro.cars.controller;


import de.cofinpro.cars.io.ConsolePrinter;
import de.cofinpro.cars.io.NamedItem;

import java.util.List;
import java.util.Scanner;

/**
 * Abstract controller class for menu looping over enum based menu actions, that takes the enum E as type parameter.
 * Since this class provides methods for handling integer-choice based menus including the action loop and calling
 * the menu item's action, new menus can be added very easily. The customization is done by abstract framework methods.
 */
abstract class AbstractMenuController<E extends Enum<E>> {

    protected final ConsolePrinter printer;
    protected final Scanner scanner;

    protected AbstractMenuController(ConsolePrinter consolePrinter, Scanner scanner) {
        this.printer = consolePrinter;
        this.scanner = scanner;
    }

    /**
     * abstract getter to implement in subclass.
     * @return the menu text to display when prompting for a user choice
     */
    protected abstract String getMenuText();

    /**
     * abstract action getter for choice to implement in subclass.
     * @param choice user's choice as enum constant of type parameter E
     * @return the action to run for the user's choice
     */
    protected abstract Runnable getMenuAction(E choice);

    /**
     * abstract getter with which subclasses must tell this controller, which is the menu option to leave the menu
     */
    protected abstract E getExitChoice();

    /**
     * entry point method doing the menu loop and triggering the actions chosen.
     */
    public void run() {
        var choice = getMenuChoice();
        while (choice != getExitChoice()) {
            getMenuAction(choice).run();
            choice = getMenuChoice();
        }
    }

    protected int chooseFromList(List<? extends NamedItem> items, String itemType) {
        if (items.isEmpty()) {
            printer.printInfo("The {} list is empty!\n", itemType);
            return 0;
        }
        printItemsMenu(items, itemType);
        var choice =  Integer.parseInt(scanner.nextLine());
        printer.printInfo("");
        return choice;
    }

    private void printItemsMenu(List<? extends NamedItem> items, String itemType) {
        printer.printInfo("Choose a {}:", itemType);
        printer.printEnumeratedList(items);
        printer.printInfo("0. Back");
    }

    /**
     * user interaction method, that displays the menu and prompts for the users integer choice.
     * @return the choice as enum constant of the enum type parameter.
     */
    private E getMenuChoice() {
        printer.printInfo(getMenuText());
        var menuOptions = getExitChoice().getDeclaringClass().getEnumConstants();
        var choice =  menuOptions[Integer.parseInt(scanner.nextLine())];
        printer.printInfo("");
        return choice;
    }
}
