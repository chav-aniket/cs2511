package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity {
    private Dungeon dungeon;
    private BooleanProperty isOpen = new SimpleBooleanProperty(false);
    private int id;

    /**
     * Create a exit positioned in square (x,y)
     * @param x x-coordinate of door
     * @param y y-coordinate of door
     */
    public Door(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    /**
     * Interaction between Player and door
     * @param character Character interacting with door
     * @return Value of whether interaction is sucessful
     */
    public Boolean interact(CharacterInterface character) {
        Player player = (Player) character;
        boolean opened = open(player.getKeyId());
        if (opened) {
            player.useKey();
            dungeon.removeEntity(this);
            player.setKeyEquipped(false);
            return true;
        }
        return false;
    }

    /**
     * Try to open a door
     * @param keyId ID of key
     * @return Value of whether door was successfully opened
     */
    private boolean open(int keyId) {
        if (keyId == id) {
            isOpen.set(true);
        }
        return isOpen.get();
    }

    /**
     * Set ID of door
     * @param id ID of door
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Get ID of door
     * @return ID of door
     */
    public int getID() {
        return this.id;
    }

    /**
     * Get status of whether door is open
     * @return value of whether door is open
     */
    public BooleanProperty getIsOpen() {
        return isOpen;
    }
}