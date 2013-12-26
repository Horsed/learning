package org.horsed.alphaciv.world.city;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.horsed.alphaciv.Player;
import org.horsed.alphaciv.world.city.City;
import org.horsed.alphaciv.world.city.CityCreator;
import org.horsed.alphaciv.world.movement.Position;
import org.junit.Test;

public class TestCityCreator {

	private final String[] worlds = new String[] {
	/**/". . . . . . . . . .",//
	    ". r . . . . . . y .",//
	    ". . . . . . . . . .",//
	    ". . . . . . . . . .",//
	    ". . . . . . . . . .",//
	    ". . . . . . . . . .",//
	    ". . . . . . . . . .",//
	    ". . . . . . . . . .",//
	    ". g . . . . . . b .",//
	    ". . . . . . . . . ." };

	@Test
	public void createsRedCityAt_1_1() {
		assertNotNull(cityAt(1, 1));
		assertThat(cityAt(1, 1), belongsTo(Player.RED));
	}

	@Test
	public void createsGreenCityAt_8_1() {
		assertNotNull(cityAt(8, 1));
		assertThat(cityAt(8, 1), belongsTo(Player.GREEN));
	}

	@Test
	public void createsYellowCityAt_1_8() {
		assertNotNull(cityAt(1, 8));
		assertThat(cityAt(1, 8), belongsTo(Player.YELLOW));
	}

	@Test
	public void createsBlueCityAt_8_8() {
		assertNotNull(cityAt(8, 8));
		assertThat(cityAt(8, 8), belongsTo(Player.BLUE));
	}

	private City cityAt(int row, int column) {
		return CityCreator.createCityMap(worlds).get(new Position(row, column));
	}

	private Matcher<City> belongsTo(final Player player) {
		return new BaseMatcher<City>() {

			@Override
			public boolean matches(Object city) {
				return ((City) city).getOwner().equals(player);
			}

			@Override
			public void describeTo(Description description) {
			}

		};
	}
}
