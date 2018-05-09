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

import io.github.agentsoz.syntheticpop.util.GlobalConstants;
import io.github.agentsoz.syntheticpop.util.Log;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.FileDataStoreFactorySpi;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Bhagya N. Wickramasinghe
 */
public class ShapefileGeoFeatureWriter {

    /**
     * Writes the feature collection in shape file format
     *
     * @param featureCollection feature collection to save
     * @param outputDir         directory where output files to be written
     * @throws IOException When reading and writing files
     */
    public Path writeFeatures(SimpleFeatureCollection featureCollection, Path outputDir) throws IOException {
        String featureName = ((SimpleFeatureCollection) featureCollection).getSchema().getName().toString();

        Path path = Paths.get(outputDir + File.separator + featureName + ".shp");
        FileDataStoreFactorySpi dataStoreFactory = new ShapefileDataStoreFactory();
        Map<String, Serializable> params = new HashMap<>();
        params.put("url", path.toUri().toURL());
        params.put("create spatial index", Boolean.TRUE);
        ShapefileDataStore newDataStore = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
        newDataStore.createSchema((SimpleFeatureType) featureCollection.getSchema());

        int attempt = 0, MAX_ATTEMPTS = 3;
        while (attempt < MAX_ATTEMPTS) {
            Transaction transaction = new DefaultTransaction("create");
            String typeName = newDataStore.getTypeNames()[0];
            SimpleFeatureSource newFeatureSource = newDataStore.getFeatureSource(typeName);

            if (newFeatureSource instanceof SimpleFeatureStore) {
                SimpleFeatureStore featureStore = (SimpleFeatureStore) newFeatureSource;

                featureStore.setTransaction(transaction);
                try {
                    featureStore.addFeatures(featureCollection);
                    transaction.commit();
                    attempt = MAX_ATTEMPTS; //Transaction was successful. So don't try again.
                    Log.debug("Saving transaction successful");
                } catch (Exception problem) {
                    Log.error("Attempt "+attempt+" to save shape file failed", problem);
                    transaction.rollback();
                } finally {
                    attempt++;
                    transaction.close();
                    newDataStore.dispose();
                }
            } else {
                Log.errorAndExit(newFeatureSource.getClass() + " is not an instance of " + SimpleFeatureStore.class,
                                 GlobalConstants.ExitCode.DATA_ERROR);
            }
        }
        return path;

    }

}
