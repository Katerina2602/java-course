package edu.project2;

public class TestUtils {
    /*
     ░░░░░░
     ████░░
     ░░██░░
   */

    public static Maze createTestMaze() {
        Cell[][] matrix = new Cell[5][5];
        matrix[0][0] = new Cell(0, 0, Cell.Type.PASSAGE);
        matrix[0][1] = new Cell(0, 1, Cell.Type.PASSAGE);
        matrix[0][2] = new Cell(0, 2, Cell.Type.PASSAGE);
        matrix[0][3] = new Cell(0, 2, Cell.Type.WALL);
        matrix[0][4] = new Cell(0, 2, Cell.Type.WALL);

        matrix[1][0] = new Cell(1, 0, Cell.Type.WALL);
        matrix[1][1] = new Cell(1, 1, Cell.Type.WALL);
        matrix[1][2] = new Cell(1, 2, Cell.Type.PASSAGE);
        matrix[1][3] = new Cell(1, 2, Cell.Type.WALL);
        matrix[1][4] = new Cell(1, 2, Cell.Type.PASSAGE);

        matrix[2][0] = new Cell(2, 0, Cell.Type.PASSAGE);
        matrix[2][1] = new Cell(2, 1, Cell.Type.WALL);
        matrix[2][2] = new Cell(2, 2, Cell.Type.PASSAGE);
        matrix[2][3] = new Cell(2, 2, Cell.Type.PASSAGE);
        matrix[2][4] = new Cell(2, 2, Cell.Type.PASSAGE);

        matrix[3][0] = new Cell(2, 0, Cell.Type.PASSAGE);
        matrix[3][1] = new Cell(2, 1, Cell.Type.WALL);
        matrix[3][2] = new Cell(2, 2, Cell.Type.PASSAGE);
        matrix[3][3] = new Cell(2, 2, Cell.Type.WALL);
        matrix[3][4] = new Cell(2, 2, Cell.Type.PASSAGE);

        matrix[4][0] = new Cell(2, 0, Cell.Type.PASSAGE);
        matrix[4][1] = new Cell(2, 1, Cell.Type.WALL);
        matrix[4][2] = new Cell(2, 2, Cell.Type.PASSAGE);
        matrix[4][3] = new Cell(2, 2, Cell.Type.WALL);
        matrix[4][4] = new Cell(2, 2, Cell.Type.PASSAGE);

        return new Maze(5, 5, matrix);
    }
}
