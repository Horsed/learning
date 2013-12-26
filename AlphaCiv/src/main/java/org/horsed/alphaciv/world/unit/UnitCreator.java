package org.horsed.alphaciv.world.unit;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.horsed.alphaciv.Player;
import org.horsed.alphaciv.world.movement.Position;

public class UnitCreator {

	private final static Map<String, UnitFactory> unitsMapping;
	static {
		unitsMapping = new HashMap<String, UnitFactory>();
		unitsMapping.put("ra", new ArcherFactory(Player.RED));
		unitsMapping.put("ga", new ArcherFactory(Player.GREEN));
		unitsMapping.put("ba", new ArcherFactory(Player.BLUE));
		unitsMapping.put("ya", new ArcherFactory(Player.YELLOW));
		unitsMapping.put("rs", new SettlerFactory(Player.RED));
		unitsMapping.put("gs", new SettlerFactory(Player.GREEN));
		unitsMapping.put("bs", new SettlerFactory(Player.BLUE));
		unitsMapping.put("ys", new SettlerFactory(Player.YELLOW));
		unitsMapping.put("rl", new LegionFactory(Player.RED));
		unitsMapping.put("gl", new LegionFactory(Player.GREEN));
		unitsMapping.put("bl", new LegionFactory(Player.BLUE));
		unitsMapping.put("yl", new LegionFactory(Player.YELLOW));
	}

	public static Map<Position, Unit> createUnitMap(String... rows) {
		Map<Position, Unit> cities = new HashMap<Position, Unit>();

		for (int row = 0; row < rows.length; row++) {
			String rowString = rows[row].trim();
			Pattern p = Pattern.compile("(ra|ga|ba|ya|rs|gs|bs|ys|rl|gl|bl|yl|[^ra\\s])");
			Matcher matcher = p.matcher(rowString);
			int column = 0;
			while (matcher.find()) {
				String group = matcher.group();
				UnitFactory unitFactory = unitsMapping.get(group);
				if (unitFactory != null)
					createUnitAt(cities, new Position(row, column), unitFactory);
				column += 1;
			}
		}

		return cities;
	}

	private static void createUnitAt(Map<Position, Unit> units, Position position, UnitFactory unitFactory) {
		units.put(position, unitFactory.createUnit());
	}

}
