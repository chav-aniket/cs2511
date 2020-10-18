package unsw.dungeon.entities;

import unsw.dungeon.DungeonController;

import java.util.ArrayList;

public class Inventory {
    private Player player;
    private ArrayList<Entity> items;
    private ArrayList<DungeonController> subscribers;
    private int invincibilityTime = 0;

    public Inventory(Player player) {
        this.player = player;
        this.items = new ArrayList<Entity>();
        this.subscribers = new ArrayList<DungeonController>();
    }

    /**
     * Add an entity to the inventory
     * @param e Entity to be added
     */
    public void add(Entity e) {
        if (e instanceof Key) {
            items.add((Key) e);
            broadcastInventoryState(e, 1);
        } else if (e instanceof Sword) {
            items.add((Sword) e);
            player.setSwordEquipped(true);
            broadcastInventoryState(e, 5);
        } else if (e instanceof Invincibility) {
            items.add((Invincibility) e);
            invincibilityTime += 30;
            broadcastInventoryState(e, 30);
        } else if (e instanceof Treasure) {
            items.add((Treasure) e);
            broadcastInventoryState(e, 1);
        }
    }

    /**
     * Get an item from inventory
     * @param string Item to be retrieved
     * @return Entity of item to be retrieved
     */
    private Entity getItem(String string) {
        for (Entity e: items) {
            if (string.equals(e.getClass().getSimpleName()))
                return e;
        }
        return null;
    }

    /**
     * Decrement invincibility time
     */
    public void invincibilityTick() {
        Entity i = getItem("Invincibility");
        if (invincibilityTime > 0) {
            invincibilityTime--;
            broadcastInventoryState(i, -1);
        }
    }

    public Key getKey() {
        return (Key) getItem("Key");
    }

    /**
     * Get sword from inventory
     * @return Sword to be retrieved
     */
    public Sword getSword() {
        return (Sword) getItem("Sword");
    }

    /**
     * Use an item in the inventory
     * @param item Item to be used
     * @return Item used
     */
    public Entity useItem(String item) {
        Entity e = getItem(item);
        if (e instanceof Sword) {
            Sword s = (Sword) e;
            s.use();
            if (s.getUses() == 0) {
                items.remove(s);
                player.setSwordEquipped(false);
            }
            broadcastInventoryState(e, -1);
        } else if (e instanceof Key) {
            items.remove(getKey());
            player.setKeyEquipped(false);
            broadcastInventoryState(e, -1);
        }
        return e;
    }

    /**
     * Add controller to subscribers
     * @param c Dungeon controller to be added
     */
    public void subscribe(DungeonController c) {
        if (!subscribers.contains(c))
            subscribers.add(c);
    }

    /**
     * Broadcast inventory state
     * @param e Entity to be updated
     * @param change Change to be made to entity in inventory
     */
    private void broadcastInventoryState(Entity e, int change) {
        for (DungeonController c: subscribers) {
            c.updateInventoryUI(e, change);
        }
    }

}