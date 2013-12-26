package org.horsed.alphaciv.world.city;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.horsed.alphaciv.Player;
import org.horsed.alphaciv.world.movement.Position;

public class CityCreator {

	private final static Map<String, Player> playersMapping;
	static {
		playersMapping = new HashMap<String, Player>();
		playersMapping.put("r", Player.RED);
		playersMapping.put("g", Player.GREEN);
		playersMapping.put("b", Player.BLUE);
		playersMapping.put("y", Player.YELLOW);
	}

	public static Map<Position, City> createCityMap(String... rows) {
		Map<Position, City> cities = new HashMap<Position, City>();

		for (int row = 0; row < rows.length; row++) {
			String rowString = rows[row].trim();
			Pattern p = Pattern.compile("(r|g|b|y|[^rgby\\s])");
			Matcher matcher = p.matcher(rowString);
			int column = 0;
			while (matcher.find()) {
				createCityAt(cities, new Position(row, column++), playersMapping.get(matcher.group()));
			}
		}

		return cities;
	}

	private static void createCityAt(Map<Position, City> cities, Position position, Player owner) {
		if (owner != null)
			cities.put(position, new CityImpl(owner));
	}

}
