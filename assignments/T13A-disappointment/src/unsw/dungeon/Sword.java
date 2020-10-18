package unsw.dungeon;

public class Sword extends Consumable {
    private int uses;
    /**
     * Create a sword positioned in square (x,y)
     * @param x
     * @param y
     */
    public Sword(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.uses = 5;
    }

    public int getUses() {
        return uses;
    }

    public void use() {
        uses--;
    }
}