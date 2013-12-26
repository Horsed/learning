package org.horsed.alphaciv.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.horsed.alphaciv.world.city.City;
import org.horsed.alphaciv.world.city.CityCreator;
import org.horsed.alphaciv.world.movement.Position;
import org.horsed.alphaciv.world.tile.TerrainCreator;
import org.horsed.alphaciv.world.tile.Tile;
import org.horsed.alphaciv.world.unit.Unit;
import org.horsed.alphaciv.world.unit.UnitCreator;

public class StandardWorldLayout implements WorldLayout {

	private Map<Position, Tile> tiles = TerrainCreator.createTerrain(
	/**/"p h p p p p p p p p p p p p p p",//
			"o p p p p p p p p p p p p p p p",//
			"p p m p p p p p p p p p p p p p",//
			"p p p p p p p p p p p p p p p p",//
			"p p p p p p p p p p p p p p p p",//
			"p p p p p p p p p p p p p p p p",//
			"p p p p p p p p p p p p p p p p",//
			"p p p p p p p p p p p p p p p p",//
			"p p p p p p p p p p p p p p p p",//
			"p p p p p p p p p p p p p p p p",//
			"p p p p p p p p p p p p p p p p",//
			"p p p p p p p p p p p p p p p p",//
			"p p p p p p p p p p p p p p p p",//
			"p p p p p p p p p p p p p p p p",//
			"p p p p p p p p p p p p p p p p",//
			"p p p p p p p p p p p p p p p p");

	private Map<Position, City> cities = CityCreator.createCityMap(
	/**/". . . . . . . . . . . . . . . .",//
			". r . . . . . . . . . . . . . .",//
			". . . . . . . . . . . . . . . .",//
			". . . . . . . . . . . . . . . .",//
			". b . . . . . . . . . . . . . .",//
			". . . . . . . . . . . . . . . .",//
			". . . . . . . . . . . . . . . .",//
			". . . . . . . . . . . . . . . .",//
			". . . . . . . . . . . . . . . .",//
			". . . . . . . . . . . . . . . .",//
			". . . . . . . . . . . . . . . .",//
			". . . . . . . . . . . . . . . .",//
			". . . . . . . . . . . . . . . .",//
			". . . . . . . . . . . . . . . .",//
			". . . . . . . . . . . . . . . .",//
			". . . . . . . . . . . . . . . .");

	private Map<Position, Unit> units = UnitCreator.createUnitMap(
	/**/".  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .",//
			".  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .",//
			"ra .  .  .  .  .  .  .  .  .  .  .  .  .  .  .",//
			".  .  bl .  .  .  .  .  .  .  .  .  .  .  .  .",//
			".  .  .  rs .  .  .  .  .  .  .  .  .  .  .  .",//
			".  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .",//
			".  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .",//
			".  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .",//
			".  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .",//
			".  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .",//
			".  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .",//
			".  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .",//
			".  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .",//
			".  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .",//
			".  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .",//
			".  .  .  .  .  .  .  .  .  .  .  .  .  .  .  .");

	public StandardWorldLayout() {
	}

	public StandardWorldLayout(Map<Position, Tile> tiles, Map<Position, City> cities, Map<Position, Unit> units) {
		this.tiles = tiles;
		this.cities = cities;
		this.units = units;
	}

	@Override
	public Map<Position, Tile> tiles() {
		return tiles;
	}

	@Override
	public Map<Position, City> cities() {
		return cities;
	}

	@Override
	public Map<Position, Unit> units() {
		return units;
	}

	@Override
	public Tile getTileAt(Position p) {
		return tiles.get(p);
	}

	@Override
	public Unit getUnitAt(Position p) {
		return units.get(p);
	}

	@Override
	public City getCityAt(Position p) {
		return cities.get(p);
	}

	@Override
	public Position getPositionOfCity(City city) {
		Position originalPosition = null;
		for (Entry<Position, City> e : this.cities.entrySet())
			if (e.getValue().equals(city))
				originalPosition = e.getKey();
		return originalPosition;
	}

	@Override
	public List<City> getAllCities() {
		return new ArrayList<City>(cities.values());
	}

	@Override
	public List<Unit> getAllUnits() {
		return new ArrayList<Unit>(units.values());
	}

	@Override
	public void addUnit(Unit unit, Position to) {
		if (canPlaceUnitAt(to))
			units.put(to, unit);
		else if (canPlaceUnitAt(to.above()))
			units.put(to.above(), unit);
		else if (canPlaceUnitAt(to.topRight()))
			units.put(to.topRight(), unit);
		else if (canPlaceUnitAt(to.right()))
			units.put(to.right(), unit);
		else if (canPlaceUnitAt(to.downRight()))
			units.put(to.downRight(), unit);
		else if (canPlaceUnitAt(to.under()))
			units.put(to.under(), unit);
		else if (canPlaceUnitAt(to.downLeft()))
			units.put(to.downLeft(), unit);
		else if (canPlaceUnitAt(to.left()))
			units.put(to.left(), unit);
		else if (canPlaceUnitAt(to.topLeft()))
			units.put(to.topLeft(), unit);
	}

	private boolean canPlaceUnitAt(Position position) {
		return getUnitAt(position) == null && !getTileAt(position).isObstacle();
	}

	@Override
	public void repositionUnit(Unit unit, Position to) {
		this.repositionUnit(getPositionOfUnit(unit), to);
	}

	@Override
	public void repositionUnit(Position from, Position to) {
		this.units.put(to, getUnitAt(from));
		this.units.remove(from);
	}

	private Position getPositionOfUnit(Unit unit) {
		Position originalPosition = null;
		for (Entry<Position, Unit> e : this.units.entrySet())
			if (e.getValue().equals(unit))
				originalPosition = e.getKey();
		return originalPosition;
	}
}
