package edu.project2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GeneratorDepthFirstSearch implements Generator {
    public static final int MIN_SIZE_MAZE = 3;
    private final Random randomizer = new Random();

    @Override
    public Maze generate(int height, int width) {
        validMazeSize(height, width);
        Cell[][] maze = createBaseMaze(height, width);
        List<Coordinate> neighbors;
        Coordinate current = new Coordinate(1, 1);
        maze[current.getRow()][current.getCol()].setType(Cell.Type.VISITED);
        Coordinate currentNeighbors;
        List<Coordinate> cellVisited = new LinkedList<>();
        List<Coordinate> unvisitedCells;
        do {
            neighbors = Neighbours.getNeighbours(height, width, maze, current, 2);
            if (!neighbors.isEmpty()) {
                int random = randomizer.nextInt(neighbors.size());
                currentNeighbors = new Coordinate(
                    neighbors.get(random).getRow(),
                    neighbors.get(random).getCol()
                );
                cellVisited.add(currentNeighbors);
                removeWall(current, currentNeighbors, maze);
                current = currentNeighbors;
                maze[current.getRow()][current.getCol()].setType(Cell.Type.VISITED);
            } else if (!cellVisited.isEmpty()) {
                cellVisited.removeLast();
                current = cellVisited.getLast();

            } else {

                unvisitedCells = getUnvisitedCells(height, width, maze);
                int random = randomizer.nextInt(neighbors.size());
                current = unvisitedCells.get(random);
                maze[current.getRow()][current.getCol()].setType(Cell.Type.VISITED);

            }
            unvisitedCells = getUnvisitedCells(height, width, maze);

        } while (!unvisitedCells.isEmpty());

        return new Maze(height, width, maze);
    }

    private List<Coordinate> getUnvisitedCells(int height, int width, Cell[][] maze) {
        List<Coordinate> list = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (maze[i][j].getType() == Cell.Type.PASSAGE) {
                    list.add(new Coordinate(maze[i][j].getRow(), maze[i][j].getCol()));
                }
            }

        }

        return list;
    }

    private void removeWall(Coordinate first, Coordinate second, Cell[][] maze) {
        int addX;
        int addY;
        if (second.getRow() - first.getRow() != 0) {
            addX = (second.getRow() - first.getRow()) / Math.abs(second.getRow() - first.getRow());
        } else {
            addX = 0;
        }
        if (second.getCol() - first.getCol() != 0) {
            addY = (second.getCol() - first.getCol()) / Math.abs(second.getCol() - first.getCol());
        } else {
            addY = 0;
        }

        maze[first.getRow() + addX][first.getCol() + addY].setType(Cell.Type.VISITED);

    }

    private void validMazeSize(int height, int width) {
        if (height < MIN_SIZE_MAZE || width < MIN_SIZE_MAZE) {
            throw new IllegalArgumentException("Размер лабиринта должен быть больше 3х");
        }
    }

    private Cell[][] createBaseMaze(int height, int width) {
        Cell[][] maze = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i % 2 != 0 && j % 2 != 0)
                    && (i < height - 1 && j < width - 1)) {
                    maze[i][j] = new Cell(i, j, Cell.Type.PASSAGE);
                } else {
                    maze[i][j] = new Cell(i, j, Cell.Type.WALL);
                }
            }
        }

        return maze;
    }
}
