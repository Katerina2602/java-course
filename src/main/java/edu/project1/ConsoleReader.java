package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleReader implements Reader {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public String read() {
        LOGGER.info("Guess a letter:");
        return SCANNER.nextLine();
    }
}
