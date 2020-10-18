package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Player;
import unsw.dungeon.GraphNode;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Enemy extends Entity implements CharacterInterface {
    private Dungeon dungeon;
    private BooleanProperty alive;
    private AStar router;
    private int destX = -1, destY = -1;

    /**
     * Create an enemy positioned in square (x,y)
     * @param x x-coordinate of enemy
     * @param y y-coordinate of enemy
     */
    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.alive = new SimpleBooleanProperty(true);
        this.router = new AStar(this, dungeon);
        dungeon.subscribe(this);
        dungeon.getPlayer().subscribe(this);
    }

    /**
     * Move an enemy to given coordinates
     * @param x x-coordinate to be moved to
     * @param y y-coordinate to be moved to
     */
    public void move(int x, int y) {
        if (!(isAlive() && interactHandler(x, y))) return;
        y().set(y);
        x().set(x);
    }

    /**
     * Update enemy at every tick; Calculate the next step for enemy to take
     * @param coords coordinates of player
     * @param isInvincible value of whether player is invincible or not
     */
    public void update(int[] coords, boolean isInvincible) {
        int playerX = coords[0], playerY = coords[1];
        if (isInvincible) {
            escape(playerX, playerY);
        } else {
            if (playerX != destX || playerY != destY) {
                destX = playerX;
                destY = playerY;
                router.findRoute(getX(), getY(), playerX, playerY);
            }
        }
    }

    /**
     * Take next step at each tick of game
     */
    public void tick() {
        router.nextStep(getX(), getY(), destX, destY);
    }

    /**
     * Interaction between enemy and player
     * @param c Player to be interacted with
     * @return confirm interaction successful
     */
    @Override
    public Boolean interact(CharacterInterface c) {
        if (!(c instanceof Player) || !c.isAlive()) return true;
        Player player = (Player) c;
        if (player.getInvincibilityStatus()) {
            dungeon.removeEntity(this);
            kill();
        } else if (player.isSwordEquipped()) {
            player.useSword();
            dungeon.removeEntity(this);
            kill();
        } else {
            player.kill();
        }
        return true;
    }

    /**
     * Get value of whether enemy is alive
     * @return alive status of enemy
     */
    public BooleanProperty getAlive() {
        return alive;
    }

    /**
     * Check whether enemy is alive
     * @return alive status of enemy
     */
    public boolean isAlive() {
        return alive.get();
    }

    /**
     * Handle collisions between enemy and other entities
     * @param targetX Target x-coordinate
     * @param targetY Target y-coordinate
     * @return Value of whether entity can be collided with
     */
    public boolean collisionHandler(int targetX, int targetY) {
        ArrayList<Entity> entities = dungeon.getEntities();
        for (Entity e: entities) {
            if (e != null && e.getX() == targetX && e.getY() == targetY) {
                if (e instanceof Wall || e instanceof Boulder || e instanceof Door)
                    return false;
            }
        }
        return true;
    }

    /**
     * Handle interactions between enemy and other entities
     * @param targetX Target x-coordinate
     * @param targetY Target y-coordinate
     */
    private boolean interactHandler(int targetX, int targetY) {
        for (Entity e: dungeon.getEntities()) {
            if (e != null && e.getX() == targetX && e.getY() == targetY) {
                if (e instanceof Player) {
                    Player p = (Player) e;
                    return p.interact(this);
                } else if (e instanceof Portal) {
                    Portal p = (Portal) e;
                    return p.interact(this);
                }
            }
        }
        return true;
    }

    /**
     * Make enemy escape player when player is invincible
     * @param playerX x-coordinate of player
     * @param playerY y-coordinate of player
     */
    private void escape(int playerX, int playerY) {
        GraphNode target = router.findFurthest(getX(), getY(), playerX, playerY);
        if (destX != target.getX() || destY != target.getY()) {
            destX = target.getX();
            destY = target.getY();
            router.findRoute(getX(), getY(), destX, destY);
        }
    }

    /**
     * Kill enemy and remove entity from dungeon
     */
    public void kill() {
        alive.set(false);
        dungeon.removeEntity(this);
    }
}
