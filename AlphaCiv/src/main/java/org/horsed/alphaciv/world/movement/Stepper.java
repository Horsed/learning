package org.horsed.alphaciv.world.movement;

public interface Stepper {

	/**
	 * Answers whether {@link from} and {@link to} are equal.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public boolean isArrived(Position from, Position to);

	/**
	 * Anwers the {@link Position} of the tile next to the tile at {@link from} in the direction of {@link to}.
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public Position makeStep(Position from, Position to);
}
