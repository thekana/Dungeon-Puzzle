package dungeon.controller;

import java.util.List;

import javafx.scene.image.ImageView;
import dungeon.loader.DungeonControllerLoader;
import dungeon.model.Dungeon;
import dungeon.view.DungeonScreen;

public class FreeDungeonController extends DungeonController {

	public FreeDungeonController(DungeonScreen screen) {
		super(screen);
	}

	public FreeDungeonController(Dungeon dungeon, List<ImageView> initialEntities, DungeonControllerLoader loader) {
		super(dungeon, initialEntities, loader);
	}

	public void setNextDungeonScreen(DungeonScreen s) {};
	public void setPrevDungeonScreen(DungeonScreen s) {};
	public void switchNextDungeon() {};
	public void switchPrevDungeon() {};

	@Override
	public void restart() {
		getCurrDungeonScreen().restart();
	}
}
