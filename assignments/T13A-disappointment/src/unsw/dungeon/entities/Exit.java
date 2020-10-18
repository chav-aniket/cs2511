package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;

public class Exit extends Entity {

    private Dungeon dungeon;
    private Boolean pressed = false;

    /**
     * Create a exit positioned in square (x,y)
     * @param x x-coordinate of exit
     * @param y y-coordinate of exit
     */
    
    public Exit(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    /**
     * Interaction between exit and character
     * @param character Character interacting
     * @return Confirm interaction successful
     */
    @Override
    public Boolean interact(CharacterInterface character) {
        pressed = true;
        return false;
    }

    /**
     * Get value of whether exit has been interacted with
     * @return value of whether exit interaction exists
     */
    public Boolean isPressed() {
        return pressed;
    }
}