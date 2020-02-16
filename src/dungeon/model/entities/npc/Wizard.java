package unsw.dungeon.model.entities.npc;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.EntityType;
import unsw.dungeon.model.entities.Player;

public class Wizard extends Entity {

	private boolean isActivated;

	public Wizard(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setPassThrough(false);
		this.setImagePath("/gnome.png");
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player)
			this.isActivated = true;
	}

	@Override
	public EntityType type() {
		return EntityType.WIZARD;
	}

	// for achieving goal

	public boolean isActivated() {
		return this.isActivated;
	}
}
