package unsw.dungeon.model.goal;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.dungeon.model.Dungeon;

import java.util.List;

public abstract class Goal {

	private Dungeon dungeon;
	private BooleanProperty satisfied;
	private boolean isLeaf;

	public Goal(Dungeon dungeon) {
		this.dungeon = dungeon;
		this.satisfied = new SimpleBooleanProperty(false);
	}

	public boolean isSatisfied() {
		update();
		return satisfied.get();
	}

	void setSatisfied(boolean bool) {
		satisfied.set(bool);
	}

	void setIsLeaf(boolean bool) {
		this.isLeaf = bool;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public BooleanProperty getSatisfiedProperty() {
		return satisfied;
	}

	public Dungeon getDungeon() {
		return dungeon;
	}

	public void addSubgoal(Goal goal) {
		System.out.println("Unsupported Operation for goal leaf!");
	}

	public void removeSubgoal(Goal goal) {
		System.out.println("Unsupported Operation for goal leaf!");
	}

	public List<Goal> getSubgoals() {
		System.out.println("Unsupported Operation for goal leaf!");
		return null;
	}

	public abstract void update();

	public abstract String toString();
}
