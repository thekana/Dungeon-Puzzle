package unsw.dungeon.model.entities.door;

public interface DoorState {
	public String getImagePath();

	public boolean canPassThrough();

	public void nextState();
}
