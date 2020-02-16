package unsw.dungeon.model.goal;

import unsw.dungeon.model.Dungeon;

import java.util.ArrayList;
import java.util.List;

public class AndGoals extends Goal {

	private List<Goal> subgoals = new ArrayList<Goal>();

	public AndGoals(Dungeon dungeon) {
		super(dungeon);
		setIsLeaf(false);
	}

	@Override
	public void addSubgoal(Goal goal) {
		subgoals.add(goal);
	}

	@Override
	public void removeSubgoal(Goal goal) {
		subgoals.remove(goal);
	}

	@Override
	public List<Goal> getSubgoals() {
		return subgoals;
	}

	public void update() {
		subgoals.forEach(Goal::update);
		setSatisfied(subgoals.stream().allMatch(Goal::isSatisfied));
	}

	@Override
	public String toString() {
		return "AndGoals: ";
	}

}
