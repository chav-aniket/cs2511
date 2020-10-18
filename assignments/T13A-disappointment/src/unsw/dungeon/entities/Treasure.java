package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;

public class Treasure extends Entity {

    Dungeon dungeon;

    /**
     * Create a treasure positioned in square (x,y)
     * @param x x-coordinate of treasure
     * @param y y-coordinate of treasure
     */
    public Treasure(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    /**
     * Interaction between Player and treasure
     * @param character Character interacting with treasure
     * @return Value of whether interaction is successful
     */
    @Override
    public Boolean interact(CharacterInterface character) {
        dungeon.pickup(this);
        return true;
    }
}