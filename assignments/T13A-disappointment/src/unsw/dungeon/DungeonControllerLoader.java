package unsw.dungeon;

import unsw.dungeon.entities.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image treasureImage;
    private Image doorImage;
    private Image keyImage;
    private Image boulderImage;
    private Image switchImage;
    private Image portalImage;
    private Image enemyImage;
    private Image swordImage;
    private Image invincibilityImage;
    private HashMap<String, ImageView> itemImages;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image((new File("images/player.png")).toURI().toString());
        wallImage = new Image((new File("images/wall.png")).toURI().toString());
        exitImage = new Image((new File("images/exit.png")).toURI().toString());
        doorImage = new Image((new File("images/closed_door.png")).toURI().toString());
        boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
        switchImage = new Image((new File("images/switch.png")).toURI().toString());
        portalImage = new Image((new File("images/portal.png")).toURI().toString());
        enemyImage = new Image((new File("images/elf.png")).toURI().toString());

        itemImages = new HashMap<String, ImageView>();
        treasureImage = new Image((new File("images/treasure.png")).toURI().toString());
        itemImages.put("Treasure", new ImageView(treasureImage));
        keyImage = new Image((new File("images/key.png")).toURI().toString());
        itemImages.put("Key", new ImageView(keyImage));
        swordImage = new Image((new File("images/sword.png")).toURI().toString());
        itemImages.put("Sword", new ImageView(swordImage));
        invincibilityImage = new Image((new File("images/invincibility.png")).toURI().toString());
        itemImages.put("Invincibility", new ImageView(invincibilityImage));
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        view.setAccessibleRoleDescription("Player");
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        view.setAccessibleRoleDescription("Wall");
        addEntity(wall, view);
    }

    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        view.setAccessibleRoleDescription("Exit");
        addEntity(exit, view);
    }

    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        view.setAccessibleRoleDescription("Treasure");
        addEntity(treasure, view);
    }

    
    public void onLoad(Door door) {
        ImageView view = new ImageView(doorImage);
        view.setAccessibleRoleDescription("Door");
        addEntity(door, view);
    }
    
    public void onLoad(Key key) {
        ImageView view = new ImageView(keyImage);
        view.setAccessibleRoleDescription("Key");
        addEntity(key, view);
    }

    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        view.setAccessibleRoleDescription("Boulder");
        addEntity(boulder, view);
    }

    public void onLoad(Switch floorSwitch) {
        ImageView view = new ImageView(switchImage);
        view.setAccessibleRoleDescription("Switch");
        addEntity(floorSwitch, view);
    }

    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        view.setAccessibleRoleDescription("Portal");
        addEntity(portal, view);
    }

    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        view.setAccessibleRoleDescription("Enemy");
        addEntity(enemy, view);
    }

    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        view.setAccessibleRoleDescription("Sword");
        addEntity(sword, view);
    }

    public void onLoad(Invincibility invincibility) {
        ImageView view = new ImageView(invincibilityImage);
        view.setAccessibleRoleDescription("Invincibility");
        addEntity(invincibility, view);
    }

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities, itemImages);
    }


}
