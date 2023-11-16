package edu.project2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleRendererTest {
    private final Renderer renderer = new ConsoleRenderer();

    @Test
    void willReturnCorrectRender() {
        Maze maze = TestUtils.createTestMaze();
        String expectedRender = "" +
            "░░░░░░████" + System.lineSeparator() +
            "████░░██░░" + System.lineSeparator() +
            "░░██░░░░░░" + System.lineSeparator() +
            "░░██░░██░░" + System.lineSeparator() +
            "░░██░░██░░" + System.lineSeparator();

        String actualRender = renderer.render(maze);
        assertEquals(expectedRender, actualRender);
    }

}
