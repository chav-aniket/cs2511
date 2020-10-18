package unsw.dungeon;

public class Consumable extends Entity {

    private Dungeon dungeon;

    /**
     * Create a consumable positioned in square (x,y)
     * @param x
     * @param y
     */
    public Consumable(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public void removeItem() {
        return;
    }
}