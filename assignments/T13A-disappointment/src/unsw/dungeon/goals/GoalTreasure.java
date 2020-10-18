package unsw.dungeon.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.Treasure;

public class GoalTreasure implements GoalInterface {
    private Dungeon dungeon;

    public GoalTreasure(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

	/**
	 * Check whether Treasure goal is complete
	 * @return Value of whether goal is complete
	 */
	@Override
    public boolean isComplete() {
		for (Entity entity : dungeon.getEntities()) {
			if (entity instanceof Treasure) {
				return false;
			}
		}
		return true;
	}
}