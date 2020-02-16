package unsw.dungeon.model.entities.enemy;

import unsw.dungeon.model.Direction;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Player;

import java.util.LinkedList;

public class EnemyMoveClose implements EnemyBehaviour {

	// a helper class for path finding
	class Grid {
		final int x;
		final int y;
		Grid(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// use BFS to find next grid
	private Direction findNextDirection(Enemy enemy, Player player, Dungeon dungeon) {

		Grid[][] visited = new Grid[dungeon.getWidth()][dungeon.getHeight()];
		boolean found = false;
		LinkedList<Grid> queue = new LinkedList<Grid>();

		Grid enemy_grid = new Grid(enemy.getX(), enemy.getY());
		visited[enemy.getX()][enemy.getY()] = enemy_grid;
		queue.add(enemy_grid);
		Grid curr = enemy_grid;

		// try to find the player
		while (queue.size() != 0 && !found)
		{
			curr = queue.poll();
			LinkedList<Grid> nearbyGrids = new LinkedList<Grid>();

			if (curr.x > 0)
				nearbyGrids.add(new Grid(curr.x - 1, curr.y));
			if (curr.x < dungeon.getWidth() - 1)
				nearbyGrids.add(new Grid(curr.x + 1, curr.y));
			if (curr.y > 0)
				nearbyGrids.add(new Grid(curr.x, curr.y - 1));
			if (curr.y < dungeon.getHeight() - 1)
				nearbyGrids.add(new Grid(curr.x, curr.y + 1));

			for (Grid grid : nearbyGrids) {
				if (grid.x == player.getX() && grid.y == player.getY()) {
					found = true;
					// this is to let humanEnemy attack player
					visited[grid.x][grid.y] = curr;
					curr = grid;
					break;
				} else if (visited[grid.x][grid.y] == null
						&& dungeon.canOccupyGrid(grid.x, grid.y)) {
					visited[grid.x][grid.y] = curr;
					queue.add(grid);
				}
			}
		}

		// retrieve the path
		while (visited[curr.x][curr.y] != enemy_grid)
			curr = visited[curr.x][curr.y];

		// find the direction
		if (curr.x > enemy.getX()) {
			return Direction.RIGHT;
		} else if (curr.x < enemy.getX()) {
			return Direction.LEFT;
		} else if (curr.y > enemy.getY()) {
			return Direction.DOWN;
		} else {
			return Direction.UP;
		}
	}

	@Override
	public void move(Enemy enemy, Player player) {
		enemy.move(findNextDirection(enemy, player, enemy.getDungeon()));
	}
}
