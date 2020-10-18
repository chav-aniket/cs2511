package test;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Invincibility;
import unsw.dungeon.entities.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class InvincibilityTest {
    @Test
    public void invincibilityCreationTest() {
        // test creation of invincibility potion
        Dungeon dungeon = new Dungeon(5, 5);
        Invincibility invincibility = new Invincibility(dungeon, 3, 3);
        dungeon.addEntity(invincibility);
        assertEquals(3, invincibility.getX());
        assertEquals(3, invincibility.getY());
    }

    @Test
    public void invincibilityInteractionTest() {
        // test potion consumption
        Dungeon dungeon = new Dungeon(5, 5);
        Invincibility potion = new Invincibility(dungeon, 3, 3);
        Player player = new Player(dungeon, 3, 2);
        dungeon.addEntity(potion);

        player.moveDown();
        // potion disappears after being consumed
        assertNotEquals(3, potion.getX());
        assertNotEquals(3, potion.getY());

        // TODO: check player invincible
        // TODO: check enemies run away
        // TODO: check potion lasts certain time only
    }
}
