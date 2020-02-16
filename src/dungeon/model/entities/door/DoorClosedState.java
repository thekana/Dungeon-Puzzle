package unsw.dungeon.model.entities.door;

public class DoorClosedState implements DoorState {

	private Door door;

	DoorClosedState(Door door) {
		this.door = door;
		door.setImagePath(this.getImagePath());
	}

	@Override
	public String getImagePath() {
		return "/closed_door.png";
	}

	@Override
	public boolean canPassThrough() {
		return false;
	}

	@Override
	public void nextState() {
		door.setState(new DoorOpenedState(door));
	}

}
