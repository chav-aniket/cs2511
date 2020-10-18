package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Player;
import unsw.dungeon.entities.Wall;

public class PlayerTest {

    @Test
    public void playerCreationTest() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 3, 3);
        assertEquals(3, player.getX());
        assertEquals(3, player.getY());
    }

    @Test
    public void playerMovementTest() {
        // test moving player into empty space
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 3, 3);

        // move player down
        player.moveDown();
        assertEquals(3, player.getX());
        assertEquals(4, player.getY());

        // move player right
        player.moveRight();
        assertEquals(4, player.getX());
        assertEquals(4, player.getY());

        // move player left
        player.moveLeft();
        assertEquals(3, player.getX());
        assertEquals(4, player.getY());

        // move player up
        player.moveUp();
        assertEquals(3, player.getX());
        assertEquals(3, player.getY());
    }

    @Test
    public void playerInteractionWallTest() {
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 3, 3);
        Wall wall = new Wall(3, 4);
        dungeon.addEntity(wall);

        player.moveDown();
        assertEquals(3, player.getX());
        assertEquals(3, player.getY());

    }
    
}

