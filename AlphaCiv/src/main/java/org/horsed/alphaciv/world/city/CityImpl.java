package org.horsed.alphaciv.world.city;

import org.horsed.alphaciv.Player;

public class CityImpl implements City {

	private final Player owner;
	private int productionAmount;
	private String productionType;

	public CityImpl(Player owner) {
		this.owner = owner;
	}

	public Player getOwner() {
		return this.owner;
	}

	public int getSize() {
		return 1;
	}

	public String getProduction() {
		return this.productionType;
	}

	public void setProduction(String productionType) {
		this.productionType = productionType;
	}

	public String getWorkforceFocus() {
		return null;
	}

	@Override
	public void incrementProductionAmount(int value) {
		this.productionAmount += value;
	}

	@Override
	public void decrementProductionAmount(int value) {
		this.productionAmount -= value;
	}

	@Override
	public int getProductionAmount() {
		return this.productionAmount;
	}

}
