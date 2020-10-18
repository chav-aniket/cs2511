package unsw.dungeon;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import unsw.dungeon.entities.Enemy;
import unsw.dungeon.entities.Entity;

/**
 * A JavaFX controller for the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class StartMenuController {

	@FXML
	private Button startButton;

	@FXML
	private ChoiceBox<String> levelSelect;

	int levelIndex = 0;
    List<String> levels = new ArrayList<String>();

	@FXML
	public void initialize() {
		
		File dir = new File("dungeons/");
		if (dir.isDirectory()) {
			for (File file : dir.listFiles()) {
				if (file.getName().endsWith(".json")) {
					levels.add(file.getName());
				}
			}
		}

		levelSelect.setItems(FXCollections.observableArrayList(levels));

		levelSelect.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				levelIndex = newValue.intValue();
			}
		});
		levelSelect.getSelectionModel().select(4);
	}

	@FXML
	public void handleStartButton(ActionEvent event) {
        try {
            String level = levels.get(levelIndex);
			Stage stage = new Stage();
            stage.setTitle(level);
			
			DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(level);

			DungeonController controller = dungeonLoader.loadController();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
			loader.setController(controller);
			Parent root = loader.load();
			Scene scene = new Scene(root);
			root.requestFocus();
			stage.setScene(scene);
			stage.show();
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace().toString());
		}
	}

}
