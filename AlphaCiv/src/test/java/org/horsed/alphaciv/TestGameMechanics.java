package org.horsed.alphaciv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.horsed.alphaciv.world.movement.Position;
import org.horsed.alphaciv.world.unit.Unit;
import org.junit.Before;
import org.junit.Test;

public class TestGameMechanics {

	private Game game;
	private Position positionOfRedArcher;
	private Position positionOfRedCity = new Position(1, 1);

	@Before
	public void setUp() {
		positionOfRedArcher = new Position(2, 0);
		game = new GameImpl();
	}

	@Test
	public void gameStartsAt_4000_BC() {
		assertEquals(-4000, game.getAge());
	}

	@Test
	public void ageIncrements_100_yearsAfterRound() {
		int age = game.getAge();
		advance_1_round();
		int ageAfterRound = game.getAge();
		assertEquals(age + 100, ageAfterRound);
	}

	@Test
	public void redShouldWinInTheYear_3000_BC() {
		advance_10_rounds();
		assertEquals(-3000, game.getAge());
		assertEquals(Player.RED, game.getWinner());
	}

	@Test
	public void redShouldBeFirstPlayerInTurn() {
		Player player = game.getPlayerInTurn();
		assertEquals(Player.RED, player);
	}

	@Test
	public void blueShouldBeInTurnAfterRed() {
		assertEquals(Player.RED, game.getPlayerInTurn());
		game.endOfTurn();
		assertEquals(Player.BLUE, game.getPlayerInTurn());
	}

	@Test
	public void redShouldBeInTurnAfterBlue() {
		assertEquals(Player.RED, game.getPlayerInTurn());
		game.endOfTurn();
		assertEquals(Player.BLUE, game.getPlayerInTurn());
		game.endOfTurn();
		assertEquals(Player.RED, game.getPlayerInTurn());
	}

	@Test
	public void citiesPopulationStaysAt_1_afterEndOfTurn() {
		int populationBeforeEndOfTurn = game.getCityAt(positionOfRedCity).getSize();
		assertEquals(1, populationBeforeEndOfTurn);

		game.endOfTurn();

		int populationAfterRound = game.getCityAt(positionOfRedCity).getSize();
		assertEquals(populationBeforeEndOfTurn, populationAfterRound);
	}

	@Test
	public void citiesGain_6_ProductionAfterEndOfTurn() {
		int productionAmountBeforeEndOfTurn = game.getCityAt(positionOfRedCity).getProductionAmount();

		advance_1_round();

		int productionAmountAfterRount = game.getCityAt(positionOfRedCity).getProductionAmount();
		assertEquals(productionAmountBeforeEndOfTurn + 6, productionAmountAfterRount);
	}

	@Test
	public void endOfTurnShouldResetMoveCounts() {
		Unit redUnit = game.getUnitAt(positionOfRedArcher);
		int moveCountBeforMovement = redUnit.getMoveCount();

		game.moveUnit(positionOfRedArcher, new Position(2, 1));
		advance_1_round();

		int moveCountAfterEndOfTurn = redUnit.getMoveCount();
		assertEquals(moveCountBeforMovement, moveCountAfterEndOfTurn);
	}

	@Test
	public void gameCanChangeProductionInCities() {
		game.changeProductionInCityAt(positionOfRedCity, GameConstants.ARCHER);
		assertEquals(GameConstants.ARCHER, game.getCityAt(positionOfRedCity).getProduction());
	}

	@Test
	public void cityProducesArcherAfter_2_rounds() {
		game.changeProductionInCityAt(positionOfRedCity, GameConstants.ARCHER);
		advance_1_round();
		advance_1_round();

		assertNotNull(game.getUnitAt(positionOfRedCity));
		assertEquals(Player.RED, game.getUnitAt(positionOfRedCity).getOwner());
		assertEquals(GameConstants.ARCHER, game.getUnitAt(positionOfRedCity).getTypeString());
	}

	@Test
	public void cityProducesLegionAfter_3_rounds() {
		game.changeProductionInCityAt(positionOfRedCity, GameConstants.LEGION);
		advance_1_round();
		advance_1_round();
		advance_1_round();

		assertNotNull(game.getUnitAt(positionOfRedCity));
		assertEquals(Player.RED, game.getUnitAt(positionOfRedCity).getOwner());
		assertEquals(GameConstants.LEGION, game.getUnitAt(positionOfRedCity).getTypeString());
	}

	@Test
	public void cityProducesSettlerAfter_5_rounds() {
		game.changeProductionInCityAt(positionOfRedCity, GameConstants.SETTLER);
		advance_1_round();
		advance_1_round();
		advance_1_round();
		advance_1_round();
		advance_1_round();

		assertNotNull(game.getUnitAt(positionOfRedCity));
		assertEquals(Player.RED, game.getUnitAt(positionOfRedCity).getOwner());
		assertEquals(GameConstants.SETTLER, game.getUnitAt(positionOfRedCity).getTypeString());
	}

	private void advance_10_rounds() {
		advance_1_round();
		advance_1_round();
		advance_1_round();
		advance_1_round();
		advance_1_round();
		advance_1_round();
		advance_1_round();
		advance_1_round();
		advance_1_round();
		advance_1_round();
	}

	private void advance_1_round() {
		game.endOfTurn();
		game.endOfTurn();
	}
}
