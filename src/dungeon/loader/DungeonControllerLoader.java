package dungeon.loader;

import dungeon.controller.DungeonController;
import dungeon.model.entities.Entity;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 *
 * @author Robert Clifton-Everest
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    public DungeonControllerLoader(String filename) throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
    }

    @Override
    public void onLoad(Entity entity) {
        try {
            Image image = new Image(entity.getImagePath());
            ImageView view = new ImageView(image);
            addEntity(entity, view);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
     * <p>
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
        entity.x().addListener((observable, oldValue, newValue) -> GridPane.setColumnIndex(node, newValue.intValue()));
        entity.y().addListener((observable, oldValue, newValue) -> GridPane.setRowIndex(node, newValue.intValue()));
    }

    private void trackImage(Entity entity, Node node) {
        entity.imagePath().addListener((observable, oldValue, newValue) -> ((ImageView) node).setImage(new Image(newValue)));
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
