package org.horsed.alphaciv.world.unit;

import org.horsed.alphaciv.Player;

public class ArcherFactory implements UnitFactory {

	private final Player owner;

	public ArcherFactory(Player owner) {
		this.owner = owner;
	}

	public Unit createUnit() {
		return new Archer(this.owner);
	}
}