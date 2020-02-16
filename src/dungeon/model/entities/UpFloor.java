package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;

public class UpFloor extends Entity {

	public UpFloor(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(true);
		this.setImagePath("/up_floor.png");
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player)
			getDungeon().switchNextDungeon();
	}

	@Override
	public EntityType type() {
		return EntityType.EXIT;
	}

}
