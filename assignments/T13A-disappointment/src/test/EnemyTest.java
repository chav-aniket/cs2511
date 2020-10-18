package test;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Enemy;
import unsw.dungeon.entities.Player;

import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {
    @Test
    public void enemyCreationTest() {
        // test enemy creation
        Dungeon dungeon = new Dungeon(5, 5);
        Enemy enemy = new Enemy(dungeon, 3, 3);
        dungeon.addEntity(enemy);
        assertEquals(3, enemy.getX());
        assertEquals(3, enemy.getY());
    }

    @Test
    public void enemyInteractionPlayerTest() {
        // test enemy kills player
        Dungeon dungeon = new Dungeon(5, 5);
        Enemy enemy = new Enemy(dungeon, 3, 3);
        Player player = new Player(dungeon, 3, 2);
        dungeon.addEntity(enemy);
        dungeon.setPlayer(player);
        assertEquals(3, enemy.getX());
        assertEquals(3, enemy.getY());

        player.moveDown();
        assertFalse((BooleanSupplier) player.getAlive());
    }

    // TODO: test enemy follows player

    // TODO: test enemy when player is invincible
}
