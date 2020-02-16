package unsw.dungeon.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.dungeon.controller.DungeonMakerController;

public class DungeonMakerScreen {

	private Stage stage;
	private String title = "Dungeon Maker";
	private DungeonMakerController controller;
	private Scene scene;

	public DungeonMakerScreen(Stage stage) throws IOException {
		this.stage = stage;

		controller = new DungeonMakerController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonMakerView.fxml"));
		loader.setController(controller);

		// load into a Parent node called root
		Parent root = loader.load();
		// scene = new Scene(root, 1000, 500);
		scene = new Scene(root);
	}

	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}

	public DungeonMakerController getController() {
		return controller;
	}
}
