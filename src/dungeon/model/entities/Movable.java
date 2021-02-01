package dungeon.model.entities;

import dungeon.model.Direction;
import javafx.beans.property.IntegerProperty;

public interface Movable {

    public IntegerProperty x();

    public IntegerProperty y();

    public int getX();

    public int getY();

    public void move(Direction direction);
}
