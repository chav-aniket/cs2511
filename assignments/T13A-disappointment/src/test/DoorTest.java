package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Door;
import unsw.dungeon.entities.Key;
import unsw.dungeon.entities.Player;

public class DoorTest {
    @Test
    public void doorCreationTest() {
        // test door creation
        Dungeon dungeon = new Dungeon(5, 5);
        Door door = new Door(dungeon, 3, 3);
        door.setID(0);
        dungeon.addEntity(door);

        assertEquals(3, door.getX());
        assertEquals(3, door.getY());
        assertEquals(0, door.getID());
        // TODO: assert door closed
    }

    @Test
    public void doorPlayerInteraction() {
        // test player interaction with door without a key
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 3, 2);
        Door door = new Door(dungeon, 3, 3);
        door.setID(0);
        dungeon.addEntity(door);

        player.moveDown();
        assertEquals(3, door.getX());
        assertEquals(3, door.getY());
        assertEquals(0, door.getID());
        assertEquals(3, player.getX());
        assertEquals(3, player.getY());
        // TODO: assert door closed
    }

    @Test
    public void doorPlayerWrongKeyInteraction() {
        // test player interaction with door with key of different ID
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 3, 2);
        Key key = new Key(dungeon, 3, 3);
        Door door = new Door(dungeon, 3, 4);
        door.setID(0);
        key.setID(1);
        dungeon.addEntity(door);
        dungeon.addEntity(key);

        player.moveDown();
        player.moveDown();

        assertEquals(3, door.getX());
        assertEquals(4, door.getY());
        assertEquals(3, player.getX());
        assertEquals(4, player.getY());
        // TODO: assert door closed
        // TODO: check key still in inventory
    }

    @Test
    public void doorPlayerCorrectKeyInteraction() {
        // test player interaction with door with key of correct ID
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 3, 2);
        Key key = new Key(dungeon, 3, 3);
        Door door = new Door(dungeon, 3, 4);
        door.setID(0);
        key.setID(0);
        dungeon.addEntity(door);
        dungeon.addEntity(key);

        player.moveDown();
        player.moveDown();

        assertEquals(3, door.getX());
        assertEquals(4, door.getY());
        assertEquals(3, player.getX());
        assertEquals(4, player.getY());
        // TODO: assert door open
        // TODO: check key gone from inventory
    }
}
