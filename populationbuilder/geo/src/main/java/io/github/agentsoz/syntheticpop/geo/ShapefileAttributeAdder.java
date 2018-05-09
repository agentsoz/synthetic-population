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

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.geotools.data.DataStore;
import org.geotools.data.DataUtilities;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.FileDataStoreFactorySpi;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class ShapefileAttributeAdder {
	public void addNewAttribute(SimpleFeatureSource featureSource) throws IOException {
		SimpleFeatureType sft = featureSource.getSchema();

		// Create the new type using the former as a template
		SimpleFeatureTypeBuilder stb = new SimpleFeatureTypeBuilder();
		stb.init(sft);
		stb.setName(sft.getName());

		// Add the new attribute
		stb.add("SA1", String.class);
		SimpleFeatureType newFeatureType = stb.buildFeatureType();

		// Create the collection of new Features
		SimpleFeatureBuilder sfb = new SimpleFeatureBuilder(newFeatureType);
		SimpleFeatureCollection collection = new DefaultFeatureCollection();

		SimpleFeatureIterator it = featureSource.getFeatures().features();
		try {
			while (it.hasNext()) {
				SimpleFeature sf = it.next();
				sfb.addAll(sf.getAttributes());
				sfb.set("SA1", "55555");
				((DefaultFeatureCollection) collection).add(sfb.buildFeature(null));
			}
		} finally {
			it.close();
		}

		FileDataStoreFactorySpi dataStoreFactory = new ShapefileDataStoreFactory();

		Map<String, Serializable> params = new HashMap<String, Serializable>();
		URL zip = new URL("jar:"+Paths.get("/home/bhagya/repos/anonymous/sources/geo/data").toUri()+"output.zip");
		URL shape = new URL("jar:"+zip);
		params.put("url", shape);
		params.put("create spatial index", Boolean.TRUE);
		DataStore newDataStore = dataStoreFactory.createNewDataStore(params);
		newDataStore.createSchema(newFeatureType);

		Transaction transaction = new DefaultTransaction("create");

		String typeName = newDataStore.getTypeNames()[0];
		SimpleFeatureSource newFeatureSource = newDataStore.getFeatureSource(typeName);

		if (newFeatureSource instanceof SimpleFeatureStore) {
			SimpleFeatureStore featureStore = (SimpleFeatureStore) newFeatureSource;

			featureStore.setTransaction(transaction);
			try {
				featureStore.addFeatures(collection);
				transaction.commit();

			} catch (Exception problem) {
				
				problem.printStackTrace();
				transaction.rollback();

			} finally {
				transaction.close();
			}
		} else {
			System.out.println(typeName + " does not support read/write access");
			System.exit(1);
		}

	}

	public static void main(String args[]) throws IOException {

		ShapefileAttributeAdder saa = new ShapefileAttributeAdder();
		SimpleFeatureSource sfs = DataUtilities.simple(new ShapefileGeoFeatureReader().getFeatureSource(Paths
				.get("/home/bhagya/repos/anonymous/data/shapefiles/address-shapefiles/alphington/VMADD/ADDRESS.shp")));
		saa.addNewAttribute(sfs);
	}

}
