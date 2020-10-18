package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;
import unsw.dungeon.entities.Exit;

public class ExitTest {
    @Test
    public void exitCreationTest() {
        // test exit creation
        Dungeon dungeon = new Dungeon(5, 5);
        Exit exit = new Exit(dungeon, 3, 3);
        dungeon.addEntity(exit);
        assertEquals(3, exit.getX());
        assertEquals(3, exit.getY());
    }

    // TODO: exit when goals are incomplete

    // TODO: exit when goals are complete
}

