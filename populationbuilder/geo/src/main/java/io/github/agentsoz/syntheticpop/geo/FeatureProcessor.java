package io.github.agentsoz.syntheticpop.geo;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2020 by its authors. See AUTHORS file.
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

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTS;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Random;

/**
 * @author Bhagya N. Wickramasinghe
 */
public class FeatureProcessor {

    /**
     * Returns the feature that contains the input point
     *
     * @param simplefeaturePolygonCollection Collection of feature polygons e.g. collection of potential SA1s
     * @param point                          The point which need to be checked e.g. Building location
     * @return The list of feature polygons which contain the point
     */
    public SimpleFeature getContainingPolygon(SimpleFeatureCollection simplefeaturePolygonCollection,
                                              SimpleFeature point) {


        SimpleFeature containingPolygon = null;
        try (FeatureIterator<SimpleFeature> featureIterator = simplefeaturePolygonCollection.features()) {
            while (featureIterator.hasNext()) {
                SimpleFeature fPolygon = featureIterator.next();
                Geometry jtsGeoPolygon = (Geometry) fPolygon.getDefaultGeometry();
                Point jtsPoint = (Point) point.getDefaultGeometryProperty().getValue();

                if (jtsGeoPolygon.contains(jtsPoint)) {
                    containingPolygon = fPolygon;
                    break;
                }
            }
        }

        return containingPolygon;
    }

    public SimpleFeature getContainingPolygon(LinkedHashSet<SimpleFeature> featurePolygons, SimpleFeature point) {

        for (SimpleFeature polygon : featurePolygons) {
            Geometry jtsGeoPolygon = (Geometry) polygon.getDefaultGeometry();
            Point jtsPoint = (Point) point.getDefaultGeometryProperty().getValue();

            if (jtsGeoPolygon.contains(jtsPoint)) {
                return polygon;
            }
        }
        return null;
    }

    /**
     * Checks whether point geographically located within the envelope
     *
     * @param envelope The envelope
     * @param point    The point
     * @return True if point is within the envelope, false otherwise
     */
    public boolean checkContains(Envelope envelope, SimpleFeature point) {

        Point p = (Point) point.getDefaultGeometry();
        return envelope.contains(p.getCoordinate());

    }

    /**
     * Checks whether point geographically located within the polygon
     *
     * @param polygon The polygon
     * @param point   The point
     * @return True if point is within the polygon, false otherwise
     */
    public boolean checkContains(SimpleFeature polygon, SimpleFeature point) {
        Point jtsPoint = (Point) point.getDefaultGeometry();
        Geometry jtsGeoPolygon = (Geometry) polygon.getDefaultGeometry();
        return jtsGeoPolygon.contains(jtsPoint);
    }

    /**
     * Transforms Coordinate Reference System (CRS) of the features in featureCollection
     *
     * @param featureCollection features
     * @param transformer       MathTransform object for converting feature's CRS to target CRS
     * @throws MismatchedDimensionException When finding the CRS
     * @throws TransformException           When finding the CRS
     */
    public SimpleFeatureCollection transformGeometryCRS(SimpleFeatureCollection featureCollection,
                                                        MathTransform transformer) throws MismatchedDimensionException, TransformException {

        try (FeatureIterator<SimpleFeature> featureItr = featureCollection.features()) {
            while (featureItr.hasNext()) {
                SimpleFeature feature = featureItr.next();
                transformGeometryCRS(feature, transformer);
            }

        }
        return featureCollection;
    }

    /**
     * Transforms Coordinate Reference System (CRS) of the feature
     *
     * @param feature     Current Feature
     * @param transformer MathTransform object for converting feature's CRS to target CRS
     */
    public void transformGeometryCRS(SimpleFeature feature, MathTransform transformer)
            throws MismatchedDimensionException, TransformException {
        Geometry geometry = (Geometry) feature.getDefaultGeometry();
        Geometry geometry2 = JTS.transform(geometry, transformer);
        feature.setDefaultGeometry(geometry2);

    }

    /**
     * Add new attribute to a copy of the feature collection. Value of the new attribute is set to null
     *
     * @param simpleFeature  The feature to be used as a template
     * @param attributeNames String list of attribute names
     * @param valueClass     Object class of the values
     * @return Copy of the original feature collection with new attribute added
     */
    public SimpleFeature addNewAttributeType(SimpleFeature simpleFeature,
                                             Collection<String> attributeNames,
                                             Class valueClass) throws IOException {
        SimpleFeatureType sFeatureType = DataUtilities.simple(simpleFeature.getFeatureType());

        // Create the new type using the former as a template
        SimpleFeatureTypeBuilder sFeatureTypeBuilder = new SimpleFeatureTypeBuilder();
        sFeatureTypeBuilder.init(sFeatureType);
        sFeatureTypeBuilder.setName(sFeatureType.getName());

        // Add the new attribute
        for (String attributeName : attributeNames) {
            sFeatureTypeBuilder.add(attributeName, valueClass);
        }

        SimpleFeatureType newFeatureType = sFeatureTypeBuilder.buildFeatureType();

        // Create the collection of new Features
        SimpleFeatureBuilder sfeatureBuilder = new SimpleFeatureBuilder(newFeatureType);

        sfeatureBuilder.addAll(simpleFeature.getAttributes());
        sfeatureBuilder.add(null);
        return sfeatureBuilder.buildFeature(null);
    }

    public Geometry getRandomPointIn(Feature mask, Random random) {

        ControlledRandomPointsBuilder ranPointBuilder = new ControlledRandomPointsBuilder(random);
        ranPointBuilder.setExtent((Geometry) ((SimpleFeature) mask).getDefaultGeometry());
        ranPointBuilder.setNumPoints(1);
        Geometry ranP = ranPointBuilder.getGeometry();
        return ranP;
    }

}
