package org.horsed.alphaciv.world.tile;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.horsed.alphaciv.GameConstants;
import org.horsed.alphaciv.world.movement.Position;
import org.horsed.alphaciv.world.tile.TerrainCreator;
import org.junit.Test;

public class TestTerrainCreator {

	private final String[] worlds = new String[] {
	/**/"m m m m m m m m m m",//
	    "o p p p p p p p p m ",//
	    "m p p p p p p p p m ",//
	    "m p p p p p p p p m ",//
	    "m p p p h h p p p m ",//
	    "m p p p h h p p p m ",//
	    "m p p p p p p p p m ",//
	    "m p p p p p p f f m ",//
	    "m p p p p p p f f m ",//
	    "m m m m m m m m m m" };

	@Test
	public void createsMountainAt_0_0() {
		assertThat(tileTypeAt(0, 0), is(GameConstants.MOUNTAINS));
	}

	@Test
	public void createsOceanAt_1_0() {
		assertThat(tileTypeAt(1, 0), is(GameConstants.OCEANS));
	}

	@Test
	public void createsPlainAt_1_1() {
		assertThat(tileTypeAt(1, 1), is(GameConstants.PLAINS));
	}

	@Test
	public void createsForestAt_8_8() {
		assertThat(tileTypeAt(8, 8), is(GameConstants.FOREST));
	}

	@Test
	public void createsHillAt_4_4() {
		assertThat(tileTypeAt(4, 4), is(GameConstants.HILLS));
	}

	private String tileTypeAt(int row, int column) {
		return TerrainCreator.createTerrain(worlds).get(new Position(row, column)).getTypeString();
	}
}
