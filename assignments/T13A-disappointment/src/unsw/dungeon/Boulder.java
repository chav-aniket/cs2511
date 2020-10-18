package unsw.dungeon;

/**
 * A boulder in the dungeon
 */
public class Boulder extends Entity {

    private Dungeon dungeon;

    /**
     * Create a boulder positioned in square (x,y)
     * @param x
     * @param y
     */
    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public boolean moveBoulder(int changeX, int changeY) {
        Entity target = dungeon.detectCollision(getX()+changeX, getY()+changeY);
        if (target != null && 
            (target.getClass().getName().equals("unsw.dungeon.Wall") || target.getClass().getName().equals("unsw.dungeon.Boulder"))) {
            return false;
        }
        y().set(getY()+changeY);
        x().set(getX()+changeX);
        return true;
    }

}
