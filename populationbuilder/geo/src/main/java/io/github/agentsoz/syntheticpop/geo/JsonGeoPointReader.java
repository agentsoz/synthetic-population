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

import io.github.agentsoz.syntheticpop.filemanager.json.JSONReader;
import org.geotools.feature.FeatureCollection;
import org.geotools.util.UnsupportedImplementationException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class JsonGeoPointReader extends GeoFeatureReader {
	private JSONReader jsonReader;

	public JsonGeoPointReader(JSONReader jsonReader) {
		this.jsonReader = jsonReader;

	}
	
	public void loadFeaturesByProperty(Path input, String property, String[] propertyValues){
		throw new UnsupportedImplementationException("Function not implemented");
	}

	public void loadFeatures(Path input) throws IOException {
		Map jsonMap = jsonReader.readJSON(input);
		List<Object> features = (List) jsonMap.get("features");;
		for (Object feature : features) {
			HashMap mFeature = (HashMap) feature;
			HashMap geom = (HashMap) mFeature.get("geometry");
			HashMap<String, Double> tmpCoords = new HashMap<String, Double>(2);
			tmpCoords.put("lat", (Double) ((ArrayList) geom.get("coordinates")).get(0));
			tmpCoords.put("lon", (Double) ((ArrayList) geom.get("coordinates")).get(1));

			mFeature.put("coordinates", tmpCoords);
			mFeature.remove("geometry");
		}
		featureCollection = (FeatureCollection) features; // This won't work
	}

}
