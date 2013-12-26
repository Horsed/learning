package org.horsed.alphaciv.world.movement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.horsed.alphaciv.Game;
import org.horsed.alphaciv.GameConstants;
import org.horsed.alphaciv.Player;
import org.horsed.alphaciv.world.GameFactory;
import org.horsed.alphaciv.world.unit.Unit;
import org.junit.Before;
import org.junit.Test;

public class TestMovement {

	private Game game;
	private Position positionOfRedArcher;
	private Position positionOfBlueLegion;

	@Before
	public void setUp() {
		positionOfRedArcher = new Position(1, 1);
		positionOfBlueLegion = new Position(2, 2);
	}

	@Test
	public void canMoveUnitUp() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"m p m",//
						"m p m",//
						"m m m"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		assertTrue(game.moveUnit(positionOfRedArcher, new Position(0, 1)));
	}

	@Test
	public void canMoveUnitRight() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"m m m",//
						"m p p",//
						"m m m"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});

		assertTrue(game.moveUnit(positionOfRedArcher, new Position(1, 2)));
	}

	@Test
	public void canMoveUnitDown() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"m m m",//
						"m p m",//
						"m p m"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		assertTrue(game.moveUnit(positionOfRedArcher, new Position(2, 1)));
	}

	@Test
	public void canMoveUnitLeft() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"m m m",//
						"p p m",//
						"m m m"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});

		assertTrue(game.moveUnit(positionOfRedArcher, new Position(1, 0)));
	}

	@Test
	public void canMoveUnitUpRight() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"m m p",//
						"m p m",//
						"m m m"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		assertTrue(game.moveUnit(positionOfRedArcher, new Position(0, 2)));
	}

	@Test
	public void canMoveUnitDownRight() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"m m m",//
						"m p m",//
						"m m p"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		assertTrue(game.moveUnit(positionOfRedArcher, new Position(2, 2)));
	}

	@Test
	public void canMoveUnitDownLeft() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"m m m",//
						"m p m",//
						"p m m"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		assertTrue(game.moveUnit(positionOfRedArcher, new Position(2, 0)));
	}

	@Test
	public void canMoveUnitUpLeft() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"p m m",//
						"m p m",//
						"m m m"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		assertTrue(game.moveUnit(positionOfRedArcher, new Position(0, 0)));
	}

	@Test
	public void cannotMoveUnitUpOverMountain() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"m m m",//
						"m p m",//
						"m m m"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		assertFalse(game.moveUnit(positionOfRedArcher, new Position(0, 1)));
	}

	@Test
	public void cannotMoveUnitRightOverMountain() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"m m m",//
						"m p m",//
						"m m m"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		assertFalse(game.moveUnit(positionOfRedArcher, new Position(1, 2)));
	}

	@Test
	public void cannotMoveUnitDownOverMountain() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"m m m",//
						"m p m",//
						"m m m"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		assertFalse(game.moveUnit(positionOfRedArcher, new Position(2, 1)));
	}

	@Test
	public void cannotMoveUnitLeftOverMountain() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"m m m",//
						"m p m",//
						"m m m"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		assertFalse(game.moveUnit(positionOfRedArcher, new Position(1, 0)));
	}

	@Test
	public void cannotMoveUnitUpRightOverMountain() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"m m m",//
						"m p m",//
						"m m m"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		assertFalse(game.moveUnit(positionOfRedArcher, new Position(0, 2)));
	}

	@Test
	public void cannotMoveUnitDownRightOverMountain() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"m m m",//
						"m p m",//
						"m m m"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		assertFalse(game.moveUnit(positionOfRedArcher, new Position(2, 2)));
	}

	@Test
	public void cannotMoveUnitDownLeftOverMountain() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"m m m",//
						"m p m",//
						"m m m"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		assertFalse(game.moveUnit(positionOfRedArcher, new Position(2, 0)));
	}

	@Test
	public void cannotMoveUnitUpLeftOverMountain() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"m m m",//
						"m p m",//
						"m m m"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		assertFalse(game.moveUnit(positionOfRedArcher, new Position(0, 0)));
	}

	@Test
	public void redCannotMoveBlueUnit() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"p p p",//
						"p p p",//
						"p p p"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  . ",//
						".  ra . ",//
						".  .  ba"});

		assertEquals(Player.RED, game.getPlayerInTurn());
		assertFalse(game.moveUnit(positionOfBlueLegion, new Position(1, 2)));
	}

	@Test
	public void movingUnitShouldDecreaseMoveCount() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"p p p",//
						"p p p",//
						"p p p"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});

		Unit redArcher = game.getUnitAt(positionOfRedArcher);
		int moveCountBeforeMovement = redArcher.getMoveCount();

		game.moveUnit(positionOfRedArcher, new Position(2, 1));

		int moveCountAfterMovement = redArcher.getMoveCount();
		assertEquals(moveCountBeforeMovement - 1, moveCountAfterMovement);
	}

	@Test
	public void unitCanMoveOnlyOneTile() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"p p p",//
						"p p p",//
						"p p p"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});

		game.moveUnit(positionOfRedArcher, new Position(2, 1));

		assertFalse(game.moveUnit(new Position(2, 1), positionOfRedArcher));
	}

	@Test
	public void redsUnitAttacksBluesUnit() {
		moveRedUnitToPositionOfBlueUnit();
		assertEquals(Player.RED, game.getUnitAt(positionOfBlueLegion).getOwner());
		assertEquals(GameConstants.ARCHER, game.getUnitAt(positionOfBlueLegion).getTypeString());
	}

	@Test
	public void movingOnTheSameTileShouldNotDecreaseMovementCount() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"p p p",//
						"p p p",//
						"p p p"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  .",//
						".  ra .",//
						".  .  ."});
		Unit redArcher = game.getUnitAt(positionOfRedArcher);
		int moveCountBeforMove = redArcher.getMoveCount();

		game.moveUnit(positionOfRedArcher, positionOfRedArcher);

		int moveCountAfterMove = redArcher.getMoveCount();
		assertEquals(moveCountBeforMove, moveCountAfterMove);
	}

	private void moveRedUnitToPositionOfBlueUnit() {
		game = GameFactory.gameWithWorldLayout(//
				new String[] {//
				/**/"p p p",//
						"p p p",//
						"p p p"},//
				new String[] {//
				/**/". . .",//
						". . .",//
						". . ."},//
				new String[] {//
				/**/".  .  . ",//
						".  ra . ",//
						".  .  ba"});

		assertTrue(game.moveUnit(positionOfRedArcher, positionOfBlueLegion));
	}
}
