package org.horsed.alphaciv;

import java.util.Arrays;
import java.util.List;

import org.horsed.alphaciv.world.StandardWorldLayout;
import org.horsed.alphaciv.world.WorldLayout;
import org.horsed.alphaciv.world.city.City;
import org.horsed.alphaciv.world.movement.ObstacleCheckerImpl;
import org.horsed.alphaciv.world.movement.Position;
import org.horsed.alphaciv.world.tile.Tile;
import org.horsed.alphaciv.world.unit.ArcherFactory;
import org.horsed.alphaciv.world.unit.LegionFactory;
import org.horsed.alphaciv.world.unit.SettlerFactory;
import org.horsed.alphaciv.world.unit.Unit;

public class GameImpl implements Game {

	private static final int AGING = 100;
	private static final int CITY_PRODUCTION_AMOUNT = 6;
	private final WorldLayout worldLayout;
	private final ObstacleCheckerImpl obstacleChecker;
	private final List<Player> players = Arrays.asList(Player.RED, Player.BLUE);
	private Player playerInTurn = Player.RED;
	private int age = -4000;

	public GameImpl() {
		worldLayout = new StandardWorldLayout();
		obstacleChecker = new ObstacleCheckerImpl();
	}

	public GameImpl(WorldLayout worldLayout) {
		this.worldLayout = worldLayout;
		this.obstacleChecker = new ObstacleCheckerImpl();
	}

	@Override
	public Tile getTileAt(Position p) {
		return worldLayout.getTileAt(p);
	}

	@Override
	public Unit getUnitAt(Position p) {
		return worldLayout.getUnitAt(p);
	}

	@Override
	public City getCityAt(Position p) {
		return worldLayout.getCityAt(p);
	}

	@Override
	public Player getPlayerInTurn() {
		return playerInTurn;
	}

	@Override
	public Player getWinner() {
		return getAge() == -3000
				? Player.RED
				: null;
	}

	@Override
	public int getAge() {
		return this.age;
	}

	@Override
	public boolean moveUnit(Position from, Position to) {
		if (!unitCanBeMoved(from, to))
			return false;

		worldLayout.repositionUnit(from, to);
		getUnitAt(to).decreaseMoveCount();
		return true;
	}

	private boolean unitCanBeMoved(Position from, Position to) {
		Unit unitAt = getUnitAt(from);
		return from != to && playerInTurnOwnsUnit(unitAt) && unitHasMoveCount(unitAt)
				&& !obstacleChecker.isObstacleInTheWay(worldLayout, from, to);
	}

	private boolean playerInTurnOwnsUnit(Unit unitAt) {
		Player owner = unitAt.getOwner();
		boolean playerInTurnOwnsUnit = getPlayerInTurn().equals(owner);
		return playerInTurnOwnsUnit;
	}

	private boolean unitHasMoveCount(Unit unitAt) {
		boolean unitHasMoveCount = unitAt.getMoveCount() > 0;
		return unitHasMoveCount;
	}

	@Override
	public void endOfTurn() {
		if (isEndOfTurn()) {
			incrementProductionAmountsOfAllCities();
			produceUnits();
			resetMoveCountOfAllUnits();
			incrementAge();
		}
		nextPlayer();
	}

	private boolean isEndOfTurn() {
		int indexOfPlayerInTurn = players.indexOf(this.playerInTurn);
		return indexOfPlayerInTurn == players.size() - 1;
	}

	private void incrementProductionAmountsOfAllCities() {
		for (City city : worldLayout.getAllCities())
			city.incrementProductionAmount(CITY_PRODUCTION_AMOUNT);
	}

	private void produceUnits() {
		for (City city : worldLayout.getAllCities()) {
			if (city.getProduction() != null) {
				if (city.getProduction().equals(GameConstants.ARCHER)) {
					produceArcher(city);
				} else if (city.getProduction().equals(GameConstants.LEGION)) {
					produceLegion(city);
				} else if (city.getProduction().equals(GameConstants.SETTLER)) {
					produceSettler(city);
				}
			}
		}
	}

	private void produceArcher(City city) {
		if (city.getProductionAmount() >= 10) {
			city.decrementProductionAmount(10);
			this.worldLayout.addUnit(new ArcherFactory(city.getOwner()).createUnit(),
					this.worldLayout.getPositionOfCity(city));
		}
	}

	private void produceLegion(City city) {
		if (city.getProductionAmount() >= 15) {
			city.decrementProductionAmount(15);
			this.worldLayout.addUnit(new LegionFactory(city.getOwner()).createUnit(),
					this.worldLayout.getPositionOfCity(city));
		}
	}

	private void produceSettler(City city) {
		if (city.getProductionAmount() >= 30) {
			city.decrementProductionAmount(30);
			this.worldLayout.addUnit(new SettlerFactory(city.getOwner()).createUnit(),
					this.worldLayout.getPositionOfCity(city));
		}
	}

	private void resetMoveCountOfAllUnits() {
		for (Unit unit : worldLayout.getAllUnits()) {
			unit.resetMoveCount();
		}
	}

	private void incrementAge() {
		this.age += AGING;
	}

	private void nextPlayer() {
		this.playerInTurn = players.get(indexOfNextPlayerInTurn());
	}

	private int indexOfNextPlayerInTurn() {
		int indexOfPlayerInTurn = players.indexOf(this.playerInTurn);
		return indexOfPlayerInTurn < players.size() - 1
				? indexOfPlayerInTurn + 1
				: 0;
	}

	@Override
	public void changeWorkForceFocusInCityAt(Position p, String balance) {
	}

	@Override
	public void changeProductionInCityAt(Position p, String unitType) {
		getCityAt(p).setProduction(unitType);
	}

	@Override
	public void performUnitActionAt(Position p) {
	}
}
