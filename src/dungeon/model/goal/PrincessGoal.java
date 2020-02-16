package unsw.dungeon.model.goal;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.npc.Princess;

public class PrincessGoal extends Goal {

	public PrincessGoal(Dungeon dungeon) {
		super(dungeon);
		setIsLeaf(true);
	}

	public void update() {
		setSatisfied(getDungeon().getPrincess().stream().allMatch(Princess::isActivated));
	}

	@Override
	public String toString() {
		return "Save the princess";
	}
}
