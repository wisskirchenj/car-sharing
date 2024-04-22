package de.cofinpro.cars.io;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Slf4J wrapping printer class, accepting input to print to the console (stdout)
 */
@Slf4j
@Component
public class ConsolePrinter {

    public void printInfo(String message, Object... arguments) {
        log.info(message, arguments);
    }

    public void printCarList(List<? extends NamedItem> cars) {
        if (cars.isEmpty()) {
            printInfo("The car list is empty!");
            return;
        }
        printEnumeratedList(cars);
    }

    public void printEnumeratedList(List<? extends NamedItem> items) {
        IntStream.range(0, items.size())
                .forEach( i -> printInfo("{}. {}", i + 1, items.get(i).getName()));
    }
}
