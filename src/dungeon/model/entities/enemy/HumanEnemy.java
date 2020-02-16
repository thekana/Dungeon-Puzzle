package unsw.dungeon.model.entities.enemy;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.*;

public class HumanEnemy extends Enemy implements Movable {

	private EnemyBehaviour moveClose = new EnemyMoveClose();
	private EnemyBehaviour moveAway = new EnemyMoveAway();

	public HumanEnemy(int x, int y, Dungeon dungeon) {
		super(x, y, dungeon);
		this.setImagePath("/deep_elf_master_archer.png");
		this.setBehaviour(moveClose);
	}

	public void updatePerMovement() {
		// set strategy
		if (getDungeon().getStatus().isInvisible())
			return;
		else if (getDungeon().isPlayerInvincible())
			this.setBehaviour(moveAway);
		else
			this.setBehaviour(moveClose);

		Player player = getDungeon().getPlayer();
		if (player != null) // avoid null pointer when the player is killed by other enemy
			this.move(player);
	}

}
