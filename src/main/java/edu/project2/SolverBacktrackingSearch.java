package edu.project2;

import java.util.ArrayList;
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
        List<Coordinate> cellVisition = new LinkedList<>();
        cellVisition.add(current);
        while (!current.equals(end)) {

            neighbors = getNeighbours(maze.getHeight(), maze.getWidth(), grid, current);

            if (neighbors.size() != 0) {

                int random = randomizer.nextInt(neighbors.size());
                current = new Coordinate(neighbors.get(random).getRow(), neighbors.get(random).getCol());
                grid[current.getRow()][current.getCol()].setType(Cell.Type.VISITED);
                cellVisition.add(current);

            } else if (cellVisition.size() > 0) {
                cellVisition.removeLast();
                if (cellVisition.size() != 0) {
                    current = cellVisition.getLast();
                }

            } else {
                //   System.out.print("Не возможно найти путь");
                return Collections.emptyList();
            }

        }

        Renderer renderer = new ConsoleRenderer();
        renderer.render(maze, cellVisition);
        return cellVisition;
    }

    private List<Coordinate> getNeighbours(int height, int width, Cell[][] maze, Coordinate c) {

        int x = c.getRow();
        int y = c.getCol();
        int distance = 1;
        Coordinate up = new Coordinate(x, y - distance);
        Coordinate rt = new Coordinate(x + distance, y);
        Coordinate dw = new Coordinate(x, y + distance);
        Coordinate lt = new Coordinate(x - distance, y);
        Coordinate[] d = {up, rt, dw, lt};
        List<Coordinate> cell = new ArrayList<>();

        for (int i = 0; i < d.length; i++) {
            if (d[i].getRow() >= 0 && d[i].getRow() < height && d[i].getCol() >= 0 && d[i].getCol() < width) {
                if (maze[d[i].getRow()][d[i].getCol()].getType() != Cell.Type.WALL
                    && maze[d[i].getRow()][d[i].getCol()].getType() != Cell.Type.VISITED) {
                    cell.add(d[i]);
                }
            }

        }

        return cell;

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

