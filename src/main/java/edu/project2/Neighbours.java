package edu.project2;

import java.util.ArrayList;
import java.util.List;

public class Neighbours {
    private Neighbours() {

    }

    public static List<Coordinate> getNeighbours(int height, int width, Cell[][] maze, Coordinate c, int distance) {

        int x = c.getRow();
        int y = c.getCol();
        Coordinate up = new Coordinate(x, y - distance);
        Coordinate rt = new Coordinate(x + distance, y);
        Coordinate dw = new Coordinate(x, y + distance);
        Coordinate lt = new Coordinate(x - distance, y);
        Coordinate[] d = {up, rt, dw, lt};
        List<Coordinate> cell = new ArrayList<>();

        for (Coordinate coordinate : d) {
            if (coordinate.getRow() >= 0 && coordinate.getRow() < height && coordinate.getCol() >= 0
                && coordinate.getCol() < width
                && (maze[coordinate.getRow()][coordinate.getCol()].getType() != Cell.Type.WALL
                && maze[coordinate.getRow()][coordinate.getCol()].getType() != Cell.Type.VISITED)) {
                cell.add(coordinate);
            }

        }

        return cell;
    }
}
