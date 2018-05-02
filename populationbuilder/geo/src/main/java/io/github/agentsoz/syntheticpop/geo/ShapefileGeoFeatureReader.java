package io.github.agentsoz.syntheticpop.geo;

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

    //    public FeatureCollection loadFeatures(List<Path> inputs) throws IOException {
    //
    //        Map<String, Object> map = new HashMap<String, Object>();
    //        DefaultFeatureCollection dfc = new DefaultFeatureCollection();
    //        for (Path inPath : inputs) {
    //            FeatureSource<SimpleFeatureType, SimpleFeature> source = this.getFeatureSource(inPath);
    //            Filter filter = Filter.INCLUDE;
    //            FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
    //            dfc.addAll(collection);
    //        }
    //
    //        return dfc;
    //
    //    }

    /**
     * Load features from a shapefile and assigns the specified CoordinateReferenceSystem
     *
     * @param featureSource FeatureSource instance created with shapefile
     * @param crs           target coordinate reference system
     */
    public FeatureCollection loadFeatures(FeatureSource<SimpleFeatureType, SimpleFeature> featureSource,
                                          CoordinateReferenceSystem crs) throws IOException {

        Query query = new Query(null, null);
        query.setCoordinateSystemReproject(crs);

        return featureSource.getFeatures(query);
    }

    public FeatureCollection loadFeaturesByProperty(FeatureSource<SimpleFeatureType, SimpleFeature> featureSource,
                                                    String property,
                                                    String propertyValue) throws IOException, CQLException {

        Filter filter = CQL.toFilter(property + "=" + propertyValue);

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
     * Load features from a shapefile and filters the properties based on a property and its values. Multiple property values are handled
     * like in
     * a logical OR operation
     *
     * @param featureSource  FeatureSource instance created with shapefile
     * @param property       Name of the property in shape file
     * @param propertyValues Values of the property
     * @param crs            target coordinate reference system
     */
    public FeatureCollection loadFeaturesByProperty(FeatureSource<SimpleFeatureType, SimpleFeature> featureSource,
                                                    String property,
                                                    String[] propertyValues,
                                                    CoordinateReferenceSystem crs) throws IOException {

        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
        List<Filter> match = new ArrayList<>();
        for (String name : propertyValues) {
            Filter aMatch = ff.equals(ff.property(property), ff.literal(name));
            match.add(aMatch);
        }
        Filter filter = ff.or(match);
        Query query = new Query(property, filter);
        // query.setCoordinateSystem(crs);
        query.setCoordinateSystemReproject(crs);

        return featureSource.getFeatures(query);
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
        String typeName = dataStore.getTypeNames()[0];
        FeatureSource<SimpleFeatureType, SimpleFeature> ftSrc = DataUtilities.simple(dataStore.getFeatureSource(typeName));
        dataStore.dispose();
        return ftSrc;
    }


}

