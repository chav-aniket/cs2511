package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity {
    private BooleanProperty isOpen = new SimpleBooleanProperty(false);
    private int id;

    /**
     * Create a exit positioned in square (x,y)
     * @param x x-coordinate of door
     * @param y y-coordinate of door
     */
    public Door(int x, int y) {
        super(x, y);
    }

    public boolean open(Key key) {
        if (key != null && key.getID() == id) {
            isOpen.set(true);
        }
        return isOpen.get();
    }


    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

    public BooleanProperty getIsOpen() {
        return isOpen;
    }
}