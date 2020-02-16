package unsw.dungeon.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.dungeon.controller.DungeonController;
import unsw.dungeon.loader.DungeonControllerLoader;
import unsw.dungeon.model.Dungeon;

import java.io.IOException;

public class DungeonScreen {

	private Stage stage;
	private String title = "Dungeon Game";
	private DungeonController controller;
	private Scene scene;
	private String map;

	public DungeonScreen(Stage stage) {
		this.stage = stage;
	}

	public void setDungeonController(DungeonController controller) {
		this.controller = controller;
	}

	public void load(String mapJson) throws IOException {
		this.map = mapJson;
		// use ControllerLoader to load map from a json file
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(mapJson);

		// create a Controller from the ControllerLoader
		dungeonLoader.loadController(this.controller);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		loader.setController(this.controller);

		// get the root and set scene
		Parent root = loader.load();
		scene = new Scene(root);
	}

	public void start() {
		scene.getRoot().requestFocus();
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
		this.controller.startDungeon();
	}

	public void restart() {
		try {
			this.load(map);
			this.start();
		} catch (Exception e) {
			System.out.println("restart fault!");
		}
	}

	public void start(Dungeon prev_dungeon) {
		scene.getRoot().requestFocus();
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
		this.controller.getDungeon().inheritFrom(prev_dungeon);
		this.controller.startDungeon();
	}

	public DungeonController getController() {
		return controller;
	}

}
