package dungeon.model.goal;

import dungeon.model.Dungeon;
import dungeon.model.entities.npc.Wizard;

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
