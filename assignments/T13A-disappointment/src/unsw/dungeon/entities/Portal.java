package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;

public class Portal extends Entity {
    private Dungeon dungeon;
    private int id;
    /**
     * Create a portal positioned in square (x,y)
     * @param x x-coordinate for portal
     * @param y y-coordinate for portal
     */
    public Portal(Dungeon dungeon, int id, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.id = id;
    }

    /**
     * Get the portal ID
     * @return portal ID
     */
    public int getID() {
        return id;
    }

    /**
     * Interaction between player and portal
     * @param character Player or Enemy to be teleported
     * @return false to tell collision handler that player cannot walk into portal
     */
    @Override
    public Boolean interact(CharacterInterface character) {
        teleport(character);
        return false;
    }

    /**
     * Search for portals with corresponding ID as source portal
     * @param id id of source and dest portal
     * @return destination portal
     */
    private Portal portalSearch(int id) {
        for (Entity e: dungeon.getEntities()) {
            if (e != null && e.getClass().getName().equals("unsw.dungeon.entities.Portal")) {
                Portal portal = (Portal) e;
                if (portal.getID() == id && (portal.getX() != getX() || portal.getY() != getY())) {
                    return (Portal) e;
                }
            }
        }
        return null;
    }

    /**
     * Teleport a player to a corresponding portal
     */
    private void teleport(CharacterInterface c) {
        Portal p = portalSearch(id);
        if (p == null) return;
        int destX = p.getX();
        int destY = p.getY();

        int deltaX = getX() - c.getX();
        int deltaY = getY() - c.getY();
        c.move(destX + deltaX, destY + deltaY);
    }
}