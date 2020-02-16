package unsw.dungeon.controller;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import unsw.dungeon.maker.DungeonMaker;
import unsw.dungeon.view.MenuScreen;

public class DungeonMakerController {
	@FXML
	private GridPane square;
	@FXML
	private Button backButton;
	@FXML
	private Button createButton;
	@FXML
	private ComboBox<String> actionBox;
	@FXML
	private TextField fileNameTextBox;
	@FXML
	private ComboBox<String> goal;

	private DungeonMaker maker;

	private MenuScreen menu;

	public DungeonMakerController() {
		this.maker = new DungeonMaker();
	}

	@FXML
	public void handleBackButton(ActionEvent event) {
		this.menu.start();
	}

	@FXML
	public void handleCreateButton(ActionEvent event) {
		if (fileNameTextBox.getText() != null) {
			maker.writeToFile(fileNameTextBox.getText());
		}
	}

	@FXML
	public void handleApplyButton(ActionEvent event) {
		if (actionBox.getValue() == null) {
			return;
		}
		if (actionBox.getValue().equals("Clear")) {
			maker.clearDungeon();
		} else if (actionBox.getValue().equals("Walls on 4 sides")) {
			maker.addSurroundingWalls();
		}
	}

	public void setMenuScreen(MenuScreen screen) {
		this.menu = screen;
	}

	@FXML
	public void initialize() {
		String entities[] = { "player", "wall", "exit", "treasure", "door", "key", "boulder", "switch", "bomb", "enemy",
				"hound", "invincibility", "wizard", "princess", "up_floor", "down_floor", "stone", "invisible" };
		Arrays.sort(entities);
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				ComboBox<String> box = new ComboBox<String>(FXCollections.observableArrayList(entities));
				box.setMaxWidth(90);
				box.setEditable(true);
				box.setPromptText(null);
				box.accessibleTextProperty();
				maker.cellProperty(i, j).bindBidirectional(box.valueProperty());
				square.add(box, i, j);
			}
		}
		// Options to action box
		actionBox.getItems().addAll("Clear", "Walls on 4 sides");
		// Options to goal box
		goal.getItems().addAll("exit", "enemies", "boulders", "treasure", "wizard", "princess");
		goal.getSelectionModel().select(0);
		maker.getGoalProperty().bind(goal.valueProperty());
	}
}
