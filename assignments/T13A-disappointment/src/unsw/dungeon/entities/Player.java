package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonController;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.time.LocalDateTime;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements CharacterInterface {
    private BooleanProperty alive;
    private BooleanProperty keyEquipped;
    private BooleanProperty swordEquipped;
    private Dungeon dungeon;
    private Inventory inventory;
    private ArrayList<Enemy> subscribers;
    private InvincibleStateInterface invincibleState;
    private Entity lastItem;

    /**
     * Create a player positioned in square (x,y)
     * @param x x-coordinate of player
     * @param y y-coordinate of player
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.alive = new SimpleBooleanProperty(true);
        this.keyEquipped = new SimpleBooleanProperty(false);
        this.swordEquipped = new SimpleBooleanProperty(false);
        this.dungeon = dungeon;
        this.invincibleState = new NonInvincibleState(this);
        this.inventory = new Inventory(this);
        this.subscribers = new ArrayList<>();
        this.lastItem = null;
    }

    /**
     * Move player to given coordinates
     * @param x x-coordinate for player to move to
     * @param y y-coordinate for player to move to
     */
    public void move(int x, int y) {
        try {
            if ( x < 0 && x >= dungeon.getWidth() ||
                 y < 0 && y >= dungeon.getHeight() ||
                 !collisionHandler(x, y)) return;
        } catch (ConcurrentModificationException e) {
            ;
        }
        y().set(y);
        x().set(x);
    }

    /**
     * Move player up
     */
    public void moveUp() {
        move(getX(), getY() - 1);
    }

    /**
     * Move player down
     */
    public void moveDown() {
        move(getX(), getY() + 1);
    }

    /**
     * Move player left
     */
    public void moveLeft() {
        move(getX() - 1, getY());
    }

    /**
     * Move player right
     */
    public void moveRight() {
        move(getX() + 1, getY());
    }

    /**
     * Interaction between player and enemy
     * @param enemy Enemy for player to interact with
     * @return Value of whether interaction is successful
     */
    public boolean interact(Enemy enemy) {
        if (getInvincibilityStatus()) {
            enemy.kill();
        } else {
            if (isSwordEquipped()) {
                useSword();
                enemy.kill();
            } else {
                this.kill();
                return true;
            }
        }
        return false;
    }

    /**
     * Kill player and set alive status to false
     */
    public void kill() {
        setAlive(false);
    }

    /**
     * Handle collisions between player and other entities
     * @param targetX x-coordinate of target
     * @param targetY y-coordinate of target
     * @return Value of whether entity can be collided with
     */
    public boolean collisionHandler(int targetX, int targetY) {
        ArrayList<Entity> entities = dungeon.getEntities();
        boolean ret = true;
        for (Entity e: entities) {
            if (e != null && !(e instanceof Switch) && e.getX() == targetX && e.getY() == targetY) {
                if (!e.interact(this)) ret = false;
            }
        }
        return ret;
    }

    /**
     * Get the last item added to inventory
     * @return last item added to inventory
     */
    public Entity getLastItem() {
        return lastItem;
    }

    /**
     * Add enemy to subscribers
     * @param e Enemy to be added
     */
    public void subscribe(Enemy e) {
        if (!subscribers.contains(e))
            subscribers.add(e);
    }

    /**
     * Subscribe to inventory
     * @param c Controller to be subscribed
     */
    public void subscribeToInventory(DungeonController c) {
        inventory.subscribe(c);
    }

    /**
     * Gives position of player to all subscribers
     */
    public void positionBroadcast() {
        int[] coords = {getX(), getY()};
        for (Enemy e: subscribers) {
            e.update(coords, getInvincibilityStatus());
        }
    }

    /**
     * Get player inventory
     * @return Inventory of player
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Add item to inventory
     * @param e Entity picked up by player
     */
    public void obtain(Entity e) {
        lastItem = e;
        inventory.add(e);
    }

    /**
     * Use key in inventory
     */
    public void useKey() {
        inventory.useItem("Key");
    }

    public void useSword() {
        inventory.useItem("Sword");
    }
    
    /**
     * Use item in inventory
     * @param item Item to be used
     */
    public void useItem(String item) {
        inventory.useItem(item);
    }

    /**
     * Get id of key currently in inventory
     * @return key id
     */
    public int getKeyId() {
        if (keyEquipped.get())
            return inventory.getKey().getID();
        return -1;
    }

    /**
     * Set keyEquipped to a given value
     * @param value Value of whether a key is currently equipped
     */
    public void setKeyEquipped(boolean value) {
        keyEquipped.set(value);
    }

    /**
     * Set swordEquipped to a given value
     * @param bool Value of whether a sword is currently equipped
     */
    public void setSwordEquipped(boolean bool) {
        swordEquipped.set(bool);
    }

    /**
     * Check whether a sword is currently equipped
     * @return Value of whether a sword is currently equipped
     */
    public boolean isSwordEquipped() {
        return swordEquipped.get();
    }

    /**
     * Get value of whether player is alive
     * @return Alive status of player
     */
    public BooleanProperty getAlive() {
        return alive;
    }

    /**
     * Check whether player is currently alive
     * @return Alive status of player
     */
    public boolean isAlive() {
        return alive.get();
    }

    /**
     * Set alive status of player
     * @param status Alive status of player
     */
    public void setAlive(boolean status) {
        alive.set(status);
    }

    /**
     * Stores time the player becomes invincible
     * @param dateTime time
     */
    public void setInvincible(LocalDateTime dateTime) {
        setInvincibleState(new InvincibleState(this, dateTime));
    }

    /**
     * Sets player to invincibility state given
     * @param state State to set player.
     */
    public void setInvincibleState(InvincibleStateInterface state) {
        this.invincibleState = state;
    }

    /**
     * Checks if the last time player became invincible was 30 seconds ago.
     * @return Boolean returns True if player became invincible 30 seconds ago from time of call.
     */
    public boolean getInvincibilityStatus() {
        return invincibleState.isInvincible();
    }

    /**
     * Go to next tick of invincibility potion in inventory
     */
    public void invincibilityTick() {
        inventory.invincibilityTick();
    }

}
