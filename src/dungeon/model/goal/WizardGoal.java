package unsw.dungeon.model.goal;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.npc.Wizard;

public class WizardGoal extends Goal {

	public WizardGoal(Dungeon dungeon) {
		super(dungeon);
		setIsLeaf(true);
	}

	public void update() {
		setSatisfied(getDungeon().getWizards().stream().allMatch(Wizard::isActivated));
	}

	@Override
	public String toString() {
		return "Talk to the Wizard";
	}
}
