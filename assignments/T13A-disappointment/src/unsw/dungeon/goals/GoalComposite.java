package unsw.dungeon.goals;

import java.util.ArrayList;

public class GoalComposite implements GoalInterface {
    private ArrayList<GoalInterface> goals;
    private GoalType type;
    
    public GoalComposite(ArrayList<GoalInterface> goals, GoalType type) {
        this.goals = goals;
        this.type = type;
    }

    /**
     * Check whether goals are complete
     * @return Value of whether goals are complete
     */
    public boolean isComplete() {
        for (GoalInterface g: goals) {
            if (type == GoalType.AND && !g.isComplete()) {
                return false;
            } else if (type == GoalType.OR && g.isComplete()) {
                return true;
            }
        }

        if (type == GoalType.AND) {
            return true;
        } else if (type == GoalType.OR) {
            return false;
        }
        return false;
    }
}