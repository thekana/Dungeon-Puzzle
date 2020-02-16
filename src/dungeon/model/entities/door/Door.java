package unsw.dungeon.model.entities.door;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Entity;
import unsw.dungeon.model.entities.EntityType;
import unsw.dungeon.model.entities.Player;

public class Door extends Entity {

	private int id;
	private DoorState state;

	public Door(int x, int y, Dungeon dungeon, int id) {
		super(x, y, dungeon);
		this.id = id;
		this.state = new DoorClosedState(this);
	}

	@Override
	public Boolean canPassThrough() {
		return this.state.canPassThrough();
	}

	/**
	 * @param state the state to set
	 */
	public void setState(DoorState state) {
		this.state = state;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public void open() {
		state.nextState();
	}

	public void collideWith(Entity entity) {
		if (entity instanceof Player)
			this.getDungeon().tryOpenDoor(this);
	}

	@Override
	public EntityType type() {
		return EntityType.DOOR;
	}

}
