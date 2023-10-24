package edu.project2;

import java.util.List;

public class ConsoleRenderer implements Renderer {

    @Override
    public String render(Maze maze) {
        StringBuilder pageMaze = new StringBuilder();
        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                if (maze.getGrid()[y][x].getType() == Cell.Type.WALL) {
                    pageMaze.append("██");
                }

                if (maze.getGrid()[y][x].getType() == Cell.Type.PATH) {
                    pageMaze.append("\u001B[31m" + "░░" + "\u001B[0m");
                }
                if (maze.getGrid()[y][x].getType() == Cell.Type.PASSAGE) {
                    pageMaze.append("░░");
                }
                if (maze.getGrid()[y][x].getType() == Cell.Type.VISITED) {
                    pageMaze.append("░░");
                }
            }
            pageMaze.append("\n\r");
        }

        return pageMaze.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {

        for (Coordinate f : path) {
            maze.getGrid()[f.getRow()][f.getCol()].setType(Cell.Type.PATH);
        }
        render(maze);
        return null;
    }
}
