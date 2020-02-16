package unsw.dungeon.model.entities.door;

public class DoorOpenedState implements DoorState {

	private Door door;

	DoorOpenedState(Door door) {
		this.door = door;
		this.door.setImagePath(this.getImagePath());
	}
	
	@Override
	public String getImagePath() {
		return "/open_door.png";
	}

	@Override
	public boolean canPassThrough() {
		return true;
	}

	@Override
	public void nextState() {
		System.out.println("Door is already opened");
	}

}
