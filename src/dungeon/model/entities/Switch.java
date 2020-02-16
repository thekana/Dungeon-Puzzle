package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;

public class Switch extends Entity {

	public Switch(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(true);
		this.setImagePath("/pressure_plate.png");
	}

	public void collideWith(Entity entity) {

	}

	@Override
	public EntityType type() {
		return EntityType.SWITCH;
	}

	// for achieving goal

	public boolean isActivated() {
		return getDungeon().getEntities(getX(), getY()).stream().anyMatch(entity -> entity instanceof Boulder);
	}
}
