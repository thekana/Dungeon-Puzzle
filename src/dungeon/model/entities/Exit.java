package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;

public class Exit extends Entity {

	public Exit(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(true);
		this.setImagePath("/exit.png");
	}

	public void collideWith(Entity entity) {
	}

	@Override
	public EntityType type() {
		return EntityType.EXIT;
	}

}
