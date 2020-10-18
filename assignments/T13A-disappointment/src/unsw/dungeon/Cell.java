package unsw.dungeon;

import java.util.ArrayList;

public class Cell {

    private final int x, y;
    // private ArrayList<Entity> entities;
    
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        // this.entities = new ArrayList<Entity>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}