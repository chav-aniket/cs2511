package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Boulder;
import unsw.dungeon.entities.Player;

public class BoulderTest {

    @Test
    public void boulderCreationTest() {
        Dungeon dungeon = new Dungeon(5, 5);
        Boulder boulder = new Boulder(dungeon, 3, 3);
        dungeon.addEntity(boulder);
        assertEquals(3, boulder.getX());
        assertEquals(3, boulder.getY());
    }

    @Test
    public void boulderInteractionPlayerTest() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 3, 2);
        Boulder boulder = new Boulder(dungeon, 3, 3);

        dungeon.addEntity(boulder);

        player.moveDown();
        assertEquals(3, player.getX());
        assertEquals(3, player.getY());
        assertEquals(3, boulder.getX());
        assertEquals(4, boulder.getY());
    }


    @Test
    public void boulderInteractionBoulderTest() {
        Dungeon dungeon = new Dungeon(7, 7);
        Player player = new Player(dungeon, 3, 2);
        Boulder boulder = new Boulder(dungeon, 3, 3);
        Boulder boulder2 = new Boulder(dungeon, 3, 4);

        dungeon.addEntity(boulder);
        dungeon.addEntity(boulder2);

        player.moveDown();
        assertEquals(3, player.getX());
        assertEquals(2, player.getY());
    }

}
