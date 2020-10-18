package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;

/**
 * A boulder in the dungeon
 */
public class Boulder extends Entity {

    private Dungeon dungeon;

    /**
     * Create a boulder positioned in square (x,y)
     * @param x x-coordinate of boulder
     * @param y y-coordinate of boulder
     */
    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    /**
     * Interaction between player and boulder
     * @param character Player interacting with boulder
     * @return Value of whether boulder can be moved
     */
    @Override
    public Boolean interact(CharacterInterface character) {
        return moveBoulder(getX() - character.getX(), getY() - character.getY());
    }

    /**
     *
     * @param changeX Change in x-coordinate
     * @param changeY Change in y-coordinate
     * @return Value of whether boulder can be moved
     */
    private boolean moveBoulder(int changeX, int changeY) {
        Entity target = dungeon.detectCollision(getX()+changeX, getY()+changeY);
        if (target != null) {
            String name = target.getClass().getSimpleName();
            if (name.equals("Wall") || name.equals("Boulder")) return false;
        }
        y().set(getY()+changeY);
        x().set(getX()+changeX);
        return true;
    }

}
