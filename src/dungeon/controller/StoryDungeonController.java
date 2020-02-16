package unsw.dungeon.controller;

import java.util.List;

import javafx.scene.image.ImageView;
import unsw.dungeon.DungeonApplication;
import unsw.dungeon.loader.DungeonControllerLoader;
import unsw.dungeon.loader.DungeonScreenLoader;
import unsw.dungeon.model.Dungeon;
import unsw.dungeon.view.DungeonScreen;

public class StoryDungeonController extends DungeonController {

	private DungeonScreen nextDungeonScreen;
	private DungeonScreen prevDungeonScreen;

	public StoryDungeonController(DungeonScreen screen) {
		super(screen);
	}

	public StoryDungeonController(Dungeon dungeon, List<ImageView> initialEntities, DungeonControllerLoader loader) {
		super(dungeon, initialEntities, loader);
	}

	public void setNextDungeonScreen(DungeonScreen nextDungeonScreen) {
		this.nextDungeonScreen = nextDungeonScreen;
	}

	public void setPrevDungeonScreen(DungeonScreen prevDungeonScreen) {
		this.prevDungeonScreen = prevDungeonScreen;
	}

	public void switchNextDungeon() {
		getTimeline().stop();
		this.nextDungeonScreen.start(getDungeon());
	}

	public void switchPrevDungeon() {
		getTimeline().stop();
		this.prevDungeonScreen.start(getDungeon());
	}

	@Override
	public void restart() {
		DungeonScreen dungeonScreen = DungeonScreenLoader.loadStoryScreen(DungeonApplication.getLevelsJson());
		if (dungeonScreen != null)
			dungeonScreen.start();
	}
}
