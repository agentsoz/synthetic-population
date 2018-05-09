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

import org.geotools.feature.FeatureCollection;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public abstract class GeoFeatureReader {
	FeatureCollection featureCollection = null;

	public boolean anyFeatures() {
		if (featureCollection == null) {
			return false;
		}
		return true;
	}

	public FeatureCollection getFeatures() {
		if (this.featureCollection == null) {
			throw new NullPointerException(
					"Geographical features are not loaded. Call loadFeatures() method before calling getPoints()");
		}
		return this.featureCollection;
	}

	abstract public void loadFeatures(Path input) throws IOException;

	abstract public void loadFeaturesByProperty(Path target, String property, String[] propertyValues)
			throws IOException;

}
