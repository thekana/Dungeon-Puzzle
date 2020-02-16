package unsw.dungeon.model.goal;

import unsw.dungeon.model.Dungeon;

public class EnemyGoal extends Goal {

	public EnemyGoal(Dungeon dungeon) {
		super(dungeon);
		setIsLeaf(true);
	}

	public void update() {
		setSatisfied(getDungeon().getEnemies().size() == 0);
	}

	@Override
	public String toString() {
		return "Destroy all enemies";
	}
}
