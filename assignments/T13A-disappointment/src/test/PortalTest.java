package test;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Player;
import unsw.dungeon.entities.Portal;

import static org.junit.jupiter.api.Assertions.*;

public class PortalTest {
    @Test
    public void portalCreationTest() {
        Dungeon dungeon = new Dungeon(5, 5);
        Portal portal = new Portal(dungeon, 0, 3, 3);
        dungeon.addEntity(portal);
        assertEquals(3, portal.getX());
        assertEquals(3, portal.getY());
        // TODO: check portal ID?
    }

    @Test
    public void portalInteractionPlayerTest() {
        // test a pair of portals and interaction with player
        Dungeon dungeon = new Dungeon(5, 5);
        Portal portal1 = new Portal(dungeon, 0, 2, 2);
        Portal portal2 = new Portal(dungeon, 0, 4, 4);
        Player player = new Player(dungeon, 3, 2);
        dungeon.addEntity(portal1);
        dungeon.addEntity(portal2);

        player.moveLeft();
        assertEquals(4, player.getX());
        assertEquals(4, player.getY());
    }

    @Test
    public void portalMultipleTest() {
        // test multiple pairs of portals
        Dungeon dungeon = new Dungeon(5, 5);
        Portal portal1 = new Portal(dungeon, 0, 2, 2);
        Portal portal2 = new Portal(dungeon, 0, 4, 4);
        Portal portal3 = new Portal(dungeon, 1, 2, 4);
        Portal portal4 = new Portal(dungeon, 1, 4, 2);
        Player player = new Player(dungeon, 3, 2);
        dungeon.addEntity(portal1);
        dungeon.addEntity(portal2);
        dungeon.addEntity(portal3);
        dungeon.addEntity(portal4);

        player.moveLeft();
        assertEquals(4, player.getX());
        assertEquals(4, player.getY());

        player.moveUp();
        player.moveUp();
        assertEquals(2, player.getX());
        assertEquals(4, player.getY());
    }

    // TODO test portals with other entities?
}
