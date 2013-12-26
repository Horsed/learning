package org.horsed.alphaciv.world.movement;

import org.horsed.alphaciv.world.WorldLayout;

public class ObstacleCheckerImpl implements ObstacleChecker {

	private final Stepper stepper;

	public ObstacleCheckerImpl() {
		stepper = new StepperImpl();
	}

	public ObstacleCheckerImpl(Stepper stepper) {
		this.stepper = stepper;
	}

	@Override
	public boolean isObstacleInTheWay(WorldLayout worldLayout, Position from, Position to) {
		while (!stepper.isArrived(from, to)) {
			from = stepper.makeStep(from, to);
			if (tileIsObstacle(worldLayout, from))
				return true;
		}
		return false;
	}

	private boolean tileIsObstacle(WorldLayout worldLayout, Position tilePosition) {
		return worldLayout.getTileAt(tilePosition).isObstacle();
	}

}
