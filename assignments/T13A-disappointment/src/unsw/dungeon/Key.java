package unsw.dungeon;

public class Key extends Consumable {
    private int id;

    /**
     * Create a key positioned in square (x,y)
     * @param x
     * @param y
     */
    public Key(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
}