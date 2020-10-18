package unsw.dungeon.goals;

import unsw.dungeon.Dungeon;
import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.Switch;

public class GoalSwitch implements GoalInterface {
    private Dungeon dungeon;

    public GoalSwitch(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

	/**
	 * Check whether switch goal is complete
	 * @return Value of whether goal is complete
	 */
	@Override
    public boolean isComplete() {
		for (Entity entity : dungeon.getEntities()) {
			if (entity instanceof Switch) {
				Switch floorSwitch = (Switch) entity;
				if (!floorSwitch.isPressed())
					return false;
			}
		}
		return true;
	}
}