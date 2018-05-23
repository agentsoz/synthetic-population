package io.github.agentsoz.syntheticpop.addressmapper;

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

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;
import io.github.agentsoz.syntheticpop.geo.FeatureProcessor;
import io.github.agentsoz.syntheticpop.geo.ShapefileGeoFeatureReader;
import io.github.agentsoz.syntheticpop.util.GlobalConstants;
import io.github.agentsoz.syntheticpop.util.LIFOLinkedHashSet;
import io.github.agentsoz.syntheticpop.util.Log;
import org.geotools.data.FeatureSource;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wniroshan 18 Apr 2018
 */
class ShapesProcessor {

    final private FeatureProcessor featProcessor;
    final private ShapefileGeoFeatureReader shapesReader;

    /**
     * Matches an address with a statistical area from ABS census data. This class is written in a way so it will work for other ABS
     * geographical areas as well.
     *
     * @param featureProcessor Feature processor instance to perform shape file operations
     * @param shapesReader     A ShapefileGeoFeatureReader instance to read shape files
     */
    ShapesProcessor(FeatureProcessor featureProcessor, ShapefileGeoFeatureReader shapesReader) {
        this.featProcessor = featureProcessor;
        this.shapesReader = shapesReader;
    }

    /**
     * Finds the SA polygon of a the specified address.
     *
     * @param address    The address feature
     * @param saPolygons The Statistical Area polygons and their Envelope map
     * @return The Statistical Area feature of that contains the address
     */
    SimpleFeature findStatisticalArea(SimpleFeature address,
                                      Map<SimpleFeature, Envelope> saPolygons) {

        for (SimpleFeature sa : saPolygons.keySet()) {
            if (featProcessor.checkContains(saPolygons.get(sa), address)) {
                if (featProcessor.checkContains(sa, address)) {
                    return sa;
                }

            }
        }

        return null;
    }

    SimpleFeatureCollection loadAddresses(Path addressShape) throws IOException {
        FeatureSource<SimpleFeatureType, SimpleFeature> addressFeatSrc = shapesReader.getFeatureSource(addressShape);
        return (SimpleFeatureCollection) shapesReader.loadFeatures(addressFeatSrc);

    }

    /**
     * Loads statistical areas features from SA shapes file. This loads only the features where saFilterKey has values specified under
     * saFilterValues. This also computes the envelopes (bounding box) each SA feature.
     *
     * @param saFilePath      SA shapes zip file from ABS
     * @param saShapeFileName The name of the .shp file inside SA shapes zip file
     * @param saFilterKey     A column name in attribute table in SA shapes file
     * @param saFilterValues  An array of values under the saFilterKey column
     * @return A map of SA features and the corresponding bounding boxes
     */
    Map<SimpleFeature, Envelope> loadStatisticalAreas(Path saFilePath,
                                                      String saShapeFileName,
                                                      String saFilterKey,
                                                      String[] saFilterValues) {
        Map<SimpleFeature, Envelope> saFeatures = new HashMap<>();
        try {
            Log.debug("Loading SA shapes where " + saFilterKey + " is " + Arrays.toString(saFilterValues));
            FeatureSource<SimpleFeatureType, SimpleFeature> ftSource = shapesReader.getFeatureSource(saFilePath, saShapeFileName);
            SimpleFeatureCollection meshBlocks = (SimpleFeatureCollection) shapesReader.loadFeaturesByProperty(ftSource,
                                                                                                               saFilterKey,
                                                                                                               saFilterValues);
            try (SimpleFeatureIterator itr = meshBlocks.features()) {
                while (itr.hasNext()) {
                    SimpleFeature sa = itr.next();
                    saFeatures.put(sa, ((Geometry) sa.getDefaultGeometry()).getEnvelopeInternal());
                }
            }

        } catch (IOException ex) {
            Log.errorAndExit("Unable to read " + saShapeFileName + " file", ex, GlobalConstants.ExitCode.USERINPUT);
        }
        return saFeatures;
    }


    /**
     * Updates a copy of the address with new information and returns the copy
     *
     * @param address                The address feature
     * @param sa                     The containing SA of the address
     * @param newAttributesToAddress The new attributes to be added to address feature
     * @throws IOException If processing failed
     */
    SimpleFeature updateAddress(SimpleFeature address,
                                SimpleFeature sa,
                                Map<String, String> newAttributesToAddress) throws IOException {
        SimpleFeature copyAddressFeat = featProcessor.addNewAttributeType(address,
                                                                          newAttributesToAddress.keySet(),
                                                                          String.class);
        for (Map.Entry<String, String> entry : newAttributesToAddress.entrySet()) {
            copyAddressFeat.setAttribute(entry.getKey(), sa.getAttribute(entry.getValue()));
        }

        return copyAddressFeat;
    }

}
