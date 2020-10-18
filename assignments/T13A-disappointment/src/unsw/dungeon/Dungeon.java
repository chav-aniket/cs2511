/**
 *
 */
package unsw.dungeon;

import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.Enemy;
import unsw.dungeon.entities.Player;
import unsw.dungeon.goals.GoalInterface;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {
    private int width, height;
    private ArrayList<Entity> entities;
    private Player player;
    private BooleanProperty dungeonCompleted;
    private Graph graph;
    private GoalInterface goal = null;
    private ArrayList<Enemy> movingBodies;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.graph = new Graph(width, height);
        this.entities = new ArrayList<>();
        this.player = null;
        this.dungeonCompleted = new SimpleBooleanProperty(false);
        this.movingBodies = new ArrayList<>();
    }

    /**
     * Get dungeon width
     * @return dungeon width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get dungeon height
     * @return dungeon height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get Player object
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get Graph object
     * @return Graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Get list of entities
     * @return list of entities
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * Set Player object
     * @param player Player to be set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Set Goal object
     * @param goal Goal to be set
     */
    public void setGoal(GoalInterface goal) {
        this.goal = goal;
    }

    /**
     * Check if goal is completed
     */
    public void isGoalComplete() {
        setDungeonCompleted(goal.isComplete());
    }

    /**
     * Set whether dungeon is completed
     * @param bool value to indicate whether dungeon is completed
     */
    public void setDungeonCompleted(boolean bool) {
        dungeonCompleted.set(bool);
    }

    /**
     * Get value of whether dungeon is completed
     * @return status of dungeon completion
     */
    public BooleanProperty getDungeonCompleted() {
        return dungeonCompleted;
    }

    /**
     * Add an entity to the dungeon
     * @param entity Entity to be added
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * Detect whether entity exists at given coordinates
     * @param x x-coordinate to be checked
     * @param y y-coordinate to be checked
     * @return entity at which there is a collision
     */
    public Entity detectCollision(int x, int y) {
        for (Entity e: entities) {
            if (e != null && e.getY() == y && e.getX() == x) {
                if (e.getClass().getSimpleName().equals("Switch")) continue;
                return e;
            }
        }
        return null;
    }

    /**
     * Remove an entity from dungeon
     * @param e Entity to be removed
     */
    public void removeEntity(Entity e) {
        entities.remove(e);
    }

    /**
     * Pick up and item from the map
     * @param e Entity to be picked up
     */
    public void pickup(Entity e) {
        removeEntity(e);
        player.obtain(e);
    }

    /**
     * Add enemies to list of moving bodies
     * @param e Enemy to be added
     */
    public void subscribe(Enemy e) {
        if (!movingBodies.contains(e))
            movingBodies.add(e);
    }

    /**
     * Update moving bodies in dungeon
     */
    public void updateMovingBodies() {
        for (Enemy e: movingBodies) {
            e.tick();
        }
    }
}
