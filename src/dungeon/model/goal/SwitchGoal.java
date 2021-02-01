package dungeon.model.goal;

import dungeon.model.Dungeon;
import dungeon.model.entities.Switch;

public class SwitchGoal extends Goal {

	public SwitchGoal(Dungeon dungeon) {
		super(dungeon);
		setIsLeaf(true);
	}

	public void update() {
		setSatisfied(getDungeon().getSwitches().stream().allMatch(Switch::isActivated));
	}

	@Override
	public String toString() {
		return ("Boulders on all switches");
	}
}
