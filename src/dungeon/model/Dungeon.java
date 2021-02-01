/**
 *
 */
package dungeon.model;

import dungeon.controller.DungeonController;
import dungeon.model.entities.*;
import dungeon.model.entities.bomb.ExplodedBomb;
import dungeon.model.entities.bomb.LitBomb;
import dungeon.model.entities.bomb.UnlitBomb;
import dungeon.model.entities.door.Door;
import dungeon.model.entities.enemy.Enemy;
import dungeon.model.entities.enemy.StoneEnemy;
import dungeon.model.entities.npc.Princess;
import dungeon.model.entities.npc.Wizard;
import dungeon.model.entities.potion.Potion;
import dungeon.model.goal.Goal;
import dungeon.model.inventory.Inventory;
import dungeon.model.status.Status;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private final int width, height;
    // TODO delete the controller
    private DungeonController controller;
    private ListProperty<Entity> entities;
    private Player player;
    private Status status;
    private Inventory inventory;
    private Goal goal;
    private boolean gameOver;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new SimpleListProperty<Entity>(FXCollections.observableArrayList());
        this.player = null;
        this.controller = null;
        this.inventory = new Inventory();
        this.status = new Status();
        this.goal = null;
        this.gameOver = false;
    }

    // --- setter ---

    public void setController(DungeonController controller) {
        this.controller = controller;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // --- getter ---

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ListProperty<Entity> getEntitiesProperty() {
        return entities;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    // helper function: To retrieve an entity in a specific grid
    public List<Entity> getEntities(int X, int Y) {
        return entities.stream().filter(entity -> (entity.getX() == X && entity.getY() == Y))
                .collect(Collectors.toList());
    }

    public List<Entity> getEntities(EntityType type) {
        return entities.stream().filter(entity -> entity.type() == type).collect(Collectors.toList());
    }

    // a helper function for bomb
    private List<Entity> getNearbyEntities(int X, int Y) {
        List<Entity> nearbyEntities = new LinkedList<Entity>();
        nearbyEntities.addAll(getEntities(X, Y + 1));
        nearbyEntities.addAll(getEntities(X, Y - 1));
        nearbyEntities.addAll(getEntities(X + 1, Y));
        nearbyEntities.addAll(getEntities(X - 1, Y));
        return nearbyEntities;
    }

    public List<Enemy> getEnemies() {
        return entities.stream().filter(entity -> entity.type() == EntityType.ENEMY).map(Enemy.class::cast)
                .collect(Collectors.toList());
    }

    public List<Switch> getSwitches() {
        return entities.stream().filter(entity -> entity.type() == EntityType.SWITCH).map(Switch.class::cast)
                .collect(Collectors.toList());
    }

    public List<Treasure> getTreasures() {
        return entities.stream().filter(entity -> entity.type() == EntityType.TREASURE).map(Treasure.class::cast)
                .collect(Collectors.toList());
    }

    public List<LitBomb> getLitBombs() {
        return entities.stream().filter(entity -> entity.type() == EntityType.LITBOMB).map(LitBomb.class::cast)
                .collect(Collectors.toList());
    }

    public List<Wizard> getWizards() {
        return entities.stream().filter(entity -> entity.type() == EntityType.WIZARD).map(Wizard.class::cast)
                .collect(Collectors.toList());
    }

    public List<Princess> getPrincess() {
        return entities.stream().filter(entity -> entity.type() == EntityType.PRINCESS).map(Princess.class::cast)
                .collect(Collectors.toList());
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Status getStatus() {
        return status;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    // functions for entities

    // create a new entity in the dungeon
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    // remove an entity from the dungeon
    public void removeEntity(Entity entity) {
        this.entities.remove(entity);
    }

    // player movement

    public void movePlayer(Direction direction) {
        if (player != null)
            player.move(direction);
    }

    // helper function: check whether a grid is walkable
    public boolean canOccupyGrid(int X, int Y) {
        return this.getEntities(X, Y).stream().allMatch(Entity::canPassThrough)
                && (0 <= X && X < this.getWidth() && 0 <= Y && Y < this.getHeight());
    }

    /*
     * For every player movement, something must change Might be better to have
     * enemy observes player And put invincibility as an attribute inside the player
     * class
     */
    public void tick() {
        if (goalAchieved()) {
            gameOver();
        }

        // update all all observers
        goal.update();
        status.updatePerMovement();
        getEnemies().forEach(Enemy::updatePerMovement);
        getLitBombs().forEach(LitBomb::updatePerMovement);
    }

    // for picking up entities

    public void pickUp(Entity entity) {
        switch (entity.type()) {
            case SWORD:
                pickUpSword((Sword) entity);
                break;
            case TREASURE:
                pickUpTreasure((Treasure) entity);
                break;
            case UNLITBOMB:
                pickUpBomb((UnlitBomb) entity);
                break;
            case KEY:
                pickUpKey((Key) entity);
                break;
            case INVINCIBLEPOTION:
            case INVISIBLEPOTION:
                pickUpPotion((Potion) entity);
                break;
            default:
                break;
        }
    }

    // pickUp helper

    private void pickUpSword(Sword sword) {
        removeEntity(sword);
        this.getInventory().pickSword();
    }

    private void pickUpTreasure(Treasure treasure) {
        removeEntity(treasure);
        this.getInventory().pickTreasure();
    }

    private void pickUpBomb(UnlitBomb bomb) {
        removeEntity(bomb);
        this.getInventory().pickBomb();
    }

    private void pickUpPotion(Potion potion) {
        System.out.println("pick up " + potion);
        switch (potion.type()) {
            case INVINCIBLEPOTION:
                this.status.becomeInvincible();
                break;
            case INVISIBLEPOTION:
                this.status.becomeInvisible();
                break;
            default:
                System.err.println("What potion did I pick?");
                break;
        }
        removeEntity(potion);
    }

    private void pickUpKey(Key key) {
        // player can only have 1 key
        if (this.getInventory().pickKey(key.getId()))
            removeEntity(key);
    }

    // --- other interactions

    public boolean isPlayerInvincible() {
        return status.isInvincible();
    }

    public void fightEnemy(Enemy enemy) {
        if (isPlayerInvincible()) {
            removeEntity(enemy);
        } else if (this.getInventory().useSword()) {
            removeEntity(enemy);
            if (enemy instanceof StoneEnemy)
                this.getInventory().breakSword();
        } else {
            killPlayer();
        }
    }

    public void tryOpenDoor(Door door) {
        if (door.canPassThrough())
            System.out.println("Door already opened.");
        else if (this.getInventory().getKey() == null)
            System.out.println("No key");
        else if (this.getInventory().getKeyID() != door.getId())
            System.out.println("Wrong key");
        else {
            this.getInventory().useKey();
            door.open();
        }
    }

    public void placeBomb() {
        if (getInventory().useBomb()) {
            System.out.println("Use bomb");
            addEntity(new LitBomb(player.getX(), player.getY(), this));
        } else {
            System.out.println("No bomb to use");
        }
    }

    public void explodeBomb(LitBomb bomb) {
        // put exploded Bomb
        addEntity(new ExplodedBomb(bomb.getX() + 1, bomb.getY(), this));
        addEntity(new ExplodedBomb(bomb.getX() - 1, bomb.getY(), this));
        addEntity(new ExplodedBomb(bomb.getX(), bomb.getY() + 1, this));
        addEntity(new ExplodedBomb(bomb.getX(), bomb.getY() - 1, this));
        // kill nearby Entities
        List<Entity> nearbyEntities = getNearbyEntities(bomb.getX(), bomb.getY());
        nearbyEntities.addAll(getEntities(bomb.getX(), bomb.getY()));
        for (Entity entity : nearbyEntities) {
            switch (entity.type()) {
                case ENEMY:
                    removeEntity(entity);
                    break;
                case BOULDER:
                    removeEntity(entity);
                    break;
                case PLAYER:
                    killPlayer();
                    break;
                default:
                    break;
            }
        }
    }

    // to finish a game over
    private void gameOver() {
        System.out.println("Game Over!");
        gameOver = true;
        if (controller != null) {
            if (goalAchieved())
                controller.gameOver("Congrat! You finished the game!");
            else
                controller.gameOver("Oppps. You failed. Please retry!");
        }
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public boolean goalAchieved() {
        return this.goal.isSatisfied();
    }

    // a helper function to kill the player
    private void killPlayer() {
        // if the player is invincible now, it would not die
        if (!isPlayerInvincible()) {
            removeEntity(this.player);
            this.player = null;
            if (controller != null)
                gameOver();
        }
    }

    public void switchNextDungeon() {
        if (controller != null) {
            this.controller.switchNextDungeon();
        }
    }

    public void switchPrevDungeon() {
        if (controller != null) {
            this.controller.switchPrevDungeon();
        }
    }

    public void inheritFrom(Dungeon prev_dungeon) {
        this.inventory = prev_dungeon.getInventory();
        this.status = prev_dungeon.getStatus();
    }
}
