package unsw.dungeon.model.entities.bomb;

public class LitBombState2 implements LitBombState {

	private LitBomb bomb;

	LitBombState2(LitBomb bomb) {
		this.bomb = bomb;
		bomb.setImagePath(this.getImagePath());
	}

	@Override
	public String getImagePath() {
		return "/bomb_lit_2.png";
	}

	@Override
	public void nextState() {
		bomb.setState(new LitBombState3(bomb));
	}

}
