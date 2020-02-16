package unsw.dungeon.model.entities.bomb;

public class LitBombState1 implements LitBombState {

	private LitBomb bomb;

	LitBombState1(LitBomb bomb) {
		this.bomb = bomb;
		bomb.setImagePath(this.getImagePath());
	}

	@Override
	public String getImagePath() {
		return "/bomb_lit_1.png";
	}

	@Override
	public void nextState() {
		bomb.setState(new LitBombState2(bomb));
	}

}
