package org.horsed.alphaciv.world;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.horsed.alphaciv.Game;
import org.horsed.alphaciv.GameConstants;
import org.horsed.alphaciv.GameImpl;
import org.horsed.alphaciv.Player;
import org.horsed.alphaciv.world.city.City;
import org.horsed.alphaciv.world.movement.Position;
import org.horsed.alphaciv.world.tile.Tile;
import org.horsed.alphaciv.world.unit.Unit;
import org.junit.Before;
import org.junit.Test;

public class TestStandardWorldLayout {

	private Game game;

	@Before
	public void setUp() throws Exception {
		game = new GameImpl();
	}

	@Test
	public void gameCanBeInitializedWithCustomWorldLayout() {
		WorldLayout worldLayout = new StandardWorldLayout();

		game = new GameImpl(worldLayout);

		assertEquals(GameConstants.PLAINS, game.getTileAt(new Position(1, 1)).getTypeString());
	}

	@Test
	public void shouldHaveHillAt_0_1() {
		assertEquals(GameConstants.HILLS, game.getTileAt(new Position(0, 1)).getTypeString());
	}

	@Test
	public void shouldHaveMountainAt_2_2() {
		assertEquals(GameConstants.MOUNTAINS, game.getTileAt(new Position(2, 2)).getTypeString());
	}

	@Test
	public void shouldHaveOceanAt_1_0() {
		Tile tile = game.getTileAt(new Position(1, 0));
		assertEquals(GameConstants.OCEANS, tile.getTypeString());
	}

	@Test
	public void shouldHaveRedCityAt_1_1() {
		City city = game.getCityAt(new Position(1, 1));
		assertNotNull(city);
		Player player = city.getOwner();
		assertEquals(Player.RED, player);
	}

	@Test
	public void shouldHaveBlueCityAt_4_1() {
		City city = game.getCityAt(new Position(4, 1));
		assertNotNull(city);
		Player player = city.getOwner();
		assertEquals(Player.BLUE, player);
	}

	@Test
	public void shouldHaveRedArcherAt_2_0() {
		Unit unit = game.getUnitAt(new Position(2, 0));
		assertNotNull(unit);
		assertEquals(GameConstants.ARCHER, unit.getTypeString());
		Player owner = unit.getOwner();
		assertEquals(Player.RED, owner);
	}

	@Test
	public void shouldHaveRedSettlerAt_4_3() {
		Unit unit = game.getUnitAt(new Position(4, 3));
		assertNotNull(unit);
		assertEquals(GameConstants.SETTLER, unit.getTypeString());
		Player owner = unit.getOwner();
		assertEquals(Player.RED, owner);
	}

	@Test
	public void shouldHaveBlueLegionAt_3_2() {
		Unit unit = game.getUnitAt(new Position(3, 2));
		assertNotNull(unit);
		assertEquals(GameConstants.LEGION, unit.getTypeString());
		Player owner = unit.getOwner();
		assertEquals(Player.BLUE, owner);
	}

	@Test
	public void producingUnitAddsTheUnitToTheCitysPosition() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"p p p",//
						"p p p",//
						"p p p"},//
				new String[] {//
				/**/". . .",//
						". r .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  .  .",//
						".  .  ."});
		game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
		advance_2_rounds();

		assertNotNull(game.getUnitAt(new Position(1, 1)));
	}

	@Test
	public void producingUnitAddsTheUnitAboveTheCitysPosition() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"p p p",//
						"p p p",//
						"p p p"},//
				new String[] {//
				/**/". . .",//
						". r .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
		advance_2_rounds();

		assertNotNull(game.getUnitAt(new Position(0, 1)));
	}

	@Test
	public void producingUnitAddsTheUnitTopRightFromTheCitysPosition() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"p m p",//
						"p p p",//
						"p p p"},//
				new String[] {//
				/**/". . .",//
						". r .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
		advance_2_rounds();

		assertNotNull(game.getUnitAt(new Position(0, 2)));
	}

	@Test
	public void producingUnitAddsTheUnitToTheRightOfTheCitysPosition() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"p m m",//
						"p p p",//
						"p p p"},//
				new String[] {//
				/**/". . .",//
						". r .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
		advance_2_rounds();

		assertNotNull(game.getUnitAt(new Position(1, 2)));
	}

	@Test
	public void producingUnitAddsTheUnitDownRightFromTheCitysPosition() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"p m m",//
						"p p m",//
						"p p p"},//
				new String[] {//
				/**/". . .",//
						". r .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
		advance_2_rounds();

		assertNotNull(game.getUnitAt(new Position(2, 2)));
	}

	@Test
	public void producingUnitAddsTheUnitUnderTheCitysPosition() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"p m m",//
						"p p m",//
						"p p m"},//
				new String[] {//
				/**/". . .",//
						". r .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
		advance_2_rounds();

		assertNotNull(game.getUnitAt(new Position(2, 1)));
	}

	@Test
	public void producingUnitAddsTheUnitDownLeftFromTheCitysPosition() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"p m m",//
						"p p m",//
						"p m m"},//
				new String[] {//
				/**/". . .",//
						". r .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
		advance_2_rounds();

		assertNotNull(game.getUnitAt(new Position(2, 0)));
	}

	@Test
	public void producingUnitAddsTheUnitToTheLeftOfTheCitysPosition() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"p m m",//
						"p p m",//
						"m m m"},//
				new String[] {//
				/**/". . .",//
						". r .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
		advance_2_rounds();

		assertNotNull(game.getUnitAt(new Position(1, 0)));
	}

	@Test
	public void producingUnitAddsTheUnitTopLeftFromTheCitysPosition() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"p m m",//
						"m p m",//
						"m m m"},//
				new String[] {//
				/**/". . .",//
						". r .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		game.changeProductionInCityAt(new Position(1, 1), GameConstants.ARCHER);
		advance_2_rounds();

		assertNotNull(game.getUnitAt(new Position(0, 0)));
	}

	private void advance_2_rounds() {
		game.endOfTurn();
		game.endOfTurn();
		game.endOfTurn();
		game.endOfTurn();
	}
}
