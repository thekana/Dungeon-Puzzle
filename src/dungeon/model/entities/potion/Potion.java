package dungeon.model.entities.potion;

import dungeon.model.Dungeon;
import dungeon.model.entities.Entity;
import dungeon.model.entities.EntityType;
import dungeon.model.entities.Player;

public abstract class Potion extends Entity {

	public Potion(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(true);
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player)
			this.getDungeon().pickUp(this);
	}

	@Override
	public abstract EntityType type();

}
