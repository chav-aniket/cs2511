package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;

import java.util.List;

public class Switch extends Entity {

    Dungeon dungeon;
    /**
     * Create a switch positioned in square (x,y)
     * @param x x-coordinate of switch
     * @param y y-coordinate of switch
     */
    public Switch(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    /**
     * Check whether switch is pressed by a boulder
     * @return Value of whether switch is currently pressed
     */
    public boolean isPressed() {
        List<Entity> entities = dungeon.getEntities();
        for (Entity e: entities) {
            if (e != null && e.getY() == getY() && e.getX() == getX()) {
                if ("Boulder".equals(e.getClass().getSimpleName()))
                    return true;
            }
        }
        return false;
    }
}