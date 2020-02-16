package unsw.dungeon;

import javafx.application.Application;
import javafx.stage.Stage;
import unsw.dungeon.view.DungeonMakerScreen;
import unsw.dungeon.loader.DungeonScreenLoader;
import unsw.dungeon.view.MenuScreen;

import java.io.IOException;

public class DungeonApplication extends Application {


	// Global config
	private static int game_speed = 50;
	private static int game_volume = 50;
	private static String[] levels_json = new String[] {
			"intro.json",
			"level1.json",
			"level6.json",
			"level7.json",
			"level8.json",
	};

	public static int getGameSpeed() {
		return game_speed;
	}

	public static int getGameVolume() {
		return game_volume;
	}

	public static void setGameVolume(int volume) {
		game_volume = volume;
	}

	public static void setGameSpeed(int speed) {
		game_speed = speed;
	}

	public static String[] getLevelsJson() {
		return levels_json;
	}


	@Override
	public void start(Stage primaryStage) throws IOException {

		// create the menu screen and dungeon screen
		MenuScreen menuScreen = new MenuScreen(primaryStage);

		// dungeon maker screen
		DungeonMakerScreen makerScreen = new DungeonMakerScreen(primaryStage);
		makerScreen.getController().setMenuScreen(menuScreen);
		menuScreen.getController().setMakerScreen(makerScreen);

		// create the storyDungeon screen
		DungeonScreenLoader.setPrimaryStage(primaryStage);
		DungeonScreenLoader.setMenuScreen(menuScreen);

		menuScreen.start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
