package unsw.dungeon.controller;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;
import unsw.dungeon.DungeonApplication;
import unsw.dungeon.loader.DungeonScreenLoader;
import unsw.dungeon.view.DungeonMakerScreen;
import unsw.dungeon.view.DungeonScreen;

public class MenuController {

	@FXML
	private ChoiceBox<String> dungeonChoice;
	@FXML
	private Button freeStartButton;
	@FXML
	private Button storyStartButton;
	@FXML
	private Text warning;
	@FXML
	private Button createButton;
	// config part
	@FXML
	private Text soundInfo;
	@FXML
	private Slider soundSlider;
	@FXML
	private ChoiceBox<String> difficultyChoice;

	private DungeonMakerScreen makerScreen;

	@FXML
	public void initialize() {
		// initialize the dungeon list
		for (File file : readDungeons())
			dungeonChoice.getItems().add(file.getName());

		// track the config
		soundSlider.valueProperty().addListener((ov, old_val, new_val) -> {
			soundInfo.setText(new_val.intValue() + " / 100");
			DungeonApplication.setGameVolume(new_val.intValue());
		});

		difficultyChoice.getItems().addAll("Easy", "Medium", "Hard");
		difficultyChoice.setValue("Medium");
		difficultyChoice.getSelectionModel().selectedItemProperty().addListener((ov, old_val, new_val) -> {
			switch ((String) new_val) {
			case "Easy":
				DungeonApplication.setGameSpeed(75);
				break;
			case "Medium":
				DungeonApplication.setGameSpeed(50);
				break;
			case "Hard":
				DungeonApplication.setGameSpeed(25);
				break;
			}
		});
	}

	private File[] readDungeons() {
		File folder = new File("dungeons/");
		return folder.listFiles();
	}

	@FXML
	public void handleFreeStartButton(ActionEvent event) {
		startFreeDungeonScreen();
	}

	@FXML
	public void handleStoryStartButton(ActionEvent event) {
		startStoryDungeonScreen();
	}

	@FXML
	void handleCreateButton(ActionEvent event) {
		makerScreen.start();
	}

	@FXML
	void handleRefreshButton(ActionEvent event) {
		this.refreshDungeonList();
	}

	public void startFreeDungeonScreen() {
		if (dungeonChoice.getValue() == null)
			return;
		DungeonScreen freeScreen = DungeonScreenLoader.loadFreeScreen(dungeonChoice.getValue().toString());
		if (freeScreen != null)
			freeScreen.start();
	}

	public void startStoryDungeonScreen() {
		DungeonScreen storyScreen = DungeonScreenLoader.loadStoryScreen(DungeonApplication.getLevelsJson());
		if (storyScreen != null)
			storyScreen.start();
	}

	public void setMakerScreen(DungeonMakerScreen screen) {
		this.makerScreen = screen;
	}

	public void refreshDungeonList() {
		dungeonChoice.getItems().clear();
		for (File file : readDungeons())
			dungeonChoice.getItems().add(file.getName());
	}

}
