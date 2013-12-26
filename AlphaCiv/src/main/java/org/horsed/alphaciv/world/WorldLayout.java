package org.horsed.alphaciv.world;

import java.util.List;
import java.util.Map;

import org.horsed.alphaciv.world.city.City;
import org.horsed.alphaciv.world.movement.Position;
import org.horsed.alphaciv.world.tile.Tile;
import org.horsed.alphaciv.world.unit.Unit;

public interface WorldLayout {

	public Map<Position, Tile> tiles();

	public Map<Position, City> cities();

	public Map<Position, Unit> units();

	public Tile getTileAt(Position p);

	public Unit getUnitAt(Position p);

	public City getCityAt(Position p);

	public Position getPositionOfCity(City city);

	List<City> getAllCities();

	List<Unit> getAllUnits();

	/**
	 * Adds the given unit add at the given position.
	 * 
	 * @param unit
	 *          new unit to be added
	 * @param to
	 *          position of the new unit
	 */
	public void addUnit(Unit unit, Position to);

	/**
	 * Removes the given unit from its current position and places it at {@link to}.
	 * 
	 * @param unit
	 *          unit to be moved
	 * @param to
	 *          desired position of the unit
	 */
	public void repositionUnit(Unit unit, Position to);

	/**
	 * Removes the unit at {@link from} and places it at {@link to}.
	 * 
	 * @param from
	 *          current position of the unit
	 * @param to
	 *          desired position of the unit
	 */
	public void repositionUnit(Position from, Position to);

}
