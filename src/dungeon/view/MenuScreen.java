package unsw.dungeon.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.dungeon.controller.MenuController;

import java.io.IOException;

public class MenuScreen {

	private Stage stage;
	private String title = "Dungeon Menu";
	private MenuController controller;
	private Scene scene;

	public MenuScreen(Stage stage) throws IOException {
		this.stage = stage;

		controller = new MenuController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuView.fxml"));
		loader.setController(controller);

		// load into a Parent node called root
		Parent root = loader.load();
		scene = new Scene(root, 800, 400);
	}

	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		controller.refreshDungeonList();
		stage.show();
	}

	public MenuController getController() {
		return controller;
	}

}
