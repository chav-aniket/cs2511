package unsw.dungeon.goals;

import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.Enemy;
import unsw.dungeon.Dungeon;

public class GoalEnemy implements GoalInterface {
    private Dungeon dungeon;

    public GoalEnemy(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

	/**
	 * Check whether Enemy goal is complete
	 * @return Value of whether goal is complete
	 */
	@Override
    public boolean isComplete() {
		for (Entity entity : dungeon.getEntities()) {
			if (entity instanceof Enemy) {
				return false;
			}
		}
		return true;
	}
}