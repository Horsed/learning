package org.horsed.alphaciv.world.movement;

import org.horsed.alphaciv.world.WorldLayout;

public interface ObstacleChecker {

	/**
	 * Answers whether there is an obstacle (mountain, ocean) between the two given positions.
	 * 
	 * @param tiles
	 * @param from
	 * @param to
	 * @return
	 */
	public boolean isObstacleInTheWay(WorldLayout worldLayout, Position from, Position to);

}
