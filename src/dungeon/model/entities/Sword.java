package unsw.dungeon.model.entities;

import unsw.dungeon.model.Dungeon;

public class Sword extends Entity {

	public Sword(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(true);
		this.setImagePath("/greatsword_1_new.png");
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player)
			this.getDungeon().pickUp(this);
	}

	@Override
	public EntityType type() {
		return EntityType.SWORD;
	}

}
