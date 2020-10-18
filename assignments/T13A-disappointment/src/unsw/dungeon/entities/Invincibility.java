package unsw.dungeon.entities;

import unsw.dungeon.Dungeon;

import java.time.LocalDateTime;

public class Invincibility extends Entity {
    
    private Dungeon dungeon;

    /**
     * Create an invincibility potion positioned in square (x,y)
     * @param x x-coordinate of potion
     * @param y y-coordinate of potion
     */
    public Invincibility(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    /**
     * Interaction between player and potion
     * @param c Player interacting with potion
     * @return Value of whether interaction is successful
     */
    @Override
    public Boolean interact(CharacterInterface c) {
        dungeon.pickup(this);
        use((Player) c);
        return true;
    }

    /**
     * Player to use invincibility potion
     * @param player Player using potion
     */
    private void use(Player player) {
        player.setInvincible(LocalDateTime.now());
    }
}