package unsw.dungeon.model.entities.potion;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.EntityType;

public class InvisiblePotion extends Potion {

	public InvisiblePotion(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setImagePath("/bubbly.png");
	}

	@Override
	public EntityType type() {
		return EntityType.INVISIBLEPOTION;
	}

}
