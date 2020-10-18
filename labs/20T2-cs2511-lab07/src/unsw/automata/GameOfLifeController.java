package unsw.automata;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;

/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLifeController {

    @FXML
    private GridPane grid;

    @FXML
    private Button tick;

    @FXML
    private Button play;

    @FXML
	private Button stop;
	
	private Timeline timeline;

    private GameOfLife gol;

    public GameOfLifeController() {
        gol = new GameOfLife();
        grid = new GridPane();
        timeline  = new Timeline(); 
		timeline.setCycleCount(Timeline.INDEFINITE);
		EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                 gol.tick();
            }
        };
		KeyFrame playFrame = new KeyFrame(Duration.millis(500), onFinished);
		timeline.getKeyFrames().add(playFrame);
    }

    @FXML
    public void initialize() {
        for (int x = 0; x < gol.getSize(); x++) {
            for (int y = 0; y < gol.getSize(); y++) {
                CheckBox check = new CheckBox();
                check.setAlignment(Pos.CENTER);
                check.selectedProperty().bindBidirectional(gol.cellProperty(x, y));
                grid.add(check, x, y);
            }
        }
    }

    @FXML
    public void handleTick(ActionEvent event) {
        gol.tick();
    }

    @FXML
    public void handlePlay(ActionEvent event) {
        switch (play.getText()) {
            case "Play":
                timeline.play();
                play.setText("Stop");
                break;
            case "Stop":
                timeline.stop();
                play.setText("Play");
                break;
            default:
                gol.resetBoard();
                break;
        }
    }

    @FXML
    public void handleReset(ActionEvent event) {
        gol.resetBoard();
    }
}

