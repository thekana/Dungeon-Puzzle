package unsw.dungeon.model.entities.bomb;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.EntityType;

public class LitBomb extends Entity {

	private LitBombState state;

	public LitBomb(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.state = new LitBombState1(this);
		this.setPassThrough(false);
	}

	public void collideWith(Entity entity) {
	}

	@Override
	public EntityType type() {
		return EntityType.LITBOMB;
	}

	public void updatePerMovement() {
		this.nextState();
	}

	private void nextState() {
		state.nextState();
	}

	/**
	 * @param state the state to set
	 */
	public void setState(LitBombState state) {
		this.state = state;
	}

}
