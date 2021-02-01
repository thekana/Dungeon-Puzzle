package dungeon.model.goal;

import dungeon.model.Dungeon;

public class TreasureGoal extends Goal {

    public TreasureGoal(Dungeon dungeon) {
        super(dungeon);
        setIsLeaf(true);
    }

    public void update() {
        setSatisfied(getDungeon().getTreasures().size() == 0);
    }

    @Override
    public String toString() {
        return "Collect all treasures";
    }

}
