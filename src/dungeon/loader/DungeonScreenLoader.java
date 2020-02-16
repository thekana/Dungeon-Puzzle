package unsw.dungeon.loader;

import javafx.stage.Stage;
import unsw.dungeon.controller.FreeDungeonController;
import unsw.dungeon.controller.StoryDungeonController;
import unsw.dungeon.view.DungeonScreen;
import unsw.dungeon.view.MenuScreen;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DungeonScreenLoader {

	private static Stage primaryStage;
	private static MenuScreen menuScreen;

	public static void setPrimaryStage(Stage stage) {
		primaryStage = stage;
	}

	public static void setMenuScreen(MenuScreen screen) {
		menuScreen = screen;
	}

	public static DungeonScreen loadFreeScreen(String dungeonChoice) {
		DungeonScreen freeDungeonScreen = new DungeonScreen(primaryStage);
		freeDungeonScreen.setDungeonController(new FreeDungeonController(freeDungeonScreen));
		freeDungeonScreen.getController().setMenuScreen(menuScreen);
		try {
			freeDungeonScreen.load(dungeonChoice);
		} catch (Exception e) {
			System.out.println("The map json does not exist!");
		}

		return freeDungeonScreen;
	}

	public static DungeonScreen loadStoryScreen(String[] levels_json) {
		List<DungeonScreen> levels = new LinkedList<DungeonScreen>();
		for (String level_json : levels_json) {
			DungeonScreen curr_screen = new DungeonScreen(primaryStage);
			curr_screen.setDungeonController(new StoryDungeonController(curr_screen));
			curr_screen.getController().setMenuScreen(menuScreen);
			try {
				curr_screen.load(level_json);
			} catch (IOException e) {
				System.out.println("The level json does not exist!");
			}
			levels.add(curr_screen);

			if (levels.size() == 1)
				continue;
			DungeonScreen prev_screen = levels.get(levels.size() - 2);
			prev_screen.getController().setNextDungeonScreen(curr_screen);
			curr_screen.getController().setPrevDungeonScreen(prev_screen);
		}

		if (levels.size() > 0)
			return levels.get(0);
		else
			return null;
	}
}
