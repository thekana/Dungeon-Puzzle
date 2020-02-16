package unsw.dungeon.model.goal;

import unsw.dungeon.model.Dungeon;

import java.util.ArrayList;
import java.util.List;

public class OrGoals extends Goal {

	private List<Goal> subgoals = new ArrayList<Goal>();

	public OrGoals(Dungeon dungeon) {
		super(dungeon);
		setIsLeaf(false);
	}

	public void addSubgoal(Goal goal) {
		subgoals.add(goal);
	}

	public void removeSubgoal(Goal goal) {
		subgoals.remove(goal);
	}

	public List<Goal> getSubgoals() {
		return subgoals;
	}

	public void update() {
		subgoals.forEach(Goal::update);
		setSatisfied(subgoals.stream().anyMatch(Goal::isSatisfied));
	}

	@Override
	public String toString() {
		return "OrGoals: ";
	}

}
