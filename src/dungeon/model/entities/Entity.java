package dungeon.model.entities;

import dungeon.model.Dungeon;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

/**
 * An entity in the dungeon.
 *
 * @author Robert Clifton-Everest
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private final StringProperty imagePath;
    private final IntegerProperty x;
    private final IntegerProperty y;
    private final Dungeon dungeon;
    private Node node;
    private Boolean passThrough;

    /**
     * Create an entity positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    public Entity(int x, int y, Dungeon dungeon) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.imagePath = new SimpleStringProperty();
        this.dungeon = dungeon;
    }

    // properties for javafx

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    public StringProperty imagePath() {
        return imagePath;
    }

    public String getImagePath() {
        return this.imagePath.get();
    }

    public void setImagePath(String path) {
        this.imagePath.set("file:images/" + path);
    }

    public Node getNode() {
        return this.node;
    }

    public void setNode(Node node) {
        this.node = node;
    }


    // other methods

    abstract public void collideWith(Entity entity);

    public void setPassThrough(Boolean bool) {
        this.passThrough = bool;
    }

    public Boolean canPassThrough() {
        return this.passThrough;
    }

    public Dungeon getDungeon() {
        return this.dungeon;
    }

    public abstract EntityType type();
}
