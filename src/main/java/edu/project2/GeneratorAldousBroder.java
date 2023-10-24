package edu.project2;

import java.util.Arrays;
import java.util.Random;

public class GeneratorAldousBroder implements Generator {
    public static final int MIN_SIZE_MAZE = 3;
    public static final int TWO = 2;

    @Override
    public Maze generate(int height, int width) {
        validMazeSize(height, width);
        Cell[][] grid = createBaseMaze(height, width);
        buildMaze(grid, height, width);

        return new Maze(height, width, grid);
    }

    private void validMazeSize(int height, int width) {
        if (height < MIN_SIZE_MAZE || width < MIN_SIZE_MAZE) {
            throw new IllegalArgumentException("Размер лабиринта должен быть больше 3х");
        }
    }

    private int randomCoordinata(int number, int granica) {
        var random = new Random();
        if (number >= TWO && number < granica - TWO) {
            var list = Arrays.asList(-TWO, 0, TWO);
            return list.get(random.nextInt(list.size()));
        }
        java.util.List<Integer> list;
        if (number < TWO) {
            list = Arrays.asList(0, TWO);
        } else {
            list = Arrays.asList(0, -TWO);
        }
        return list.get(random.nextInt(list.size()));
    }

    private boolean hasWallForBroken(Cell[][] grid, int height, int width) {
        for (int i = 0; i < height; i = i + TWO) {
            for (int j = 0; j < width; j = j + TWO) {
                if (grid[i][j].getType() == Cell.Type.WALL) {
                    return true;
                }
            }
        }
        return false;
    }

    private Cell[][] createBaseMaze(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(y, x, Cell.Type.WALL);
            }
        }
        return grid;
    }

    private void buildMaze(Cell[][] grid, int height, int width) {
        int xCoordinata = 0;
        int yCoordinata = 0;
        int xran = 0;
        int yran = 0;

        while (hasWallForBroken(grid, height, width)) {
            xran = randomCoordinata(xCoordinata, width);
            xCoordinata = xCoordinata + xran;
            if (xran == 0) {
                while (yran == 0) {
                    yran = randomCoordinata(yCoordinata, height);
                    yCoordinata = yCoordinata + yran;
                }
            }

            if (grid[yCoordinata][xCoordinata].getType() == Cell.Type.WALL) {
                grid[yCoordinata][xCoordinata].setType(Cell.Type.PASSAGE);
                grid[yCoordinata - yran / 2][xCoordinata - xran / 2].setType(Cell.Type.PASSAGE);
            }
            yran = 0;
        }

    }

}
