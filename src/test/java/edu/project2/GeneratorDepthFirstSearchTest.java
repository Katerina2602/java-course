package edu.project2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GeneratorDepthFirstSearchTest {

    @Test
    void willReturnCorrectMaze() {
        Generator generator = new GeneratorDepthFirstSearch();
        Maze maze = generator.generate(25, 15);
        assertEquals(25, maze.getHeight());
        assertEquals(15, maze.getWidth());
        Renderer renderer = new ConsoleRenderer();
        renderer.render(maze);

    }

    @Test
    void willReturnErrorIfSizeMazeWrong() {
        Generator maze = new GeneratorDepthFirstSearch();
        assertThrows(IllegalArgumentException.class, () -> maze.generate(2, 2));
    }

}
