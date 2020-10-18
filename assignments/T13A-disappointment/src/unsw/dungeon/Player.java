package unsw.dungeon;

// import org.graalvm.compiler.nodes.NodeView.Default;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private Inventory inventory;
    private boolean invincible;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.inventory = new Inventory();
        this.invincible = false;
    }

    public void teleport(int x, int y) {
        x().set(x);
        y().set(y);
    }

    public void moveUp() {
        if (!collisionHandler(getX(), getY()-1)) return;
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (!collisionHandler(getX(), getY()+1)) return;
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (!collisionHandler(getX()-1, getY())) return;
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (!collisionHandler(getX()+1, getY())) return;
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }

    public boolean collisionHandler(int targetX, int targetY) {
        Entity target = dungeon.detectCollision(targetX, targetY);
        if (target == null) return true;
        String entity = target.getClass().getName();

        switch (entity) {
            case "unsw.dungeon.Wall":
                return false;
            case "unsw.dungeon.Treasure":
                dungeon.removeEntity(target);
                System.out.println("Treasure has been picked up!");
                return true;
            case "unsw.dungeon.Enemy":
                Sword sword = inventory.hasSword();
                if (sword != null) {
                    sword.use();
                    dungeon.removeEntity(target);
                    Enemy enemy = (Enemy) target;
                    enemy.kill();
                    System.out.println("Enemy has been slain");
                }
                return true;
            case "unsw.dungeon.Sword":
                dungeon.removeEntity(target);
                sword = (Sword) target;
                inventory.add(sword);
                System.out.println("Sword has been picked up!");
                return true;
            case "unsw.dungeon.Invincibility":
                dungeon.removeEntity(target);
                Invincibility invincibility = (Invincibility) target;
                // inventory.add(invincibility);
                invincibility.use(this);
                System.out.println("Invincibility potion has been picked up!");
                return true;
            case "unsw.dungeon.Boulder":
                Boulder b = (Boulder) target;
                return b.moveBoulder(targetX-getX(), targetY-getY());
            case "unsw.dungeon.Key":
                dungeon.removeEntity(target);
                System.out.println("Key has been picked up!");
                Key key = (Key) target;
                inventory.add(key);
                return true;
            case "unsw.dungeon.Door":
                Door door = (Door) target;
                boolean opened = door.open(inventory.getKey());
                if (opened) {
                    System.out.println("Door has been opened");
                    inventory.remove("key");
                    return true;
                }
                return false;
            case "unsw.dungeon.Portal":
                Portal portal = (Portal) target;
                int deltaX = getX() - portal.getX();
                int deltaY = getY() - portal.getY();
                portal.teleportFrom(deltaX, deltaY);
                return true;
            default:
                return true;
        }
    }

    public int[] positionBroadcast() {
        int[] coords = {getX(), getY()};
        return coords;
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void toggleInvincible(boolean bool) {
        invincible = bool;
    }
}
