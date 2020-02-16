package unsw.dungeon.maker;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Simple dungeon maker
 *
 * @author Kanadech Jirapongtanavech
 *
 */
public class DungeonMaker {

	private StringProperty[][] cell;
	private StringProperty _goal;
	private int keyID = 0;
	private int doorID = 0;

	public DungeonMaker() {
		cell = new StringProperty[15][15];
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				cell[i][j] = new SimpleStringProperty();
			}
		}
		_goal = new SimpleStringProperty();
	}

	public void writeToFile(String filename) {
		String path = "dungeons/" + filename;
		JSONObject obj = createJSONObject();
		try {
			FileWriter file = new FileWriter(path);
			file.write(obj.toString(2));
			System.out.println("Successfully Copied JSON Object to File...");
			file.close();
		} catch (IOException e) {
			System.err.println("Something went wrong");
		}
	}

	private JSONObject createJSONObject() {
		resetID();
		JSONObject obj = new JSONObject();
		obj.put("width", 15);
		obj.put("height", 15);
		JSONArray ent_list = new JSONArray();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (cell[i][j].getValue() == null) {
					continue;
				}
				JSONObject ent = new JSONObject();
				ent.put("x", i);
				ent.put("y", j);
				ent.put("type", cell[i][j].getValue());
				if (cell[i][j].getValue().equals("door")) {
					ent.put("id", doorID);
					doorID++;
				}
				if (cell[i][j].getValue().equals("key")) {
					ent.put("id", keyID);
					keyID++;
				}

				ent_list.put(ent);
			}
		}
		obj.put("entities", ent_list);

		JSONObject goal = new JSONObject();
		obj.put("goal-condition", goal.put("goal", _goal.getValue()));

		System.out.println(obj.toString(2));
		return obj;
	}

	public void clearDungeon() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				cell[i][j].setValue(null);
			}
		}
	}

	public void addSurroundingWalls() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (i == 0)
					cell[i][j].setValue("wall");
				if (i == 14)
					cell[i][j].setValue("wall");
				if (j == 0)
					cell[i][j].setValue("wall");
				if (j == 14)
					cell[i][j].setValue("wall");
			}
		}
	}

	private void resetID() {
		keyID = 0;
		doorID = 0;
	}

	public StringProperty cellProperty(int x, int y) {
		return cell[x][y];
	}

	public StringProperty getGoalProperty() {
		return _goal;
	}

}
