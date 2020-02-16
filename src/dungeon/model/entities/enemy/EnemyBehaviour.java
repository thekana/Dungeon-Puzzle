package unsw.dungeon.model.entities.enemy;

import unsw.dungeon.model.entities.Player;

public interface EnemyBehaviour {
	public void move(Enemy enemy, Player player);
}
