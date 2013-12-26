package org.horsed.alphaciv.world.unit;

import org.horsed.alphaciv.GameConstants;
import org.horsed.alphaciv.Player;

public class Legion implements Unit {

	private static final int DEFAULT_MOVE_COUNT = 1;
	private final Player owner;
	private int moveCount = DEFAULT_MOVE_COUNT;
	private final int defensiveStrength = 2;
	private final int attackingStrength = 3;

	public Legion(Player owner) {
		this.owner = owner;
	}

	public String getTypeString() {
		return GameConstants.LEGION;
	}

	public Player getOwner() {
		return owner;
	}

	public int getMoveCount() {
		return moveCount;
	}

	public int getDefensiveStrength() {
		return defensiveStrength;
	}

	public int getAttackingStrength() {
		return attackingStrength;
	}

	@Override
	public void decreaseMoveCount() {
		this.moveCount--;
	}

	@Override
	public void resetMoveCount() {
		this.moveCount = DEFAULT_MOVE_COUNT;
	}

}
