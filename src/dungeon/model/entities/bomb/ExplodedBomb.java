package dungeon.model.entities.bomb;

import dungeon.model.Dungeon;

public class ExplodedBomb extends LitBomb {

    public ExplodedBomb(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.setState(new LitBombState4(this));
        this.setPassThrough(true);
    }

}
