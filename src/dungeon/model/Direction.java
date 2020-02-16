/**
 *
 */
package unsw.dungeon.model;

public enum Direction {

	UP(0, -1),
	DOWN(0, 1),
	LEFT(-1, 0),
	RIGHT(1, 0);

	// definition

	private final int dx, dy;

	private Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public int dx() {
		return dx;
	}

	public int dy() {
		return dy;
	}

	// calculate the new X and Y

	public int newX(int X) {
		return X + dx;
	}

	public int newY(int Y) {
		return Y + dy;
	}

}
