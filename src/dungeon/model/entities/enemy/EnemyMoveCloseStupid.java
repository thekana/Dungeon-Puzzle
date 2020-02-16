package unsw.dungeon.model.entities.enemy;

import unsw.dungeon.model.Direction;
import unsw.dungeon.model.entities.Player;

import java.util.Random;

public class EnemyMoveCloseStupid implements EnemyBehaviour {

	private Random rand = new Random();

	@Override
	public void move(Enemy enemy, Player player) {

		int randInt = rand.nextInt(2);
		if (randInt == 0) {
			if (player.getX() > enemy.getX()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX() + 1, enemy.getY())) {
				enemy.move(Direction.RIGHT);
			} else if (player.getX() < enemy.getX()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX() - 1, enemy.getY())) {
				enemy.move(Direction.LEFT);
			} else if (player.getY() > enemy.getY()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX(), enemy.getY() + 1)) {
				enemy.move(Direction.DOWN);
			} else if (player.getY() < enemy.getY()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX(), enemy.getY() - 1)) {
				enemy.move(Direction.UP);
			}
		} else {
			if (player.getY() > enemy.getY()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX(), enemy.getY() + 1)) {
				enemy.move(Direction.DOWN);
			} else if (player.getY() < enemy.getY()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX(), enemy.getY() - 1)) {
				enemy.move(Direction.UP);
			} else if (player.getX() > enemy.getX()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX() + 1, enemy.getY())) {
				enemy.move(Direction.RIGHT);
			} else if (player.getX() < enemy.getX()
					&& enemy.getDungeon().canOccupyGrid(enemy.getX() - 1, enemy.getY())) {
				enemy.move(Direction.LEFT);
			}
		}
	}

}
