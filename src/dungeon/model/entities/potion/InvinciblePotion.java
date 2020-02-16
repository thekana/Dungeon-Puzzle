package unsw.dungeon.model.entities.potion;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.EntityType;

public class InvinciblePotion extends Potion {

	public InvinciblePotion(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setImagePath("/brilliant_blue_new.png");
	}

	@Override
	public EntityType type() {
		return EntityType.INVINCIBLEPOTION;
	}

}
