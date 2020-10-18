package unsw.dungeon;

import java.util.HashMap;

public class Inventory {
    private HashMap<String, Entity> items;

    public Inventory() {
        this.items = new HashMap<String, Entity>();
    }

    public void add(Key k) {
        items.put("key", k);
    }

    public void add(Sword s) {
        items.put("sword", s);
    }

    public void add(Invincibility i) {
        items.put("invincibility", i);
    }

    // public void add(Treasure t) {
    //     if (!items.containsKey("treasure"))
    //         items.put("treasure", new ArrayList<Treasure>());
    //     items.put("treasure", items.get("treasure").add(t));
    // }

    public void remove(String s) {
        items.remove(s);
    }

    public Key getKey() {
        return (Key) items.get("key");
    }

    public Sword hasSword() {
        Sword sword = (Sword) items.get("sword");
        return sword;
    }
}