package dungeon.model.entities.npc;

import dungeon.model.Dungeon;
import dungeon.model.entities.Entity;
import dungeon.model.entities.EntityType;
import dungeon.model.entities.Player;

public class Princess extends Entity {

	private boolean isActivated;

	public Princess(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(false);
		this.setImagePath("/princess.png");
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player)
			this.isActivated = true;
	}

	@Override
	public EntityType type() {
		return EntityType.PRINCESS;
	}

	// for achieving goal

	public boolean isActivated() {
		return this.isActivated;
	}
}
