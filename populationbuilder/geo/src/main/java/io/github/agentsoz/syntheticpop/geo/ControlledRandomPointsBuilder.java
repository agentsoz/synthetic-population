package io.github.agentsoz.syntheticpop.geo;

import java.util.Random;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.shape.random.RandomPointsBuilder;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class ControlledRandomPointsBuilder extends RandomPointsBuilder {
	private Random random = null;

	public ControlledRandomPointsBuilder(Random random) {
		this.random = random;
	}

	@Override
	protected Coordinate createRandomCoord(Envelope env) {
		double x = env.getMinX() + env.getWidth() * this.random.nextDouble();
		double y = env.getMinY() + env.getHeight() * this.random.nextDouble();
		return createCoord(x, y);
	}
}
