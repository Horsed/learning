package org.horsed.alphaciv.world.tile;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.horsed.alphaciv.GameConstants;
import org.horsed.alphaciv.world.movement.Position;

public class TerrainCreator {

	private final static Map<String, String> tileTypeMapping;
	static {
		tileTypeMapping = new HashMap<String, String>();
		tileTypeMapping.put("m", GameConstants.MOUNTAINS);
		tileTypeMapping.put("o", GameConstants.OCEANS);
		tileTypeMapping.put("p", GameConstants.PLAINS);
		tileTypeMapping.put("f", GameConstants.FOREST);
		tileTypeMapping.put("h", GameConstants.HILLS);
	}

	public static Map<Position, Tile> createTerrain(String... rows) {
		Map<Position, Tile> tiles = new HashMap<Position, Tile>();

		for (int row = 0; row < rows.length; row++) {
			String rowString = rows[row].trim();
			Pattern p = Pattern.compile("(m|p|o|f|h)");
			Matcher matcher = p.matcher(rowString);
			int column = 0;
			while (matcher.find()) {
				createTileAt(tiles, new Position(row, column++), tileTypeMapping.get(matcher.group()));
			}
		}

		return tiles;
	}

	private static Tile createTileAt(Map<Position, Tile> tiles, Position position, String tileType) {
		return tiles.put(position, new TileImpl(position, tileType));
	}

}
