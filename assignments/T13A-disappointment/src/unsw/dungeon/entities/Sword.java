package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;

public class Sword extends Entity {
    
    private Dungeon dungeon;
    private int uses;
    /**
     * Create a sword positioned in square (x,y)
     * @param x x-coordinate of sword
     * @param y y-coordinate of sword
     */
    public Sword(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.uses = 5;
    }

    /**
     * Get number of remaining uses of sword
     * @return Number of uses
     */
    public int getUses() {
        return uses;
    }

    /**
     * Interaction between player and sword
     * @param c Player interacting with sword
     * @return Value of whether interaction is successful
     */
    @Override
    public Boolean interact(CharacterInterface c) {
        dungeon.pickup(this);
        return true;
    }

    /**
     * Deduct a use from sword uses
     */
    public void use() {
        uses--;
    }
}