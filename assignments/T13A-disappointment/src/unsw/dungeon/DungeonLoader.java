package unsw.dungeon;

import unsw.dungeon.entities.*;
import unsw.dungeon.goals.*;

import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {
    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        JSONObject goals = json.getJSONObject("goal-condition");
        dungeon.setGoal(loadGoals(dungeon, goals));

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    /**
     * Given a goalType, creates and returns a new Goal of that type.
     * @param goalType Type of goal
     * @param dungeon Dungeon instance of which goals are to be added in
     * @return Goal object relevant to goal
     */
    private GoalInterface getGoal(String goalType, Dungeon dungeon) {
        GoalInterface goal = null;
        switch (goalType) {
            case "exit":
                goal = new GoalExit(dungeon);
                break;
            case "enemies":
                goal = new GoalEnemy(dungeon);
                break;
            case "boulders":
                goal = new GoalSwitch(dungeon);
                break;
            case "treasure":
                goal = new GoalTreasure(dungeon);
                break;
        }
        return goal;
    }

    /**
     * Process goals in dungeon
     * @param dungeon Dungeon instance of which goals are to be added in
     * @param goals JSONObject of goals
     * @return  GoalComposite A tree of goals
     */
    private GoalInterface loadGoals(Dungeon dungeon, JSONObject goals) {
        String goalType = goals.getString("goal");
        GoalInterface goal = getGoal(goalType, dungeon);
        if (goal == null) {
            GoalType type = GoalType.OR;
            if (goalType.equals("AND"))
                type = GoalType.AND;

            JSONArray subgoalsArray = goals.getJSONArray("subgoals");
            ArrayList<GoalInterface> subgoals = new ArrayList<GoalInterface>();
            for (int i = 0; i < subgoalsArray.length(); i++) {
                JSONObject json = subgoalsArray.getJSONObject(i);
                subgoals.add(loadGoals(dungeon, json));
            }
            goal = new GoalComposite(subgoals, type);
        }
        return goal;
    }

    /**
     * Load entities into dungeon
     * @param dungeon Dungeon of which entities belong to
     * @param json JSON Object of entity
     */
    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int keyDoorID = 0;
        int portalID = 0;
        if ("door".equals(type) || "key".equals(type)) {
            keyDoorID = json.getInt("id");
        } else if ("portal".equals(type)) {
            portalID = json.getInt("id");
        }

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
            Exit exit = new Exit(dungeon, x, y);
            onLoad(exit);
            entity = exit;
            break;
        case "treasure":
            Treasure treasure = new Treasure(dungeon, x, y);
            onLoad(treasure);
            entity = treasure;
            break;
        case "door":
            Door door = new Door(dungeon, x, y);
            door.setID(keyDoorID);
            onLoad(door);
            entity = door;
            break;
        case "key":
            Key key = new Key(dungeon, x, y);
            key.setID(keyDoorID);
            onLoad(key);
            entity = key;
            break;
        case "boulder":
            Boulder boulder = new Boulder(dungeon, x, y);
            onLoad(boulder);
            entity = boulder;
            break;
        case "switch":
            Switch floorSwitch = new Switch(dungeon, x, y);
            onLoad(floorSwitch);
            entity = floorSwitch;
            break;
        case "portal":
            Portal portal = new Portal(dungeon, portalID, x, y);
            onLoad(portal);
            entity = portal;
            break;
        case "enemy":
            Enemy enemy = new Enemy(dungeon, x, y);
            onLoad(enemy);
            entity = enemy;
            break;
        case "sword":
            Sword sword = new Sword(dungeon, x, y);
            onLoad(sword);
            entity = sword;
            break;
        case "invincibility":
            Invincibility invincibility = new Invincibility(dungeon, x, y);
            onLoad(invincibility);
            entity = invincibility;
            break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);
    public abstract void onLoad(Wall wall);
    public abstract void onLoad(Exit exit);
    public abstract void onLoad(Treasure treasure);
    public abstract void onLoad(Door door);
    public abstract void onLoad(Key key);
    public abstract void onLoad(Boulder boulder);
    public abstract void onLoad(Switch floorSwitch);
    public abstract void onLoad(Portal portal);
    public abstract void onLoad(Enemy enemy);
    public abstract void onLoad(Sword sword);
    public abstract void onLoad(Invincibility invincibility);

}
