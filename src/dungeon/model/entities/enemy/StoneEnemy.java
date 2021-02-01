package dungeon.model.entities.enemy;

import dungeon.model.Dungeon;
import dungeon.model.entities.Movable;

public class StoneEnemy extends Enemy implements Movable {

    public StoneEnemy(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.setImagePath("/stone_monster.png");
    }

    public void updatePerMovement() {
        // not move
    }

}
