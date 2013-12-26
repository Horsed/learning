package org.horsed.alphaciv.world.unit;

import org.horsed.alphaciv.Player;

public class LegionFactory implements UnitFactory {

	private final Player owner;

	public LegionFactory(Player owner) {
		this.owner = owner;
	}

	public Unit createUnit() {
		return new Legion(this.owner);
	}
}