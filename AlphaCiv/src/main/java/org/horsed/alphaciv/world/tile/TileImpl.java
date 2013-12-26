package org.horsed.alphaciv.world.tile;

import org.horsed.alphaciv.GameConstants;
import org.horsed.alphaciv.world.movement.Position;

public class TileImpl implements Tile {

	private final String tileType;
	private final Position position;

	public TileImpl(Position position, String tileType) {
		this.position = position;
		this.tileType = tileType;
	}

	@Override
	public Position getPosition() {
		return this.position;
	}

	@Override
	public String getTypeString() {
		return this.tileType;
	}

	@Override
	public boolean isObstacle() {
		return tileType.equals(GameConstants.MOUNTAINS) || tileType.equals(GameConstants.OCEANS);
	}

}
