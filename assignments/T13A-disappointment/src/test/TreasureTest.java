package test;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Player;
import unsw.dungeon.entities.Treasure;

import static org.junit.jupiter.api.Assertions.*;

public class TreasureTest {
    @Test
    public void treasureCreationTest() {
        Dungeon dungeon = new Dungeon(5, 5);
        Treasure treasure = new Treasure(dungeon, 3, 3);
        assertEquals(3, treasure.getX());
        assertEquals(3, treasure.getY());
    }

    @Test
    public void treasureInteractionPlayer() {
        Dungeon dungeon = new Dungeon(5, 5);
        Treasure treasure = new Treasure(dungeon, 3, 3);
        Player player = new Player(dungeon, 3, 2);
        dungeon.addEntity(treasure);

        player.moveDown();
        assertEquals(3, player.getX());
        assertEquals(3, player.getY());
        assertNotEquals(3, treasure.getX());
        assertNotEquals(3, treasure.getY());
    }

}
