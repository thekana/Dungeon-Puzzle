package unsw.dungeon.model.entities.enemy;

import unsw.dungeon.model.Dungeon;
import unsw.dungeon.model.entities.*;

public class HoundEnemy extends Enemy implements Movable {

    private EnemyBehaviour moveClose = new EnemyMoveCloseStupid();
    private EnemyBehaviour moveAway = new EnemyMoveAway();

    public HoundEnemy(int x, int y, Dungeon dungeon) {
        super(x, y, dungeon);
        this.setImagePath("/hound.png");
        this.setBehaviour(moveClose);
    }

    public void updatePerMovement() {
        // set strategy
        if (getDungeon().getStatus().isInvisible()) {
            System.out.println("player is invisible!");
            return;
        } else if (getDungeon().isPlayerInvincible())
            this.setBehaviour(moveAway);
        else
            this.setBehaviour(moveClose);

        Player player = getDungeon().getPlayer();
        if (player != null) // avoid null pointer when the player is killed by other enemy
            this.move(player);
    }

}
