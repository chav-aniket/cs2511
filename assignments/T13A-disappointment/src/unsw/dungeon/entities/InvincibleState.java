package unsw.dungeon.entities;

import java.time.LocalDateTime;

public class InvincibleState implements InvincibleStateInterface {
    private Player player;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Set player as invincible for a given time.
     * @param player Player to set as invincible
     * @param startTime Time to start invisibility state.
     */
    public InvincibleState(Player player, LocalDateTime startTime) {
        this.player = player;
        this.startTime = startTime;
        if (startTime != null) {
            this.endTime = startTime.plusSeconds(30);
        }
    }

    /**
     * Check if player is invincible.
     * @return Boolean True if player invincible, False otherwise
     */
    public boolean isInvincible() {
        if (startTime == null || (endTime.isBefore(LocalDateTime.now()))) {
            player.setInvincibleState(new NonInvincibleState(player));
            return false;
        }
        return true;
    }
}
