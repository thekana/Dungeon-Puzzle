package unsw.dungeon.model.entities;

import unsw.dungeon.model.Direction;
import unsw.dungeon.model.Dungeon;

public class Boulder extends Entity implements Movable {

	private Movement movement;

	public Boulder(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		movement = new Movement(this, dungeon);
		setPassThrough(false);
		setImagePath("/boulder.png");
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player) {
			if (entity.getY() > this.getY()) {
				this.move(Direction.UP);
			} else if (entity.getY() < this.getY()) {
				this.move(Direction.DOWN);
			} else if (entity.getX() < this.getX()) {
				this.move(Direction.RIGHT);
			} else if (entity.getX() > this.getX()) {
				this.move(Direction.LEFT);
			}
		}
	}

	@Override
	public EntityType type() {
		return EntityType.BOULDER;
	}

	// for movable interface
	public void move(Direction direction) {
		movement.move(direction);
	}

}
