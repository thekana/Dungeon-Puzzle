package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;

public class DownFloor extends Entity {

	public DownFloor(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(true);
		this.setImagePath("/down_floor.png");
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player)
			getDungeon().switchPrevDungeon();
	}

	@Override
	public EntityType type() {
		return EntityType.EXIT;
	}

}
