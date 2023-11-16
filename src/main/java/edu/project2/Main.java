package edu.project2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MagicNumber")
public class Main {
    private Main() {

    }

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        buildingLabyrinthDepthFirstSearch();
        buildingLabyrinthAldousBroder();
    }

    private static void buildingLabyrinthDepthFirstSearch() {
        Generator generatorOne = new GeneratorDepthFirstSearch();
        Maze maze = generatorOne.generate(21, 31);
        findingTheWayBacktrackingSearch(maze);
    }

    private static void buildingLabyrinthAldousBroder() {
        Generator generatorOne = new GeneratorAldousBroder();
        Maze maze = generatorOne.generate(25, 35);
        findingTheWayTremo(maze);
    }

    private static void findingTheWayBacktrackingSearch(Maze maze) {
        Coordinate start = new Coordinate(5, 5);
        Coordinate end = new Coordinate(17, 29);
        Solver list = new SolverBacktrackingSearch();
        list.solve(maze, start, end);
        Renderer renderer = new ConsoleRenderer();
        LOGGER.info(System.lineSeparator() + renderer.render(maze));
    }

    private static void findingTheWayTremo(Maze maze) {
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(19, 29);
        Solver list = new SolverTremo();
        list.solve(maze, start, end);
        Renderer renderer = new ConsoleRenderer();
        LOGGER.info(System.lineSeparator() + renderer.render(maze));
    }

}
