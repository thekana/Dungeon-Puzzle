package unsw.dungeon.model.inventory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

class InvincibleState {

	private IntegerProperty remaining_time;

	InvincibleState() {
		this.remaining_time = new SimpleIntegerProperty(0);
	}

	void restore() {
		remaining_time.set(5);
	}

	void nextState() {
		if (remaining_time.get() != 0)
			remaining_time.set(remaining_time.get() - 1);
	}

	int getRemainingTime() {
		return remaining_time.get();
	}

	IntegerProperty getRemainingTimeProperty() {
		return remaining_time;
	}
}
