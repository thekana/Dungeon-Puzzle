package unsw.test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.model.Direction;
import unsw.dungeon.model.entities.*;
import unsw.dungeon.model.entities.bomb.LitBomb;
import unsw.dungeon.model.entities.bomb.UnlitBomb;
import unsw.dungeon.model.entities.door.Door;
import unsw.dungeon.model.entities.enemy.HoundEnemy;
import unsw.dungeon.model.entities.enemy.HumanEnemy;
import unsw.dungeon.model.entities.potion.InvinciblePotion;

import static org.junit.jupiter.api.Assertions.*;

public class testPlayerInteraction extends testSetup {

	/*
	 * Player can collect any number of treasures
	 */
	@Test
	void testPickUpSword() {
		setup(5, 3, 1, 1);

		dungeon.addEntity(new Sword(2, 1, dungeon));
		dungeon.addEntity(new Sword(3, 1, dungeon));

		// the player does not have any sword at first, so it cannot use sword
		assertFalse(dungeon.getInventory().useSword());
		assertEquals(2, dungeon.getEntities(EntityType.SWORD).size());

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals(1, dungeon.getEntities(EntityType.SWORD).size());
		assertEquals(5, dungeon.getInventory().getSwordDurability());
		assertTrue(dungeon.getInventory().useSword());	// now the player can use sword
		assertEquals(4, dungeon.getInventory().getSwordDurability());	// after using a sword, the durability decreases

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals(5, dungeon.getInventory().getSwordDurability()); // the durability would be refreshed
		assertEquals(0, dungeon.getEntities(EntityType.SWORD).size());
	}

	/*
	 * Player may pick up any number of bombs
	 */
	@Test
	void testPickUpBomb() {
		setup(5, 3, 1, 1);

		dungeon.addEntity(new UnlitBomb(2, 1, dungeon));
		dungeon.addEntity(new UnlitBomb(3, 1, dungeon));

		assertEquals(2, dungeon.getEntities(EntityType.UNLITBOMB).size());
		assertEquals(0, dungeon.getInventory().getBombNum());

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals(1, dungeon.getEntities(EntityType.UNLITBOMB).size());
		assertEquals(1, dungeon.getInventory().getBombNum());

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals(0, dungeon.getEntities(EntityType.UNLITBOMB).size());
		assertEquals(2, dungeon.getInventory().getBombNum());
	}

	/*
	 * Player picking potion will set invincibility step to 5. Player is invincible
	 * for 5 steps counting the first step from the moment he steps into the grid
	 * containing the potion
	 */
	@Test
	void testPickUpPotion() {
		setup(5, 3, 1, 1);

		dungeon.addEntity(new InvinciblePotion(2, 1, dungeon));
		dungeon.addEntity(new InvinciblePotion(3, 1, dungeon));

		assertEquals(2, dungeon.getEntities(EntityType.INVINCIBLEPOTION).size());
		assertEquals(false, dungeon.getStatus().isInvincible());

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals(1, dungeon.getEntities(EntityType.INVINCIBLEPOTION).size());
		assertEquals(true, dungeon.getStatus().isInvincible()); // at this point he becomes invincible

		dungeon.tick();
		assertEquals(4, dungeon.getStatus().getInvincibleStep());

		dungeon.movePlayer(Direction.RIGHT); // collects another potion
		assertEquals(0, dungeon.getEntities(EntityType.INVINCIBLEPOTION).size());
		assertEquals(true, dungeon.getStatus().isInvincible()); // at this point he becomes invincible
		assertEquals(5, dungeon.getStatus().getInvincibleStep());

		dungeon.tick();
		assertEquals(4, dungeon.getStatus().getInvincibleStep());

		dungeon.tick();
		assertEquals(3, dungeon.getStatus().getInvincibleStep());

		dungeon.tick();
		assertEquals(2, dungeon.getStatus().getInvincibleStep());

		dungeon.tick();
		assertEquals(1, dungeon.getStatus().getInvincibleStep());

		dungeon.tick();
		assertEquals(false, dungeon.getStatus().isInvincible()); // no longer invincible

	}

	/*
	 * Player may only have one key at a time
	 */
	@Test
	void testPickUpKey() {
		setup(5, 3, 1, 1);

		dungeon.addEntity(new Key(2, 1, dungeon, 99));
		dungeon.addEntity(new Key(3, 1, dungeon, 20));

		assertEquals(2, dungeon.getEntities(EntityType.KEY).size());

		dungeon.movePlayer(Direction.RIGHT);
		assertNotEquals(null, dungeon.getInventory().getKey());
		assertEquals(1, dungeon.getEntities(EntityType.KEY).size());
		assertEquals(99, dungeon.getInventory().getKeyID());

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals(1, dungeon.getEntities(EntityType.KEY).size());
		assertEquals(99, dungeon.getInventory().getKeyID());
	}

	/*
	 * Player can collect any number of treasures
	 */
	@Test
	void testPickUpTreasure() {
		setup(5, 3, 1, 1);

		dungeon.addEntity(new Treasure(2, 1, dungeon));
		dungeon.addEntity(new Treasure(3, 1, dungeon));

		// the player does not have any treasure at first
		assertEquals(0, dungeon.getInventory().getTreasureNum());
		assertEquals(2, dungeon.getEntities(EntityType.TREASURE).size());

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals(1, dungeon.getInventory().getTreasureNum());
		assertEquals(1, dungeon.getEntities(EntityType.TREASURE).size());

		dungeon.movePlayer(Direction.RIGHT);
		assertEquals(2, dungeon.getInventory().getTreasureNum());
		assertEquals(0, dungeon.getEntities(EntityType.TREASURE).size());
	}


	// --- test bombs ----

	/*
	 * Test lit a bomb
	 */
	@Test
	void testLitBomb() {
		setup(10, 1, 0, 0);

		dungeon.placeBomb(); // nothing should happen since the player does not have bombs
		assertEquals(0, dungeon.getEntities(EntityType.LITBOMB).size());

		dungeon.pickUp(new UnlitBomb(0, 0, dungeon)); // let player pick up a bomb
		dungeon.placeBomb(); // a bomb should be placed since the player has a bomb

		// a lit bomb would be placed in the grid where the player is
		assertEquals(1, dungeon.getEntities(EntityType.LITBOMB).size());
		LitBomb bombLit = (LitBomb) dungeon.getEntities(EntityType.LITBOMB).get(0);
		assertEquals(dungeon.getPlayer().getX(), bombLit.getX());
		assertEquals(dungeon.getPlayer().getY(), bombLit.getY());
		// the number of bombs in the player’s inventory is decreased by 1
		assertEquals(0, dungeon.getInventory().getBombNum());

		assertEquals("/bomb_lit_1.png", bombLit.getImagePath());

		dungeon.tick();
		assertEquals("/bomb_lit_2.png", bombLit.getImagePath());

		dungeon.tick();
		assertEquals("/bomb_lit_3.png", bombLit.getImagePath());

		dungeon.tick();
		assertEquals("/bomb_lit_4.png", bombLit.getImagePath());

		dungeon.tick();
		assertEquals(0, dungeon.getEntities(EntityType.LITBOMB).size());	// the bomb should be removed after explosion
	}

	/*
	 * Exploded bombs will destroy neighbouring boulders
	 */
	@Test
	void testLitBombDestroyBouldersEnemies() {
		setup(10, 5, 0, 0);

		dungeon.addEntity(new Boulder(2, 1, dungeon));
		dungeon.addEntity(new Boulder(2, 3, dungeon));
		dungeon.addEntity(new HumanEnemy(3, 2, dungeon));
		dungeon.addEntity(new HoundEnemy(3, 2, dungeon));

		assertEquals(2, dungeon.getEntities(EntityType.BOULDER).size());
		assertEquals(2, dungeon.getEntities(EntityType.ENEMY).size());

		LitBomb bomb = new LitBomb(2, 2, dungeon);
		dungeon.addEntity(bomb);
		dungeon.explodeBomb(bomb);

		assertEquals(0, dungeon.getEntities(EntityType.BOULDER).size());
		assertEquals(0, dungeon.getEntities(EntityType.ENEMY).size());
	}

	/*
	 * When invincible the player cannot die from explosion
	 */
	@Test
	void testLitBombKillPlayer() {
		setup(3,1,0,0);

		LitBomb bomb = new LitBomb(1, 0, dungeon);
		dungeon.addEntity(bomb);
		dungeon.explodeBomb(bomb);

		assertTrue(dungeon.isGameOver());
	}

	/*
	 * When invincible the player cannot die from explosion
	 */
	@Test
	void testBombInvinciblePlayer() {
		setup(2,1,0,0);

		dungeon.pickUp(new InvinciblePotion(0, 0, dungeon));

		LitBomb bomb = new LitBomb(1, 0, dungeon);
		dungeon.addEntity(bomb);
		dungeon.explodeBomb(bomb);

		assertNotEquals(null, dungeon.getPlayer());
	}


	/*
	 * Player can use sword to kill the enemy Each time a sword is used its
	 * durability is decreased by one.
	 */
	@Test
	void testUseSwordToFight() {

		// initialize a 3 * 1 dungeon, and add an enemy and two swords into it
		setup(4, 1, 0, 0);
		dungeon.addEntity(new HumanEnemy(1, 0, dungeon));

		assertEquals(1, dungeon.getEntities(EntityType.ENEMY).size());

		dungeon.pickUp(new Sword(0, 0, dungeon)); // player pick up a sword

		// if the player with a sword collides with an enemy, the enemy would be destroyed and the sword’s durability would be reduced by 1.
		dungeon.tick(); // collide with the enemy but the player has sword
		assertEquals(0, dungeon.getEntities(EntityType.ENEMY).size());	// the player should use the sword to kill enemy
		assertEquals(4, dungeon.getInventory().getSwordDurability());	// the durability should decrease

		// Once a sword’s durability reaches 0, the sword is destroyed and removed from the player.
		for (int i = 0; i < 4; i++)
			dungeon.fightEnemy(new HumanEnemy(0, 0, dungeon));	// fight enemy four times
		assertFalse(dungeon.getInventory().useSword()); // the sword should be broken now
	}

	/*
	 * Player can only use key to unlock matching door
	 */
	@Test
	void testUseKeyToOpenDoor() {
		setup(3, 1, 1, 0);

		Door door1 = new Door(0, 0, dungeon, 1);
		Door door2 = new Door(2, 0, dungeon, 2);
		dungeon.addEntity(door1);
		dungeon.addEntity(door2);

		// If the player holds a corresponding key to a door he is at, the door would be opened the key would be removed from the player.
		// If the player does not have a key or the key does not match, nothing should happen and the door would not be opened.

		dungeon.pickUp(new Key(0, 0, dungeon, 1));

		dungeon.tryOpenDoor(door2);		// should fail
		assertFalse(door2.canPassThrough());

		dungeon.tryOpenDoor(door1);		// should success
		assertTrue(door1.canPassThrough());

		dungeon.pickUp(new Key(0, 0, dungeon, 2));

		dungeon.tryOpenDoor(door2);		// should success
		assertTrue(door2.canPassThrough());
	}


	// test pushing boulders

	/*
	 * Boulder can be pushed by the player
	 */
	@Test
	void testPushBoulder() {
		setup(5, 5, 2, 2);

		Boulder boulderRight = new Boulder(3, 2, dungeon);
		Boulder boulderLeft = new Boulder(1, 2, dungeon);
		Boulder boulderUp = new Boulder(2, 1, dungeon);
		Boulder boulderDown = new Boulder(2, 3, dungeon);
		dungeon.addEntity(boulderRight);
		dungeon.addEntity(boulderLeft);
		dungeon.addEntity(boulderUp);
		dungeon.addEntity(boulderDown);

		dungeon.movePlayer(Direction.RIGHT); // pushing
		assertEquals(4, boulderRight.getX());
		assertEquals(2, boulderRight.getY());

		dungeon.movePlayer(Direction.LEFT); // pushing
		assertEquals(0, boulderLeft.getX());
		assertEquals(2, boulderLeft.getY());

		dungeon.movePlayer(Direction.UP); // pushing
		assertEquals(2, boulderUp.getX());
		assertEquals(0, boulderUp.getY());

		dungeon.movePlayer(Direction.DOWN); // pushing
		assertEquals(2, boulderDown.getX());
		assertEquals(4, boulderDown.getY());
	}

	/*
	 * Player is strong enough to only push 1 boulder at a time
	 */
	@Test
	void testCannotPushTwoBoulders() {
		setup(9, 9, 4, 4);

		Boulder boulderRight = new Boulder(5, 4, dungeon);
		Boulder boulderLeft = new Boulder(3, 4, dungeon);
		Boulder boulderUp = new Boulder(4, 3, dungeon);
		Boulder boulderDown = new Boulder(4, 5, dungeon);

		dungeon.addEntity(boulderRight);
		dungeon.addEntity(new Boulder(6, 4, dungeon));
		dungeon.addEntity(boulderLeft);
		dungeon.addEntity(new Boulder(2, 4, dungeon));
		dungeon.addEntity(boulderUp);
		dungeon.addEntity(new Boulder(4, 2, dungeon));
		dungeon.addEntity(boulderDown);
		dungeon.addEntity(new Boulder(4, 6, dungeon));

		dungeon.movePlayer(Direction.RIGHT); // pushing
		assertEquals(5, boulderRight.getX());
		assertEquals(4, boulderRight.getY());

		dungeon.movePlayer(Direction.LEFT); // pushing
		assertEquals(3, boulderLeft.getX());
		assertEquals(4, boulderLeft.getY());

		dungeon.movePlayer(Direction.UP); // pushing
		assertEquals(4, boulderUp.getX());
		assertEquals(3, boulderUp.getY());

		dungeon.movePlayer(Direction.DOWN); // pushing
		assertEquals(4, boulderDown.getX());
		assertEquals(5, boulderDown.getY());
	}

	/*
	 * Cant push boulder through wall and door
	 */
	@Test
	void testPushBoulderWithObstacle() {
		setup(9, 9, 4, 4);

		Boulder boulderRight = new Boulder(5, 4, dungeon);
		Boulder boulderLeft = new Boulder(3, 4, dungeon);
		Boulder boulderUp = new Boulder(4, 3, dungeon);
		Boulder boulderDown = new Boulder(4, 5, dungeon);

		dungeon.addEntity(boulderRight);
		dungeon.addEntity(new Wall(6, 4, dungeon));
		dungeon.addEntity(boulderLeft);
		dungeon.addEntity(new Wall(2, 4, dungeon));

		dungeon.addEntity(boulderUp);
		dungeon.addEntity(new Door(4, 2, dungeon, 0));
		dungeon.addEntity(boulderDown);
		dungeon.addEntity(new Door(4, 6, dungeon, 0));

		dungeon.movePlayer(Direction.RIGHT); // pushing
		assertEquals(5, boulderRight.getX());
		assertEquals(4, boulderRight.getY());

		dungeon.movePlayer(Direction.LEFT); // pushing
		assertEquals(3, boulderLeft.getX());
		assertEquals(4, boulderLeft.getY());

		dungeon.movePlayer(Direction.UP); // pushing
		assertEquals(4, boulderUp.getX());
		assertEquals(3, boulderUp.getY());

		dungeon.movePlayer(Direction.DOWN); // pushing
		assertEquals(4, boulderDown.getX());
		assertEquals(5, boulderDown.getY());
	}

	/*
	 * Player cannot push boulder into the enemy
	 */
	@Test
	void testPushBoulderEnemy() {
		setup(3, 1, 0, 0);

		Boulder boulder = new Boulder(1, 0, dungeon);
		dungeon.addEntity(boulder);
		dungeon.addEntity(new HumanEnemy(2, 0, dungeon));

		dungeon.movePlayer(Direction.RIGHT); // pushing

		assertEquals(1, boulder.getX());
		assertEquals(0, boulder.getY());
	}

	/*
	 * Player can pushed boulder through entities
	 */
	@Test
	void testPushBoulderThroughEntity() {
		setup(5, 5, 2, 2);

		Boulder boulderRight = new Boulder(3, 2, dungeon);
		dungeon.addEntity(boulderRight);
		dungeon.addEntity(new InvinciblePotion(4, 2, dungeon));

		dungeon.movePlayer(Direction.RIGHT); // pushing
		assertEquals(4, boulderRight.getX());
		assertEquals(2, boulderRight.getY());
	}

	/*
	 * Player can pushed boulder through opened door
	 */
	@Test
	void testPushBoulderThroughOpenedDoor() {
		setup(4, 1, 0, 0);

		Boulder boulder = new Boulder(1, 0, dungeon);
		dungeon.addEntity(boulder);

		// open the door
		Door door = new Door(2, 0, dungeon, 0);
		dungeon.addEntity(door);
		dungeon.pickUp(new Key(0, 0, dungeon, 0));
		dungeon.tryOpenDoor(door);

		dungeon.movePlayer(Direction.RIGHT); // pushing
		dungeon.movePlayer(Direction.RIGHT); // boulder at door
		dungeon.movePlayer(Direction.RIGHT); // boulder passes door

		assertEquals(3, boulder.getX());
		assertEquals(0, boulder.getY());
	}
}
