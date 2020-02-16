package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;

public class Key extends Entity {

	private int id;

	public Key(int x, int y, Dungeon dungeon, int id) {
		super(x, y, dungeon);
		this.setPassThrough(true);
		this.id = id;
		this.setImagePath("/key.png");
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player)
			this.getDungeon().pickUp(this);
	}

	@Override
	public EntityType type() {
		return EntityType.KEY;
	}

	public int getId() {
		return id;
	}
}
