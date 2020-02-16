package unsw.test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.model.entities.potion.InvinciblePotion;
import unsw.dungeon.model.entities.enemy.Enemy;
import unsw.dungeon.model.entities.enemy.HoundEnemy;
import unsw.dungeon.model.entities.enemy.HumanEnemy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testEnemyBehaviour extends testSetup {

	/*
	 * Normally the enemy will close the distance between the player and itself
	 */
	@Test
	void testHumanEnemyMoveTowards() {
		setup(10, 1, 0, 0);

		Enemy enemy = new HumanEnemy(9, 0, dungeon);
		dungeon.addEntity(enemy);

		int distance = enemy.getX() - player.getX(); // distance should become smaller

		for (int move = 1; move < distance; move++) {
			enemy.move(player);
			assertEquals(distance - move, (enemy.getX() - player.getX()));
		}
	}

	/*
	 * Normally the enemy will close the distance between the player and itself
	 */
	@Test
	void testHoundEnemyMoveTowards() {
		setup(10, 1, 0, 0);

		Enemy enemy = new HoundEnemy(9, 0, dungeon);
		dungeon.addEntity(enemy);

		int distance = enemy.getX() - player.getX(); // distance should become smaller

		for (int move = 1; move < distance; move++) {
			enemy.move(player);
			assertEquals(distance - move, (enemy.getX() - player.getX()));
		}
	}

	/*
	 * Enemy will move away when the player is invincible
	 */
	@Test
	void testHumanEnemyMoveAway() {
		setup(10, 1, 0, 0);

		Enemy enemy = new HumanEnemy(3, 0, dungeon);
		dungeon.addEntity(enemy);

		dungeon.pickUp(new InvinciblePotion(1, 0, dungeon));

		int distance = enemy.getX() - player.getX(); // distance will stay the same

		dungeon.tick();
		assertEquals(distance + 1, (enemy.getX() - player.getX()));

		dungeon.tick();
		assertEquals(distance + 2, (enemy.getX() - player.getX()));

		dungeon.tick();
		assertEquals(distance + 3, (enemy.getX() - player.getX()));
	}

	/*
	 * Enemy will move away when the player is invincible
	 */
	@Test
	void testHoundEnemyMoveAway() {
		setup(10, 1, 0, 0);

		Enemy enemy = new HoundEnemy(3, 0, dungeon);
		dungeon.addEntity(enemy);

		dungeon.pickUp(new InvinciblePotion(1, 0, dungeon));

		int distance = enemy.getX() - player.getX(); // distance will stay the same

		dungeon.tick();
		assertEquals(distance + 1, (enemy.getX() - player.getX()));

		dungeon.tick();
		assertEquals(distance + 2, (enemy.getX() - player.getX()));

		dungeon.tick();
		assertEquals(distance + 3, (enemy.getX() - player.getX()));
	}




}
