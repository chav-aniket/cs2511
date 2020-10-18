package test;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Key;
import unsw.dungeon.entities.Player;

import static org.junit.jupiter.api.Assertions.*;

public class KeyTest {
    @Test
    public void keyCreationTest() {
        // test key creation
        Dungeon dungeon = new Dungeon(5, 5);
        Key key = new Key(dungeon, 3, 3);
        key.setID(0);
        dungeon.addEntity(key);
        assertEquals(3, key.getX());
        assertEquals(3, key.getY());
        assertEquals(0, key.getID());
    }

    @Test
    public void keyPlayerInteraction() {
        // test player picks up key
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 3, 2);
        Key key = new Key(dungeon, 3, 3);
        key.setID(0);
        dungeon.addEntity(key);

        player.moveDown();
        assertNotEquals(3, key.getX());
        assertNotEquals(3, key.getY());
        assertEquals(3, player.getX());
        assertEquals(3, player.getY());
        assertNotNull(player.getInventory().getKey());

    }

    @Test
    public void keyPlayerInteractionMultiple() {
        // test player only picks up one key
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 3, 2);
        Key key = new Key(dungeon, 3, 3);
        Key key2 = new Key(dungeon, 3, 4);
        key.setID(0);
        key2.setID(1);
        dungeon.addEntity(key);
        dungeon.addEntity(key2);

        player.moveDown();
        player.moveDown();
        assertNotEquals(3, key.getX());
        assertNotEquals(3, key.getY());
        assertEquals(3, key2.getX());
        assertEquals(4, key2.getY());
        assertEquals(3, player.getX());
        assertEquals(4, player.getY());
        assertNotNull(player.getInventory().getKey());
        assertEquals(player.getInventory().getKey().getID(), 0);
    }
}
