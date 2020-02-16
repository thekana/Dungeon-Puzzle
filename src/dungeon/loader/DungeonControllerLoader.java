package unsw.dungeon.loader;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import unsw.dungeon.controller.DungeonController;
import unsw.dungeon.model.entities.Entity;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

	private List<ImageView> entities;

	public DungeonControllerLoader(String filename) throws FileNotFoundException {
		super(filename);
		entities = new ArrayList<>();
	}

	@Override
	public void onLoad(Entity entity) {
		ImageView view = new ImageView(new Image(entity.getImagePath()));
		addEntity(entity, view);
	}

	private void addEntity(Entity entity, ImageView view) {
		trackPosition(entity, view);
		trackImage(entity, view);
		entities.add(view);
		// TODO set node to control
		entity.setNode(view);
	}

	/**
	 * Set a node in a GridPane to have its position track the position of an entity
	 * in the dungeon.
	 *
	 * By connecting the model with the view in this way, the model requires no
	 * knowledge of the view and changes to the position of entities in the model
	 * will automatically be reflected in the view.
	 *
	 * @param entity
	 * @param node
	 */
	private void trackPosition(Entity entity, Node node) {
		GridPane.setColumnIndex(node, entity.getX());
		GridPane.setRowIndex(node, entity.getY());
		entity.x().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				GridPane.setColumnIndex(node, newValue.intValue());
			}
		});
		entity.y().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				GridPane.setRowIndex(node, newValue.intValue());
			}
		});
	}

	private void trackImage(Entity entity, Node node) {
		entity.imagePath().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				((ImageView) node).setImage(new Image(newValue.toString()));
			}
		});
	}

	/**
	 * set up a controller that can be attached to the DungeonView with all the
	 * loaded entities.
	 */
	public void loadController(DungeonController controller) {
		controller.setDungeon(load());
		controller.setInitialEntities(entities);
		controller.setLoader(this);
	}

}
