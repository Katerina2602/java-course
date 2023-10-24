package edu.project2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleRendererTest {
    private final Renderer renderer = new ConsoleRenderer();

    @Test
    void willReturnCorrectRender() {
        Maze maze = TestUtils.createTestMaze();
        String expectedRender = "" +
            "░░░░░░████\n\r" +
            "████░░██░░\n\r" +
            "░░██░░░░░░\n\r" +
            "░░██░░██░░\n\r" +
            "░░██░░██░░\n\r";

        String actualRender = renderer.render(maze);
        assertEquals(expectedRender, actualRender);
    }

}
