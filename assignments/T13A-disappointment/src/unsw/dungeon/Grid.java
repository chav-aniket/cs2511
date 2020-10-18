package unsw.dungeon;

import java.util.ArrayList;

public class Grid {
    private int rows, cols;
    private ArrayList<Cell> cells;

    public Grid(int height, int width) {
        this.rows = height;
        this.cols = width;
        this.cells = new ArrayList<Cell>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells.add(new Cell(i, j));
            }
        }
    }

    public Cell getCell(int x, int y) {
        for (Cell c: cells) {
            if (c.getX() == x && c.getY() == y) {
                return c;
            }
        }
        return null;
    }
}