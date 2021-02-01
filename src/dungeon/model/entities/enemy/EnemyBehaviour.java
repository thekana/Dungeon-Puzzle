package dungeon.model.entities.enemy;

import dungeon.model.entities.Player;

public interface EnemyBehaviour {
	public void move(Enemy enemy, Player player);
}
