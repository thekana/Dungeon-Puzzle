package unsw.dungeon.model.entities.bomb;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.EntityType;
import unsw.dungeon.model.entities.Player;

public class UnlitBomb extends Entity {

	public UnlitBomb(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		setPassThrough(true);
		setImagePath("/bomb_unlit.png");
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player)
			this.getDungeon().pickUp(this);
	}

	@Override
	public EntityType type() {
		return EntityType.UNLITBOMB;
	}

}
