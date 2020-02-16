package unsw.test;

import org.junit.jupiter.api.AfterEach;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.Player;
import unsw.dungeon.model.goal.AndGoals;
import unsw.dungeon.model.goal.Goal;

public class testSetup {

	Dungeon dungeon = null;
	Player player = null;
	Goal baseGoal;
	/*
	 * To configure dungeon for each test
	 */
	void setup(int width, int height, int playerX, int playerY) {
		// create a new dungeon
		dungeon = new Dungeon(width, height);
		// set a player
		player = new Player(playerX, playerY, dungeon);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		// set a basic goal
		baseGoal = new AndGoals(dungeon);
		dungeon.setGoal(baseGoal);
	}

	@AfterEach
	void teatDown() {
		dungeon = null;
		player = null;
		baseGoal = null;	
		
	}
}
