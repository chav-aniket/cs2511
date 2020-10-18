package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;

public class Key extends Entity {
    
    private Dungeon dungeon;
    private int id;

    /**
     * Create a key positioned in square (x,y)
     * @param x x-coordinate of key
     * @param y y-coordinate of key
     */
    public Key(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    /**
     * Interaction between player and key
     * @param character Character interacting with key
     * @return Value of whether interaction is successful
     */
    @Override
    public Boolean interact(CharacterInterface character) {
        Player player = (Player) character;
        if (player.getKeyId() == -1) {
            player.setKeyEquipped(true);
            dungeon.pickup(this);
        }
        return true;
    }

    /**
     * Get the ID of the key
     * @return key id
     */
    public int getID() {
        return id;
    }

    /**
     * Set the ID of the key
     * @param id ID to be set
     */
    public void setID(int id) {
        this.id = id;
    }
}