package unsw.dungeon;

public class Portal extends Entity {
    private Dungeon dungeon;
    private int id, partnerId;
    /**
     * Create a portal positioned in square (x,y)
     * @param x
     * @param y
     */
    public Portal(Dungeon dungeon, int id, int PartnerId, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.id = id;
        this.partnerId = PartnerId;
    }

    public int getID() {
        return id;
    }

    public void teleportFrom(int deltaX, int deltaY) {
        Portal p = dungeon.portalSearch(partnerId);
        p.teleportTo(deltaX, deltaY);
    }

    public void teleportTo(int deltaX, int deltaY) {
        dungeon.getPlayer().teleport(getX() + deltaX, getY() + deltaY);
    }
}