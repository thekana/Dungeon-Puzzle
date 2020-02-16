package unsw.dungeon.model.status;

import javafx.beans.property.IntegerProperty;

public class Status {

	private InvincibleState invincible;
	private InvisibleState invisible;

	public Status() {
		this.invincible = new InvincibleState();
		this.invisible = new InvisibleState();
	}

	public void updatePerMovement() {
		// decrease every step
		this.invincible.nextState();
		this.invisible.nextState();
	}

	public boolean isInvincible() {
		return getInvincibleStep() != 0;
	}

	public boolean isInvisible() {
		return getInvisibleStep() != 0;
	}

	public void becomeInvisible() {
		invisible.restore();
	}

	public void becomeInvincible() {
		invincible.restore();
	}

	public int getInvincibleStep() {
		return this.invincible.getRemainingTime();
	}

	public int getInvisibleStep() {
		return this.invisible.getRemainingTime();
	}

	// --- controller part ---

	public IntegerProperty getInvincibleRemainingProperty() {
		return invincible.getRemainingTimeProperty();
	}

	public IntegerProperty getInvisibleRemainingProperty() {
		return invisible.getRemainingTimeProperty();
	}
}
