package unsw.dungeon.model.inventory;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

class KeyInv {

	private final int NULL_ID = -1;

	private IntegerProperty key_id;

	KeyInv() {
		this.key_id = new SimpleIntegerProperty(NULL_ID);
	}

	boolean pickKey(int id) {
		if (getKeyID() == NULL_ID) {
			this.key_id.set(id);
			return true;
		} else {
			return false;
		}
	}

	void consumeKey() {
		this.key_id.set(NULL_ID);
	}

	int getKeyID() {
		return this.key_id.get();
	}

	IntegerProperty getKeyIDProperty() {
		return this.key_id;
	}

}
