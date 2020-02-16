package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;

public class Wall extends Entity {

	public Wall(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(false);
		this.setImagePath("/brick_brown_0.png");
	}

	public void collideWith(Entity entity) {

	}

	@Override
	public EntityType type() {
		return EntityType.WALL;
	}
}
