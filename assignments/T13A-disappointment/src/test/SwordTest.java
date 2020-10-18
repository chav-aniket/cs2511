package test;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Enemy;
import unsw.dungeon.entities.Player;
import unsw.dungeon.entities.Sword;

import static org.junit.jupiter.api.Assertions.*;

public class SwordTest {
    @Test
    public void swordCreationTest() {
        Dungeon dungeon = new Dungeon(5, 5);
        Sword sword = new Sword(dungeon, 3, 3);
        dungeon.addEntity(sword);
        assertEquals(3, sword.getX());
        assertEquals(3, sword.getY());
    }

    @Test
    public void swordPlayerInteraction() {
        Dungeon dungeon = new Dungeon(5, 5);
        Sword sword = new Sword(dungeon, 3, 3);
        Player player = new Player(dungeon, 3, 2);
        dungeon.addEntity(sword);

        player.moveDown();
        assertNotEquals(3, sword.getX());
        assertNotEquals(3, sword.getY());
        assertEquals(3, player.getX());
        assertEquals(3, player.getY());

        assertNotNull(player.getInventory().getSword());
        assertEquals(5, sword.getUses());
    }

    @Test
    public void SwordEnemyInteraction() {
        Dungeon dungeon = new Dungeon(5, 7);
        Sword sword = new Sword(dungeon, 3, 3);
        Player player = new Player(dungeon, 3, 2);
        Enemy enemy = new Enemy(dungeon, 3, 4);
        Enemy enemy2 = new Enemy(dungeon, 3, 5);
        Enemy enemy3 = new Enemy(dungeon, 3, 6);
        Enemy enemy4 = new Enemy(dungeon, 4, 6);
        Enemy enemy5 = new Enemy(dungeon, 4, 5);

        dungeon.addEntity(sword);
        dungeon.addEntity(enemy);
        dungeon.addEntity(enemy2);
        dungeon.addEntity(enemy3);
        dungeon.addEntity(enemy4);
        dungeon.addEntity(enemy5);

        player.moveDown();
        assertNotNull(player.getInventory().getSword());
        player.moveDown();
        assertEquals(4, sword.getUses());
        player.moveDown();
        assertEquals(3, sword.getUses());
        player.moveDown();
        assertEquals(2, sword.getUses());
        player.moveRight();
        assertEquals(1, sword.getUses());
        player.moveUp();
        assertEquals(0, sword.getUses());
        assertNull(player.getInventory().getSword());
    }
}
