package unsw.dungeon;

import unsw.dungeon.entities.Entity;
import unsw.dungeon.entities.Player;
import unsw.dungeon.entities.Enemy;
import unsw.dungeon.entities.Door;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    @FXML
    private GridPane inventoryGrid;

    private List<ImageView> initialEntities;

    private HashMap<String, ImageView> itemImages;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities, HashMap<String, ImageView> itemImages) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.player.subscribeToInventory(this);
        this.initialEntities = new ArrayList<>(initialEntities);
        this.itemImages = itemImages;

        dungeon.getDungeonCompleted().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Dungeon Complete");
                alert.setHeaderText("You Won!!");
                alert.setContentText("Click Ok to end game.");

                alert.setOnHidden(arg0 -> squares.getScene().getWindow().fireEvent(
                    new WindowEvent(
                        squares.getScene().getWindow(),
                        WindowEvent.WINDOW_CLOSE_REQUEST
                    )
                ));

                alert.show();
            }
        });

        for (Entity e: dungeon.getEntities()) {
            if (e instanceof Door) {
                Door door = (Door) e;
                door.getIsOpen().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        Image openDoorImage = new Image((new File("images/open_door.png")).toURI().toString());
                        ImageView openDoor = new ImageView(openDoorImage);
                        removeImage(door);
                        squares.add(openDoor, door.getX(), door.getY());
                    }
                });
            } else if (e instanceof Enemy) {
                Enemy enemy = (Enemy) e;
                enemy.getAlive().addListener((observable, oldValue, newValue) -> {
                    if (!newValue) {
                        removeImage(enemy);
                    }
                });
            }
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            player.positionBroadcast();
            player.invincibilityTick();
            dungeon.updateMovingBodies();
            dungeon.isGoalComplete();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Initialise dungeon
     */
    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                inventoryGrid.add(new ImageView(ground), x, y);
            }
        }

        dungeon.getPlayer().getAlive().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Game Over");
                alert.setHeaderText("You Died!");
                alert.setContentText("Click Ok to end game.");

                alert.setOnHidden(arg0 -> squares.getScene().getWindow().fireEvent(
                    new WindowEvent(
                        squares.getScene().getWindow(),
                        WindowEvent.WINDOW_CLOSE_REQUEST
                    )
                ));

                alert.show();
            }
        });

        int inventoryRow = 0;
        for (ImageView i: itemImages.values()) {
            Label itemCount = new Label(Integer.toString(0));
            itemCount.setTextFill(Color.web("#FFFFFF"));
            itemCount.setAlignment(Pos.CENTER_LEFT);
            itemCount.setMinSize(32, 32);
            itemCount.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
            inventoryGrid.add(i, 0, inventoryRow);
            inventoryGrid.add(itemCount, 1, inventoryRow);
            inventoryRow+=2;
        }

    }

    /**
     * Handle input from player
     * @param event Action inputted
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp(); break;
        case DOWN:
            player.moveDown(); break;
        case LEFT:
            player.moveLeft(); break;
        case RIGHT:
            player.moveRight(); break;
        default:
            break;
        }
        if (player.getLastItem() != null) {
            removeImage(player.getLastItem());
        }
    }

    /**
     * Remove an image from the map
     * @param entity Entity to be removed
     */
    private void removeImage(Entity entity) {
        if (entity == null) return;
    	int col = entity.getX();
    	int row = entity.getY();

    	ObservableList<Node> childrens = squares.getChildren();
    	for(Node node : childrens) {
    	    if(node instanceof ImageView && GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                if (entity.getClass().getSimpleName().equals(node.getAccessibleRoleDescription())) {
                    squares.getChildren().remove(node);
    	    		break;
    	    	}
    	    }
        }
    }

    /**
     * Update the inventory interface
     * @param item Item to be modified
     * @param change Change to number of items for given entity
     */
    public void updateInventoryUI(Entity item, int change) {
        if (item == null) return;
        ImageView i = itemImages.get(item.getClass().getSimpleName());
        int row = 0, col = 1;
        for (ImageView im: itemImages.values()) {
            if (im.equals(i)) break;
            row += 2;
        }
        
        ObservableList<Node> childrens = inventoryGrid.getChildren();
    	for(Node node : childrens) {
    	    if(node instanceof Label && inventoryGrid.getRowIndex(node) == row && inventoryGrid.getColumnIndex(node) == col) {
                Label old = (Label) node;
                int oldNum = Integer.parseInt(old.getText());
                inventoryGrid.getChildren().remove(node);
                int newNum = oldNum + change;
                Label itemCount = new Label(Integer.toString(newNum));
				itemCount.setTextFill(Color.web("#FFFFFF"));
				itemCount.setAlignment(Pos.CENTER_LEFT);
				itemCount.setMinSize(32, 32);
                itemCount.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 14));
                inventoryGrid.add(itemCount, col, row);
                break;
    	    }
        }
    }

}

