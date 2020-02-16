package unsw.dungeon.soundplayer;

import javafx.scene.media.AudioClip;
import unsw.dungeon.DungeonApplication;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DungeonSoundPlayer {

	@SuppressWarnings("serial")
	private static Map<DungeonSound, AudioClip> sounds = new HashMap<DungeonSound, AudioClip>() {
		
		{
			put(DungeonSound.FIGHT, new AudioClip(new File("sounds/fight.mp3").toURI().toString()));
			put(DungeonSound.POTION, new AudioClip(new File("sounds/potion.mp3").toURI().toString()));
			put(DungeonSound.OPEN_DOOR, new AudioClip(new File("sounds/open_door.mp3").toURI().toString()));
			put(DungeonSound.ACHIEVE_ITEM, new AudioClip(new File("sounds/achieve_item.mp3").toURI().toString()));
			put(DungeonSound.SWITCH_FLOOR, new AudioClip(new File("sounds/switch_floor.mp3").toURI().toString()));
			put(DungeonSound.EXPLOSION, new AudioClip(new File("sounds/explosion.mp3").toURI().toString()));
			put(DungeonSound.GAME_OVER, new AudioClip(new File("sounds/gameover.mp3").toURI().toString()));
			put(DungeonSound.BGM, new AudioClip(new File("sounds/bg.mp3").toURI().toString()));
		}
	};

	static private void adjustVolume() {
		sounds.forEach((type, audio) -> audio.setVolume(DungeonApplication.getGameVolume() / 100D));
	}

	static public void playSoundEffect(DungeonSound dungeonSound_type) {
		adjustVolume();
		sounds.get(dungeonSound_type).play();
	}

	static public void playBGM() {
		adjustVolume();
		AudioClip BGM = sounds.get(DungeonSound.BGM);
		BGM.setCycleCount(AudioClip.INDEFINITE);
		;
		BGM.stop();
		BGM.play();
	}

	static public void stopBGM() {
		AudioClip BGM = sounds.get(DungeonSound.BGM);
		BGM.stop();
	}
}