package edu.project2;

import java.util.Objects;

public class Coordinate {

    private int row;
    private int col;

    public Coordinate() {

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;

    }

    @Override public String toString() {
        return "Coordinate{"
            + "row=" + row
            + ", col=" + col
            + '}';
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
