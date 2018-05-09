package io.github.agentsoz.syntheticpop.BuildingProperties;

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

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import io.github.agentsoz.syntheticpop.geo.FeatureProcessor;
import io.github.agentsoz.syntheticpop.util.Log;
import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.util.*;
import java.util.zip.DataFormatException;

/**
 * @author Bhagya N. Wickramasinghe
 */
public class SA1Locater {

    private static LinkedHashSet<SimpleFeature> recentMatches = new LinkedHashSet<>(15);

    /**
     * Finds the SA1 each building belongs to based on geographical location
     *
     * @param allBuildings List of buildings
     * @param targetSA1s   List of SA1 matched against
     * @throws SchemaException
     */
    static void findSA1sOfBuildings(SimpleFeatureCollection allBuildings, SimpleFeatureCollection targetSA1s) throws SchemaException {

        Map<String, List<SimpleFeature>> buildingsBySA1 = new HashMap<>();
        SimpleFeature matchingSA1 = null;
        FeatureProcessor fp = new FeatureProcessor();
        try (SimpleFeatureIterator buildingsInterator = allBuildings.features()) {
            while (buildingsInterator.hasNext()) {
                SimpleFeature building = buildingsInterator.next();
                try {
                    if (!recentMatches.isEmpty()) {
                        matchingSA1 = (SimpleFeature) fp.getContainingPolygon(recentMatches, building);
                    }
                    if (matchingSA1 == null) {
                        matchingSA1 = (SimpleFeature) fp.getContainingPolygon(targetSA1s, building);
                    }

                    if (matchingSA1 != null) {
                        recentMatches.add(matchingSA1);
                        String sa1code = (String) matchingSA1.getAttribute("SA1_7DIG11");
                        if (buildingsBySA1.containsKey(sa1code))
                            buildingsBySA1.get(sa1code).add(building);
                        else
                            buildingsBySA1.put(sa1code, new ArrayList<>(Arrays.asList(building)));
                    }

                } catch (Exception e) {
                    Log.error("Failed when trying to read from buildings featureCollection", e);
                }

            }
        }
    }

    /**
     * Finds the SA1 the building belongs to based on geographical location
     *
     * @param building   the building
     * @param targetSA1s List of SA1 matched against
     * @return SA1_7DIG11 code of matching SA1
     * @throws SchemaException
     */
    static String findSA1ofBuilding(SimpleFeature building, SimpleFeatureCollection targetSA1s, FeatureProcessor fp) throws DataFormatException {

        SimpleFeature matchingSA1 = null;

        if (!recentMatches.isEmpty()) {
            matchingSA1 = fp.getContainingPolygon(recentMatches, building);
        }
        if (matchingSA1 == null) {
            matchingSA1 = fp.getContainingPolygon(targetSA1s, building);
        }

        if (matchingSA1 != null) {
            recentMatches.add(matchingSA1);
            return (String) matchingSA1.getAttribute("SA1_7DIG11");
        }
        return null;

    }

    /**
     * Creates a SimpleFeature using a Map object giving coordinates of a geographical point.
     * SimpleFeatureType is hard coded to type name:"Location" and typeSpec:"location:Point:srid=28355".
     *
     * @param coordinate Map with two entries for "easting" and "northing"
     * @return SimpleFeature representing the point
     * @throws SchemaException
     */
    static SimpleFeature makeFeature(Map<String, Double> coordinate) throws SchemaException {
        final SimpleFeatureType TYPE = DataUtilities.createType("Location", "location:Point:srid=28355"); // Point type

        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
        Point point = geometryFactory.createPoint(new Coordinate(coordinate.get("easting"), coordinate.get("northing")));
        featureBuilder.add(point);
        SimpleFeature sfeature = featureBuilder.buildFeature(null);
        return sfeature;
    }
}
