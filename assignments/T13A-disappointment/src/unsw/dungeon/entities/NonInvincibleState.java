package unsw.dungeon.entities;

public class NonInvincibleState implements InvincibleStateInterface {
    Player player;

    public NonInvincibleState(Player player) {
        this.player = player;
    }

    /**
     * @return player is not invincible
     */
    public boolean isInvincible() {
        return false;
    }
}
