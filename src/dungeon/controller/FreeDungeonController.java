package dungeon.controller;

import dungeon.loader.DungeonControllerLoader;
import dungeon.model.Dungeon;
import dungeon.view.DungeonScreen;
import javafx.scene.image.ImageView;

import java.util.List;

public class FreeDungeonController extends DungeonController {

    public FreeDungeonController(DungeonScreen screen) {
        super(screen);
    }

    public FreeDungeonController(Dungeon dungeon, List<ImageView> initialEntities, DungeonControllerLoader loader) {
        super(dungeon, initialEntities, loader);
    }

    public void setNextDungeonScreen(DungeonScreen s) {
    }

    ;

    public void setPrevDungeonScreen(DungeonScreen s) {
    }

    ;

    public void switchNextDungeon() {
    }

    ;

    public void switchPrevDungeon() {
    }

    ;

    @Override
    public void restart() {
        getCurrDungeonScreen().restart();
    }
}
