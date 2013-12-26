package org.horsed.alphaciv.world;

import org.horsed.alphaciv.Game;
import org.horsed.alphaciv.GameImpl;
import org.horsed.alphaciv.world.city.CityCreator;
import org.horsed.alphaciv.world.tile.TerrainCreator;
import org.horsed.alphaciv.world.unit.UnitCreator;

public class GameFactory {

	public static Game gameWithWorldLayout(String[] tiles, String[] cities, String[] units) {
		WorldLayout worldLayout = new StandardWorldLayout(TerrainCreator.createTerrain(tiles),
				CityCreator.createCityMap(cities), UnitCreator.createUnitMap(units));
		return new GameImpl(worldLayout);
	}
}
