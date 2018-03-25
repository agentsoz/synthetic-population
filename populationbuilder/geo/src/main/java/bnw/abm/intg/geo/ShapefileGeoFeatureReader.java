package bnw.abm.intg.geo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.data.Query;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.FeatureCollection;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import bnw.abm.intg.filemanager.zip.Zip;

public class ShapefileGeoFeatureReader extends GeoFeatureReader {

    public void loadFeatures(Path input) throws IOException {

        FeatureSource<SimpleFeatureType, SimpleFeature> source = this.getFeatureSource(input);
        Filter filter = Filter.INCLUDE; // ECQL.toFilter("BBOX(THE_GEOM,
                                        // 10,20,30,40)")

        this.featureCollection = source.getFeatures(filter);

    }

    // public void loadFeatures(List<Path> inputs) throws IOException {
    //
    // Map<String, Object> map = new HashMap<String, Object>();
    // DefaultFeatureCollection dfc = new DefaultFeatureCollection();
    // for (Path inPath : inputs) {
    // FeatureSource<SimpleFeatureType, SimpleFeature> source = this.getFeatureSource(inPath);
    // Filter filter = Filter.INCLUDE;
    // FeatureCollection<SimpleFeatureType, SimpleFeature> collection = source.getFeatures(filter);
    // dfc.addAll(collection);
    // }
    //
    // this.featureCollection = dfc;
    //
    // }

    /**
     * Load features from a shapefile and assigns the specified CoordinateReferenceSystem
     *
     * @param target
     *            File path to shapefile
     * @param crs
     *            target coordinate reference system
     * @throws IOException
     */
    public void loadFeatures(Path target, CoordinateReferenceSystem crs) throws IOException {

        FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = this.getFeatureSource(target);

        Query query = new Query(null, null);
        query.setCoordinateSystemReproject(crs);

        FeatureCollection features = featureSource.getFeatures(query);
        this.featureCollection = features;
    }

    public void loadFeaturesByProperty(Path target, String property, String[] propertyValues) throws IOException {

        FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = this.getFeatureSource(target);

        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

        List<Filter> match = new ArrayList<Filter>();
        for (String name : propertyValues) {
            Filter aMatch = ff.equals(ff.property(property), ff.literal(name));
            match.add(aMatch);
        }
        Filter filter = ff.or(match);

        this.featureCollection = featureSource.getFeatures(filter);
        featureSource.getDataStore().dispose();
    }

    /**
     * Load features from a shapefile and filters the properties based on a property and its values. Multiple property values are handled like in
     * a logical OR operation
     * 
     * @param target
     *            File path to shapefile
     * @param property
     *            Name of the property in shape file
     * @param propertyValues
     *            Values of the property
     * @param crs
     *            target coordinate reference system
     * @throws IOException
     */
    public void loadFeatures(Path target, String property, String[] propertyValues, CoordinateReferenceSystem crs) throws IOException {

        FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = this.getFeatureSource(target);

        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();
        List<Filter> match = new ArrayList<Filter>();
        for (String name : propertyValues) {
            Filter aMatch = ff.equals(ff.property(property), ff.literal(name));
            match.add(aMatch);
        }
        Filter filter = ff.or(match);
        Query query = new Query(property, null);
        // query.setCoordinateSystem(crs);
        query.setCoordinateSystemReproject(crs);

        FeatureCollection features = featureSource.getFeatures(query);
        this.featureCollection = features;
    }

    /**
     * Read features from given target file
     * 
     * @param target
     *            The file to read
     * @param filter
     *            The filter to apply when reading
     * @throws IOException
     */
    public void loadFeatures(Path target, Filter filter) throws IOException {

        FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = this.getFeatureSource(target);

        this.featureCollection = featureSource.getFeatures(filter);

    }

    /**
     * Obtain feature source from given file
     * 
     * @param target
     *            The file to process
     * @return Feature source
     * @throws IOException
     */
    public FeatureSource<SimpleFeatureType, SimpleFeature> getFeatureSource(Path target) throws IOException {
        if (target.toString().endsWith(".zip")) {
            List<Path> shapes = Zip.findFiles(target, "*.shp");
            target = shapes.get(0);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", target.toUri().toURL());

        DataStore dataStore = DataStoreFinder.getDataStore(map);
        String typeName = dataStore.getTypeNames()[0];
        FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = dataStore.getFeatureSource(typeName);

        return featureSource;
    }

}

