package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SolverBacktrackingSearchTest {
    private final Solver solver = new SolverBacktrackingSearch();

    @Test
    void test() {
        Generator generator = new GeneratorDepthFirstSearch();
        Renderer renderer = new ConsoleRenderer();
        Maze maze = generator.generate(21, 31);
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(19, 29);
        Solver list = new SolverBacktrackingSearch();
        list.solve(maze, start, end);

    }

    @Test
    void willFindCorrectPath() {
        List<Coordinate> expectedCoordinates = new ArrayList<>();
        expectedCoordinates.add(new Coordinate(0, 0));
        expectedCoordinates.add(new Coordinate(0, 1));
        expectedCoordinates.add(new Coordinate(0, 2));
        expectedCoordinates.add(new Coordinate(1, 2));
        expectedCoordinates.add(new Coordinate(2, 2));
        expectedCoordinates.add(new Coordinate(2, 3));
        expectedCoordinates.add(new Coordinate(2, 4));
        expectedCoordinates.add(new Coordinate(1, 4));

        Maze maze = TestUtils.createTestMaze();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(1, 4);

        List<Coordinate> actualCoordinates = solver.solve(maze, start, end);

        assertEquals(expectedCoordinates, actualCoordinates);

    }

    @Test
    void willNotFindCorrectPath() {
        Maze maze = TestUtils.createTestMaze();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(2, 0);

        List<Coordinate> actualCoordinates = solver.solve(maze, start, end);

        assertTrue(actualCoordinates.isEmpty());

    }

    @Test
    void willReturnOneCoordinateIfStarAndEndEqual() {
        List<Coordinate> expectedCoordinates = new ArrayList<>();
        expectedCoordinates.add(new Coordinate(0, 0));

        Maze maze = TestUtils.createTestMaze();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(0, 0);

        List<Coordinate> actualCoordinates = solver.solve(maze, start, end);

        assertEquals(expectedCoordinates, actualCoordinates);

    }

    @ParameterizedTest
    @MethodSource("provideWrongCoordinates")
    void willThrowsExceptionWhenCoordinateNotExists(Coordinate start, Coordinate end) {
        Maze maze = TestUtils.createTestMaze();

        assertThrows(IllegalArgumentException.class, () -> solver.solve(maze, start, end));
    }

    private static Stream<Arguments> provideWrongCoordinates() {

        return Stream.of(
            Arguments.of(new Coordinate(0, 0), new Coordinate(0, 5)),
            Arguments.of(new Coordinate(0, 0), new Coordinate(-3, 0)),
            Arguments.of(new Coordinate(8, 0), new Coordinate(2, 2)),
            Arguments.of(new Coordinate(1, -2), new Coordinate(2, 2))
        );
    }

}
