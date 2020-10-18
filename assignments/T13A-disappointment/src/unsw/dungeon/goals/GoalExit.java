package unsw.dungeon.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.Exit;

public class GoalExit implements GoalInterface{
	private Dungeon dungeon;
	
	public GoalExit(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

	/**
	 * Check whether Exit goal is complete
	 * @return Value of whether goal is complete
	 */
	@Override
    public boolean isComplete() {
		for (Entity entity : dungeon.getEntities()) {
			if (entity instanceof Exit) {
				Exit exit = (Exit) entity;
				if (!exit.isPressed()) {
					return false;
				}
			}
		}
		return true;
	}
}