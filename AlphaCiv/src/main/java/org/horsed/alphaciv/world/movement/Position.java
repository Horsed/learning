package org.horsed.alphaciv.world.movement;

/**
 * Position on the world map.
 * 
 * Responsibilities: 1) Know a specific location (row,column).
 */
public class Position {

	/**
	 * create a position.
	 * 
	 * @param r
	 *          the row
	 * @param c
	 *          the column
	 */
	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}

	protected int r;
	protected int c;

	/**
	 * get the row represented by this position.
	 * 
	 * @return the row.
	 */
	public int getRow() {
		return r;
	}

	/**
	 * get the column represented by this position.
	 * 
	 * @return the column.
	 */
	public int getColumn() {
		return c;
	}

	public Position above() {
		return new Position(this.r - 1, this.c);
	}

	public Position topRight() {
		return new Position(this.r - 1, this.c + 1);
	}

	public Position right() {
		return new Position(this.r, this.c + 1);
	}

	public Position downRight() {
		return new Position(this.r + 1, this.c + 1);
	}

	public Position under() {
		return new Position(this.r + 1, this.c);
	}

	public Position downLeft() {
		return new Position(this.r + 1, this.c - 1);
	}

	public Position left() {
		return new Position(this.r, this.c - 1);
	}

	public Position topLeft() {
		return new Position(this.r - 1, this.c - 1);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o.getClass() != Position.class) {
			return false;
		}
		Position other = (Position) o;
		return r == other.r && c == other.c;
	}

	@Override
	public int hashCode() {
		// works ok for positions up to columns == 479
		return 479 * r + c;
	}

	@Override
	public String toString() {
		return "[" + r + "," + c + "]";
	}
}
