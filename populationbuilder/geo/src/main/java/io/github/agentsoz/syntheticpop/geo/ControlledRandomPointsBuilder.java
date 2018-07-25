package io.github.agentsoz.syntheticpop.geo;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2018 by its authors. See AUTHORS file.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

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
