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

import io.github.agentsoz.syntheticpop.filemanager.zip.Zip;
import org.geotools.data.*;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.FeatureCollection;
import org.geotools.filter.text.cql2.CQL;
import org.geotools.filter.text.cql2.CQLException;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory;
import org.opengis.filter.FilterFactory2;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShapefileGeoFeatureReader {

    public FeatureCollection loadFeatures(FeatureSource<SimpleFeatureType, SimpleFeature> featureSource) throws IOException {

        Filter filter = Filter.INCLUDE; // ECQL.toFilter("BBOX(THE_GEOM,
        // 10,20,30,40)")

        return featureSource.getFeatures(filter);

    }


    public FeatureCollection loadFeaturesByProperty(FeatureSource<SimpleFeatureType, SimpleFeature> featureSource,
                                                    String property,
                                                    String[] propertyValues) throws IOException {

        FilterFactory ff = CommonFactoryFinder.getFilterFactory();

        List<Filter> match = new ArrayList<>();
        for (String name : propertyValues) {
            Filter aMatch = ff.equals(ff.property(property), ff.literal(name));
            match.add(aMatch);
        }
        Filter filter = ff.or(match);

        return featureSource.getFeatures(filter);
    }

    /**
     * Obtain feature source from given file
     *
     * @param target               The file to process
     * @param shapeFileNamePattern The name pattern of the .shp file
     * @return Feature source
     */
    public FeatureSource<SimpleFeatureType, SimpleFeature> getFeatureSource(Path target, String shapeFileNamePattern) throws IOException {
        if (target.toString().endsWith(".zip")) {
            List<Path> shapes = Zip.findFiles(target, shapeFileNamePattern);
            target = shapes.get(0);
        }
        return getFeatureSource(target.toUri().toURL());
    }

    public FeatureSource<SimpleFeatureType, SimpleFeature> getFeatureSource(Path target) throws IOException {
        return getFeatureSource(target.toUri().toURL());
    }

    public FeatureSource<SimpleFeatureType, SimpleFeature> getFeatureSource(URL target) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", target);

        DataStore dataStore = DataStoreFinder.getDataStore(map);
        FeatureSource<SimpleFeatureType, SimpleFeature> ftSrc;
        try {
            String typeName = dataStore.getTypeNames()[0];
            ftSrc = DataUtilities.simple(dataStore.getFeatureSource(typeName));
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            dataStore.dispose();
        }

        return ftSrc;
    }


}

