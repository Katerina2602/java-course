package edu.project2;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SolverBacktrackingSearch implements Solver {
    private final Random randomizer = new Random();

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        checkingCoordinateBoundaries(maze, start);
        checkingCoordinateBoundaries(maze, end);

        maze.getGrid()[start.getRow()][start.getCol()].setType(Cell.Type.PASSAGE);
        maze.getGrid()[end.getRow()][end.getCol()].setType(Cell.Type.PASSAGE);

        Coordinate current = new Coordinate(start.getRow(), start.getCol());
        Cell[][] grid = preparationGrid(maze);
        grid[start.getRow()][start.getCol()].setType(Cell.Type.VISITED);
        List<Coordinate> neighbors;
        List<Coordinate> cellVisited = new LinkedList<>();
        cellVisited.add(current);
        while (!current.equals(end)) {

            neighbors = Neighbours.getNeighbours(maze.getHeight(), maze.getWidth(), grid, current, 1);

            if (!neighbors.isEmpty()) {

                int random = randomizer.nextInt(neighbors.size());
                current = new Coordinate(neighbors.get(random).getRow(), neighbors.get(random).getCol());
                grid[current.getRow()][current.getCol()].setType(Cell.Type.VISITED);
                cellVisited.add(current);

            } else if (!cellVisited.isEmpty()) {
                cellVisited.removeLast();
                if (!cellVisited.isEmpty()) {
                    current = cellVisited.getLast();
                }

            } else {
                return Collections.emptyList();
            }

        }

        Renderer renderer = new ConsoleRenderer();
        renderer.render(maze, cellVisited);
        return cellVisited;
    }

    private Cell[][] preparationGrid(Maze maze) {
        Cell[][] grid = new Cell[maze.getHeight()][maze.getWidth()];
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (maze.getGrid()[i][j].getType() == Cell.Type.WALL) {
                    grid[i][j] = new Cell(i, j, Cell.Type.WALL);
                } else {
                    grid[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                }
            }
        }
        return grid;
    }

    private void checkingCoordinateBoundaries(Maze maze, Coordinate coordinate) {
        if (coordinate.getRow() >= maze.getHeight() || coordinate.getCol() >= maze.getWidth()
            || coordinate.getRow() < 0 || coordinate.getCol() < 0) {
            throw new IllegalArgumentException("Координаты за пределами лабиринта");
        }
    }
}

