package unsw.dungeon.model.inventory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

class BombInv {

	private IntegerProperty num_bombs;

	BombInv() {
		this.num_bombs = new SimpleIntegerProperty(0);
	}

	void increaseBomb() {
		this.num_bombs.set(num_bombs.getValue() + 1);
	}

	void decreaseBomb() {
		this.num_bombs.set(num_bombs.getValue() - 1);
	}

	IntegerProperty getNumBombsProperty() {
		return num_bombs;
	}

	int getNumBombs() {
		return num_bombs.get();
	}
}
