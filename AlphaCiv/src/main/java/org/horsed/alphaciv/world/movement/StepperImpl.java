package org.horsed.alphaciv.world.movement;

public class StepperImpl implements Stepper {

	private Position from;
	private Position to;

	@Override
	public boolean isArrived(Position from, Position to) {
		return from.equals(to);
	}

	@Override
	public Position makeStep(Position from, Position to) {
		this.from = from;
		this.to = to;

		if (directionIsUp())
			return moveUp();
		else if (directionIsRight())
			return moveRight();
		else if (directionIsDown())
			return moveDown();
		else if (directionIsLeft())
			return moveLeft();
		else if (directionIsUpRight())
			return moveUpRight();
		else if (directionIsDownRight())
			return moveDownRight();
		else if (directionIsDownLeft())
			return moveDownLeft();
		else if (directionIsUpLeft())
			return moveUpLeft();

		return from;
	}

	private boolean directionIsUp() {
		return from.r > to.r && from.c == to.c;
	}

	private boolean directionIsRight() {
		return from.c < to.c && from.r == to.r;
	}

	private boolean directionIsDown() {
		return to.r > from.r && from.c == to.c;
	}

	private boolean directionIsLeft() {
		return to.c < from.c && from.r == to.r;
	}

	private boolean directionIsUpRight() {
		return from.r > to.r && from.c < to.c;
	}

	private boolean directionIsDownRight() {
		return from.r < to.r && from.c < to.c;
	}

	private boolean directionIsDownLeft() {
		return from.r < to.r && from.c > to.c;
	}

	private boolean directionIsUpLeft() {
		return from.r > to.r && from.c > to.c;
	}

	private Position moveUp() {
		return new Position(from.r - 1, from.c);
	}

	private Position moveRight() {
		return new Position(from.r, from.c + 1);
	}

	private Position moveDown() {
		return new Position(from.r + 1, from.c);
	}

	private Position moveLeft() {
		return new Position(from.r, from.c - 1);
	}

	private Position moveUpRight() {
		return new Position(from.r - 1, from.c + 1);
	}

	private Position moveDownRight() {
		return new Position(from.r + 1, from.c + 1);
	}

	private Position moveDownLeft() {
		return new Position(from.r + 1, from.c - 1);
	}

	private Position moveUpLeft() {
		return new Position(from.r - 1, from.c - 1);
	}

}
