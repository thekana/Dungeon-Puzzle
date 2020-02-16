package unsw.dungeon.model.inventory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

class TreasureInv {
	private IntegerProperty num_treasures;

	TreasureInv() {
		this.num_treasures = new SimpleIntegerProperty(0);
	}

	public void pickTreasure() {
		this.num_treasures.set(num_treasures.get() + 1);
	}

	public int getNumTreasures() {
		return this.num_treasures.get();
	}

	public IntegerProperty getNumTreasuresProperty() {
		return this.num_treasures;
	}
}
