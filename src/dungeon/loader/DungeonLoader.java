package unsw.dungeon.loader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.*;
import unsw.dungeon.model.entities.bomb.UnlitBomb;
import unsw.dungeon.model.entities.door.Door;
import unsw.dungeon.model.entities.enemy.HoundEnemy;
import unsw.dungeon.model.entities.enemy.HumanEnemy;
import unsw.dungeon.model.entities.enemy.StoneEnemy;
import unsw.dungeon.model.entities.npc.Princess;
import unsw.dungeon.model.entities.npc.Wizard;
import unsw.dungeon.model.entities.potion.InvinciblePotion;
import unsw.dungeon.model.entities.potion.InvisiblePotion;
import unsw.dungeon.model.goal.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

	private JSONObject json;

	public DungeonLoader(String filename) throws FileNotFoundException {
		json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
	}

	/**
	 * Parses the JSON to create a dungeon.
	 * 
	 * @return
	 */
	public Dungeon load() {
		int width = json.getInt("width");
		int height = json.getInt("height");

		Dungeon dungeon = new Dungeon(width, height);

		// load entities
		JSONArray jsonEntities = json.getJSONArray("entities");
		for (int i = 0; i < jsonEntities.length(); i++) {
			loadEntity(dungeon, jsonEntities.getJSONObject(i));
		}

		// load goal
		JSONObject goalConditions = json.getJSONObject("goal-condition");
		System.out.println(goalConditions.toString()); // for debugging
		dungeon.setGoal(loadGoal(goalConditions, dungeon)); // give dungeon base goals
		return dungeon;
	}

	// add goals for the dungeon
	private Goal loadGoal(JSONObject json, Dungeon dungeon) {
		String goal_type = json.getString("goal");
		Goal goal = new AndGoals(dungeon); // just to satisfy the compiler
		switch (goal_type) {
		case "exit":
			goal = new ExitGoal(dungeon);
			break;
		case "enemies":
			goal = new EnemyGoal(dungeon);
			break;
		case "boulders":
			goal = new SwitchGoal(dungeon);
			break;
		case "treasure":
			goal = new TreasureGoal(dungeon);
			break;
		case "wizard":
			goal = new WizardGoal(dungeon);
			break;
		case "princess":
			goal = new PrincessGoal(dungeon);
			break;
		case "AND":
			goal = new AndGoals(dungeon);
			break;
		case "OR":
			goal = new OrGoals(dungeon);
			break;
		}

		if (!goal.isLeaf()) {
			JSONArray subGoalConditions = json.getJSONArray("subgoals");
			for (int i = 0; i < subGoalConditions.length(); i++) {
				Goal subgoal = loadGoal(subGoalConditions.getJSONObject(i), dungeon);
				goal.addSubgoal(subgoal);
			}
		}

		return goal;
	}

	// add entities for the dungeon
	private void loadEntity(Dungeon dungeon, JSONObject json) {
		String type = json.getString("type");
		int x = json.getInt("x");
		int y = json.getInt("y");

		Entity entity = null;
		switch (type) {
		case "player":
			Player player = new Player(x, y, dungeon);
			dungeon.setPlayer(player);
			onLoad(player);
			entity = player;
			break;
		case "wall":
			entity = new Wall(x, y, dungeon);
			onLoad(entity);
			break;
		case "exit":
			entity = new Exit(x, y, dungeon);
			onLoad(entity);
			break;
		case "treasure":
			entity = new Treasure(x, y, dungeon);
			onLoad(entity);
			break;
		case "door":
			entity = new Door(x, y, dungeon, json.getInt("id"));
			onLoad(entity);
			break;
		case "key":
			entity = new Key(x, y, dungeon, json.getInt("id"));
			onLoad(entity);
			break;
		case "boulder":
			entity = new Boulder(x, y, dungeon);
			onLoad(entity);
			break;
		case "switch":
			entity = new Switch(x, y, dungeon);
			onLoad(entity);
			break;
		case "bomb":
			entity = new UnlitBomb(x, y, dungeon);
			onLoad(entity);
			break;
		case "enemy":
			entity = new HumanEnemy(x, y, dungeon);
			onLoad(entity);
			break;
		case "hound":
			entity = new HoundEnemy(x, y, dungeon);
			onLoad(entity);
			break;
		case "sword":
			entity = new Sword(x, y, dungeon);
			onLoad(entity);
			break;
		case "invincibility":
			entity = new InvinciblePotion(x, y, dungeon);
			onLoad(entity);
			break;
		case "wizard":
			entity = new Wizard(x, y, dungeon);
			onLoad(entity);
			break;
		case "princess":
			entity = new Princess(x, y, dungeon);
			onLoad(entity);
			break;
		case "up_floor":
			entity = new UpFloor(x, y, dungeon);
			onLoad(entity);
			break;
		case "down_floor":
			entity = new DownFloor(x, y, dungeon);
			onLoad(entity);
			break;
		case "stone":
			entity = new StoneEnemy(x, y, dungeon);
			onLoad(entity);
			break;
		case "invisible":
			entity = new InvisiblePotion(x, y, dungeon);
			onLoad(entity);
			break;
		}
		if (entity != null)
			dungeon.addEntity(entity);
	}

	public abstract void onLoad(Entity entity);
}
