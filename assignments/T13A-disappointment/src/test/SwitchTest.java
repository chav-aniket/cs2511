package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Boulder;
import unsw.dungeon.entities.Player;
import unsw.dungeon.entities.Switch;

public class SwitchTest {
    @Test
    public void switchCreationTest() {
        Dungeon dungeon = new Dungeon(5, 5);
        Switch floorSwitch = new Switch(dungeon, 3, 3);
        dungeon.addEntity(floorSwitch);
        assertEquals(3, floorSwitch.getX());
        assertEquals(3, floorSwitch.getY());
    }

    @Test
    public void switchPlayerInteraction() {
        // test switch and player in same tile
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 3,2);
        Switch floorSwitch = new Switch(dungeon, 3, 3);
        dungeon.addEntity(floorSwitch);

        player.moveDown();
        assertEquals(3, player.getX());
        assertEquals(3, player.getY());
        assertEquals(3, floorSwitch.getX());
        assertEquals(3, floorSwitch.getY());
    }

    @Test
    public void switchBoulderInteraction() {
        // test switch and boulder in same tile
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 3,2);
        Boulder boulder = new Boulder(dungeon, 3, 3);
        Switch floorSwitch = new Switch(dungeon, 3, 4);
        dungeon.addEntity(floorSwitch);
        dungeon.addEntity(boulder);
        // TODO: check switch activated
        player.moveDown();
    }

}
