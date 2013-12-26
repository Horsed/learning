package org.horsed.alphaciv.world.unit;

import org.horsed.alphaciv.Player;

public class SettlerFactory implements UnitFactory {

	private final Player owner;

	public SettlerFactory(Player owner) {
		this.owner = owner;
	}

	public Unit createUnit() {
		return new Settler(this.owner);
	}
}