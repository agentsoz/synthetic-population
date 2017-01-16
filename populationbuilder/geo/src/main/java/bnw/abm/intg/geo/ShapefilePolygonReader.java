package bnw.abm.intg.geo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.DataUtilities;
import org.geotools.data.FeatureSource;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.FeatureCollection;
import org.geotools.filter.text.cql2.CQLException;
import org.geotools.filter.text.ecql.ECQL;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;

import bnw.abm.intg.util.BNWLogger;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class ShapefilePolygonReader extends ShapefileGeoFeatureReader {
	private static final Logger LOGGER = BNWLogger.getLogger("bnw.abm.intg.geo");

	/**
	 * Load target features within boundary area (Function not working properly)
	 * 
	 * @param target
	 *            target features shapefile path
	 * @param boudaryArea
	 *            bounday area shapefile path
	 * @throws IOException
	 */
	@Deprecated
	public void loadPolygons(Path target, Path boudaryArea) throws IOException {
		Map<String, Object> targetMap = new HashMap<String, Object>();
		targetMap.put("url", target.toUri().toURL());

		DataStore targetDataStore = DataStoreFinder.getDataStore(targetMap);
		String targetTypeName = targetDataStore.getTypeNames()[0];

		FeatureSource<SimpleFeatureType, SimpleFeature> targetSource = targetDataStore.getFeatureSource(targetTypeName);

		Map<String, Object> boundaryAreaMap = new HashMap<String, Object>();
		boundaryAreaMap.put("url", target.toUri().toURL());

		DataStore boundaryAreaDataStore = DataStoreFinder.getDataStore(boundaryAreaMap);
		String boundaryAreaTypeName = boundaryAreaDataStore.getTypeNames()[0];

		FeatureSource<SimpleFeatureType, SimpleFeature> boundaryAreaSource = boundaryAreaDataStore
				.getFeatureSource(boundaryAreaTypeName);
		Filter filter = Filter.INCLUDE;
		FeatureCollection<SimpleFeatureType, SimpleFeature> boundaryAreaFeatureCollection = boundaryAreaSource
				.getFeatures(filter);

		filter = null;
		SimpleFeature boundaryAreaFeature = boundaryAreaFeatureCollection.features().next();
		try {
			filter = ECQL.toFilter("CONTAINS(THE_GEOM," + boundaryAreaFeature.getDefaultGeometry().toString() + ")");
		} catch (CQLException e1) {
			LOGGER.log(Level.SEVERE, e1.toString(), e1);
			e1.printStackTrace();
		}

		FeatureCollection<SimpleFeatureType, SimpleFeature> targetCollection = targetSource.getFeatures(filter);

		featureCollection = targetCollection;
		try {
			super.display();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			e.printStackTrace();
		}

	}

	/**
	 * Loads SA1 features that are within a given array of SA3 names. This a
	 * specialised wrapper for loadPolygonsByProperty()
	 * 
	 * @param target
	 *            Shape file path
	 * @param SA3names
	 *            Array of SA3 area names
	 * @return FeatureCollection of SA1s
	 */
	public void loadSA1sbySA3names(Path target, String[] SA3names) {
		try {
			loadFeaturesByProperty(target, "SA3_NAME11", SA3names);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<String, SimpleFeature> getSA2Areas(Path sa2File, HashMap<String, SimpleFeature> sa2map)
			throws IOException {
		String[] sa2names = (String[]) sa2map.keySet().toArray(new String[0]);
		String property = "SA2_NAME11";

		this.loadFeaturesByProperty(sa2File, property, sa2names);

		try (SimpleFeatureIterator featItr = DataUtilities.simple(this.getFeatures()).features()) {
			while (featItr.hasNext()) {
				SimpleFeature feature = (SimpleFeature) featItr.next();
				sa2map.put((String) feature.getAttribute("SA2_NAME11"), feature);
			}
		}
		return sa2map;
	}
}
