package unsw.dungeon.model.inventory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

class SwordInv {

	private IntegerProperty durability;

	SwordInv() {
		this.durability = new SimpleIntegerProperty(0);
	}

	int getDurability() {
		return durability.get();
	}

	void restoreDurability() {
		this.durability.set(5);
	}

	void use() {
		this.durability.set(durability.get() - 1);
	}

	void breakSword() {
		this.durability.set(0);
	}

	boolean broken() {
		return this.durability.get() <= 0;
	}

	IntegerProperty getDurabilityProperty() {
		return durability;
	}

}
