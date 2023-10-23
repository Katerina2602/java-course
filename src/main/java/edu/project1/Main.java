package edu.project1;

import java.nio.file.Paths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        try {
            Dictionary dictionary = new Dictionary(Paths.get("/project1/dictionary.txt"));
            Reader reader = new ConsoleReader();
            Game game = new Game(dictionary, reader);

            game.run();
        } catch (Exception ex) {
            LOGGER.error("Error: {}", ex.getMessage());
        }
    }
}
