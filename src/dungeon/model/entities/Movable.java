package dungeon.model.entities;

import javafx.beans.property.IntegerProperty;
import dungeon.model.Direction;

public interface Movable {

    public IntegerProperty x();
    public IntegerProperty y();

    public int getX();
    public int getY();

    public void move(Direction direction);
}
