package org.horsed.alphaciv.world.unit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.horsed.alphaciv.GameConstants;
import org.horsed.alphaciv.Player;
import org.horsed.alphaciv.world.movement.Position;
import org.horsed.alphaciv.world.unit.Unit;
import org.horsed.alphaciv.world.unit.UnitCreator;
import org.junit.Test;

public class TestUnitCreator {

	private final String[] world = new String[] {
	/**/".  .  .  .  .  .  .  .  .  .",//
	    ".  .  .  .  .  .  .  .  .  .",//
	    ".  ra .  .  .  .  .  .  yl .",//
	    ".  .  .  .  .  .  .  .  .  .",//
	    ".  .  .  .  .  .  .  .  .  .",//
	    ".  .  .  .  .  .  .  .  .  .",//
	    ".  .  .  .  .  .  .  .  .  .",//
	    ".  gs .  .  .  .  .  .  ba .",//
	    ".  .  .  .  .  .  .  .  .  .",//
	    ".  .  .  .  .  .  .  .  .  ." };

	@Test
	public void createsRedUnitAt_2_1() {
		assertNotNull(unitAt(2, 1));
		assertThat(unitAt(2, 1), belongsTo(Player.RED));
		assertThat(unitAt(2, 1), isA(GameConstants.ARCHER));
	}

	@Test
	public void createsGreenUnitAt_7_1() {
		assertNotNull(unitAt(7, 1));
		assertThat(unitAt(7, 1), belongsTo(Player.GREEN));
		assertThat(unitAt(7, 1), isA(GameConstants.SETTLER));
	}

	@Test
	public void createsYellowUnitAt_2_8() {
		assertNotNull(unitAt(2, 8));
		assertThat(unitAt(2, 8), belongsTo(Player.YELLOW));
		assertThat(unitAt(2, 8), isA(GameConstants.LEGION));
	}

	@Test
	public void createsBlueUnitAt_7_8() {
		assertNotNull(unitAt(7, 8));
		assertThat(unitAt(7, 8), belongsTo(Player.BLUE));
		assertThat(unitAt(7, 8), isA(GameConstants.ARCHER));
	}

	private Unit unitAt(int row, int column) {
		return UnitCreator.createUnitMap(world).get(new Position(row, column));
	}

	private Matcher<Unit> belongsTo(final Player player) {
		return new BaseMatcher<Unit>() {

			public boolean matches(Object unit) {
				return ((Unit) unit).getOwner().equals(player);
			}

			public void describeTo(Description description) {
			}

		};
	}

	private Matcher<Unit> isA(final String unitType) {
		return new BaseMatcher<Unit>() {

			public boolean matches(Object unit) {
				return ((Unit) unit).getTypeString().equals(unitType);
			}

			public void describeTo(Description description) {
			}

		};
	}
}
