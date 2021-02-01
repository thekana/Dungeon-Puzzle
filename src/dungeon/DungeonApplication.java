package dungeon;

import dungeon.loader.DungeonScreenLoader;
import dungeon.view.DungeonMakerScreen;
import dungeon.view.MenuScreen;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class DungeonApplication extends Application {


    private static final String[] levels_json = new String[]{
            "intro.json",
            "level1.json",
            "level6.json",
            "level7.json",
            "level8.json",
    };
    // Global config
    private static int game_speed = 50;

    public static int getGameSpeed() {
        return game_speed;
    }

    public static void setGameSpeed(int speed) {
        game_speed = speed;
    }

    public static String[] getLevelsJson() {
        return levels_json;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        // create the menu screen and dungeon screen
        MenuScreen menuScreen = new MenuScreen(primaryStage);

        // dungeon maker screen
        DungeonMakerScreen makerScreen = new DungeonMakerScreen(primaryStage);
        makerScreen.getController().setMenuScreen(menuScreen);
        menuScreen.getController().setMakerScreen(makerScreen);

        // create the storyDungeon screen
        DungeonScreenLoader.setPrimaryStage(primaryStage);
        DungeonScreenLoader.setMenuScreen(menuScreen);

        menuScreen.start();
    }
}
