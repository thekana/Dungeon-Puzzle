package unsw.test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.model.Direction;
import unsw.dungeon.model.entities.*;
import unsw.dungeon.model.entities.enemy.HumanEnemy;
import unsw.dungeon.model.goal.*;

import static org.junit.jupiter.api.Assertions.*;

public class testGoals extends testSetup {

	/*
	 * Collect all treasures to complete the goal
	 */
	@Test
	void testTreasureGoal() {
		setup(5, 3, 1, 1);
		baseGoal.addSubgoal(new TreasureGoal(dungeon));

		dungeon.addEntity(new Treasure(2, 1, dungeon));
		dungeon.addEntity(new Treasure(3, 1, dungeon));

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals(false, dungeon.goalAchieved());

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals(true, dungeon.goalAchieved());
	}

	/*
	 * Kill all enemies to complete the goal
	 */
	@Test
	void testEnemyGoal() {
		setup(5, 1, 0, 0);
		baseGoal.addSubgoal(new EnemyGoal(dungeon));
		dungeon.addEntity(new HumanEnemy(1, 0, dungeon));

		Sword sword = new Sword(0, 0, dungeon);
		dungeon.addEntity(sword);
		dungeon.pickUp(sword);

		assertEquals(false, dungeon.goalAchieved());
		dungeon.movePlayer(Direction.RIGHT); // pick sword
		assertEquals(true, dungeon.goalAchieved());

	}

	/*
	 * Push all boulders on switches
	 */
	@Test
	void testBouldersGoal() {
		setup(3, 1, 0, 0);

		baseGoal.addSubgoal(new SwitchGoal(dungeon));

		dungeon.addEntity(new Switch(1, 0, dungeon));
		dungeon.addEntity(new Switch(2, 0, dungeon));

		assertFalse(dungeon.goalAchieved());

		// put boulders on switches
		dungeon.addEntity(new Boulder(1, 0, dungeon));
		dungeon.addEntity(new Boulder(2, 0, dungeon));

		assertTrue(dungeon.goalAchieved());
	}

	/*
	 * Be at the exit to complete the goal
	 */
	@Test
	void testExitGoal() {
		setup(5, 5, 2, 2);

		baseGoal.addSubgoal(new ExitGoal(dungeon));

		dungeon.addEntity(new Exit(2, 1, dungeon));

		assertFalse(dungeon.goalAchieved());

		dungeon.movePlayer(Direction.UP);	// let player move to the exit
		assertTrue(dungeon.goalAchieved());
	}

	/*
	 * Testing 2 AND goals
	 */
	@Test
	void testOneLevelCompositeGoal() {
		setup(3, 1, 0, 0);

		AndGoals andGoals = new AndGoals(dungeon);
		andGoals.addSubgoal(new ExitGoal(dungeon));
		andGoals.addSubgoal(new TreasureGoal(dungeon));
		baseGoal.addSubgoal(andGoals);

		dungeon.addEntity(new Treasure(1, 0, dungeon));
		dungeon.addEntity(new Exit(2, 0, dungeon));

		dungeon.movePlayer(Direction.RIGHT);
		assertFalse(dungeon.goalAchieved());

		dungeon.movePlayer(Direction.RIGHT);
		assertTrue(dungeon.goalAchieved());
	}

	/*
	 * Testing two level goals AND + (OR goals)
	 */
	@Test
	void testTwoLevelCompositeGoal1() {
		setup(5, 3, 2, 2);

		AndGoals andGoals = new AndGoals(dungeon);
		andGoals.addSubgoal(new ExitGoal(dungeon));
		OrGoals orGoals = new OrGoals(dungeon);
		andGoals.addSubgoal(orGoals);
		orGoals.addSubgoal(new SwitchGoal(dungeon));
		orGoals.addSubgoal(new TreasureGoal(dungeon));
		baseGoal.addSubgoal(andGoals);


		dungeon.addEntity(new Treasure(1, 2, dungeon));
		dungeon.addEntity(new Boulder(3, 2, dungeon));
		dungeon.addEntity(new Switch(4, 2, dungeon));
		dungeon.addEntity(new Exit(2, 1, dungeon));

		dungeon.movePlayer(Direction.RIGHT);
		assertFalse(dungeon.goalAchieved());

		dungeon.movePlayer(Direction.UP);
		assertTrue(dungeon.goalAchieved());
	}

	/*
	 * Testing two level goals OR + (AND goals)
	 */
	@Test
	void testTwoLevelCompositeGoal2() {
		setup(5, 3, 2, 2);

		AndGoals andGoals = new AndGoals(dungeon);
		andGoals.addSubgoal(new SwitchGoal(dungeon));
		andGoals.addSubgoal(new TreasureGoal(dungeon));
		OrGoals orGoals = new OrGoals(dungeon);
		orGoals.addSubgoal(new ExitGoal(dungeon));
		orGoals.addSubgoal(andGoals);
		baseGoal.addSubgoal(orGoals);

		dungeon.addEntity(new Treasure(1, 2, dungeon));
		dungeon.addEntity(new Boulder(3, 2, dungeon));
		dungeon.addEntity(new Switch(4, 2, dungeon));
		dungeon.addEntity(new Exit(2, 1, dungeon));

		dungeon.movePlayer(Direction.UP);
		assertEquals(true, dungeon.goalAchieved()); // at exit true

		dungeon.movePlayer(Direction.DOWN);
		assertEquals(false, dungeon.goalAchieved()); // move back to origin false

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals(false, dungeon.goalAchieved()); // only pushes boulder false

		dungeon.movePlayer(Direction.LEFT); // collected treasure
		assertEquals(true, dungeon.goalAchieved());

		dungeon.movePlayer(Direction.LEFT); // dummy movement will result in true because the player has satisfied boulder +
							// treasure
		assertEquals(true, dungeon.goalAchieved());
	}
}
