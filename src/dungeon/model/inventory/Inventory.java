package unsw.dungeon.model.inventory;

import javafx.beans.property.IntegerProperty;

public class Inventory {

	private BombInv bomb;
	private TreasureInv treasure;
	private SwordInv sword;
	private KeyInv key;

	public Inventory() {
		this.bomb = new BombInv();
		this.treasure = new TreasureInv();
		this.sword = new SwordInv();
		this.key = new KeyInv();
	}

	// --- treasure part ---

	public void pickTreasure() {
		this.treasure.pickTreasure();
	}

	public int getTreasureNum() {
		return this.treasure.getNumTreasures();
	}

	// --- sword part ---

	public void pickSword() {
		this.sword.restoreDurability();
	}

	public boolean useSword() {
		if (this.sword.broken())
			return false;

		this.sword.use();
		return true;
	}

	public SwordInv getSword() {
		return this.sword;
	}

	public void breakSword() {
		this.sword.breakSword();
	}

	public int getSwordDurability() {
		return this.sword.getDurability();
	}

	// -- bomb part ---

	public void pickBomb() {
		this.bomb.increaseBomb();
	}

	public boolean useBomb() {
		if (getBombNum() <= 0)
			return false;
		this.bomb.decreaseBomb();
		return true;
	}

	public int getBombNum() {
		return this.bomb.getNumBombs();
	}

	// --- key part ---

	public boolean pickKey(int id) {
		return this.key.pickKey(id);
	}

	public void useKey() {
		this.key.consumeKey();
	}

	public KeyInv getKey() {
		return this.key;
	}

	public int getKeyID() {
		return this.key.getKeyID();
	}

	// --- controller part ---

	public IntegerProperty getBombNumProperty() {
		return bomb.getNumBombsProperty();
	}

	public IntegerProperty getSwordDurabilityProperty() {
		return sword.getDurabilityProperty();
	}

	public IntegerProperty getNumTreasuresProperty() {
		return treasure.getNumTreasuresProperty();
	}

	public IntegerProperty getKeyIDProperty() {
		return key.getKeyIDProperty();
	}
}
