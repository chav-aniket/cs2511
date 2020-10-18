package unsw.dungeon.entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;

    /**
     * Create an entity positioned in square (x,y)
     * @param x x-coordinate of entity
     * @param y y-coordinate of entity
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    /**
     * Get the x-coordinate property of entity
     * @return x-coordinate property
     */
    public IntegerProperty x() {
        return x;
    }


    /**
     * Get the y-coordinate property of entity
     * @return y-coordinate property
     */
    public IntegerProperty y() {
        return y;
    }

    /**
     * Get the x-coordinate of entity
     * @return x-coordinate
     */
    public int getY() {
        return y().get();
    }

    /**
     * Get the y-coordinate of entity
     * @return y-coordinate
     */
    public int getX() {
        return x().get();
    }

    /**
     * Default interact value; doesn't allow a player to interact with anything if there isn't an overridden function for it
     * @param character Character interacting
     * @return Default interact value, false
     */
    public Boolean interact(CharacterInterface character) {
        return false;
    }
}
