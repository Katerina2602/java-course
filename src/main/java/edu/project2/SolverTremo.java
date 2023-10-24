package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolverTremo implements Solver {
    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {

        checkingCoordinateBoundaries(maze, start);
        checkingCoordinateBoundaries(maze, end);
        maze.getGrid()[start.getRow()][start.getCol()].setType(Cell.Type.PASSAGE);
        maze.getGrid()[end.getRow()][end.getCol()].setType(Cell.Type.PASSAGE);

        int[][] grid = new int[maze.getHeight()][maze.getWidth()];
        preparationGrid(maze, grid);
        int maxIteration = 0;
        int count = 2;
        grid[end.getRow()][end.getCol()] = count;
        while (grid[start.getRow()][start.getCol()] == 0) {
            count++;
            searchWays(grid, count);
            maxIteration++;
            if (maxIteration >= grid.length * grid[0].length) {
                return Collections.emptyList();
            }
        }

        count = grid[start.getRow()][start.getCol()];

        return saveThePath(start, grid, count);
    }

    private void checkingCoordinateBoundaries(Maze maze, Coordinate coordinate) {
        if (coordinate.getRow() >= maze.getHeight() || coordinate.getCol() >= maze.getWidth()
            || coordinate.getRow() < 0 || coordinate.getCol() < 0) {
            throw new IllegalArgumentException("Координаты за пределами лабиринта");
        }
    }

    private void preparationGrid(Maze maze, int[][] grid) {
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                if (maze.getGrid()[i][j].getType() == Cell.Type.WALL) {
                    grid[i][j] = 1;
                }
            }
        }
    }

    private int searchPath(int y, int x, int[][] grid, int num, List<Coordinate> list) {
        int count = num;
        if (y >= 0 && y < grid.length && x >= 0 && x < grid[0].length && grid[y][x] == count - 1 && grid[y][x] >= 2) {
            list.add(new Coordinate(y, x));
            count--;
        }
        return count;

    }

    private List<Coordinate> saveThePath(Coordinate current, int[][] grid, int num) {

        int count = num;
        List<Coordinate> list = new ArrayList<>();
        list.add(current);
        while (count > 2) {
            count = searchPath(list.getLast().getRow() - 1, list.getLast().getCol(), grid, count, list);
            count = searchPath(list.getLast().getRow() + 1, list.getLast().getCol(), grid, count, list);
            count = searchPath(list.getLast().getRow(), list.getLast().getCol() - 1, grid, count, list);
            count = searchPath(list.getLast().getRow(), list.getLast().getCol() + 1, grid, count, list);

        }
        return list;
    }

    private void initialization(int[][] grid, int i, int j, int count) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] != 1) {
            if (grid[i][j] == 0) {
                grid[i][j] = count;
            } else {
                grid[i][j] = Math.min(count, grid[i][j]);
            }
        }
    }

    private void searchWays(int[][] grid, int count) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 1 && grid[i][j] != count) {
                    initialization(grid, i - 1, j, count);
                    initialization(grid, i + 1, j, count);
                    initialization(grid, i, j - 1, count);
                    initialization(grid, i, j + 1, count);
                }
            }
        }
    }
}
