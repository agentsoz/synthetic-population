package bnw.abm.intg.geo;

import java.io.IOException;
import java.nio.file.Path;

import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.map.DefaultMapContext;
import org.geotools.map.MapContext;
import org.geotools.swing.JMapFrame;
import org.opengis.feature.simple.SimpleFeature;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public abstract class GeoFeatureReader {
	FeatureCollection featureCollection = null;

	public boolean anyFeatures() {
		if (featureCollection == null) {
			return false;
		}
		return true;
	}

	public FeatureCollection getFeatures() {
		if (this.featureCollection == null) {
			throw new NullPointerException(
					"Geographical features are not loaded. Call loadFeatures() method before calling getPoints()");
		}
		return this.featureCollection;
	}

	abstract public void loadFeatures(Path input) throws IOException;

	abstract public void loadFeaturesByProperty(Path target, String property, String[] propertyValues)
			throws IOException;

	/**
	 * Print and show features in feature collection (Uses deprecated library
	 * functions)
	 */
	void display() {

		try (FeatureIterator<SimpleFeature> features = featureCollection.features()) {
			while (features.hasNext()) {
				SimpleFeature feature = features.next();
				System.out.print(feature.getID());
				System.out.print(": ");
				System.out.println(feature.getDefaultGeometryProperty().getValue());
			}
		}

		// Create a map context and add our shapefile to it
		MapContext map = new DefaultMapContext();
		map.addLayer(featureCollection, null);

		// Create a JMapFrame with custom toolbar buttons
		JMapFrame mapFrame = new JMapFrame(map);
		mapFrame.enableToolBar(true);
		mapFrame.enableStatusBar(true);

		// Display the map frame. When it is closed the application will exit
		mapFrame.setSize(800, 600);
		mapFrame.setVisible(true);
	}

	public static void display(FeatureCollection featureCollection) {

		try (FeatureIterator<SimpleFeature> features = featureCollection.features()) {
			while (features.hasNext()) {
				SimpleFeature feature = features.next();
				System.out.print(feature.getID());
				System.out.print(": ");
				System.out.println(feature.getDefaultGeometryProperty().getValue());
			}
		}

		// Create a map context and add our shapefile to it
		MapContext map = new DefaultMapContext();
		map.addLayer(featureCollection, null);

		// Create a JMapFrame with custom toolbar buttons
		JMapFrame mapFrame = new JMapFrame(map);
		mapFrame.enableToolBar(true);
		mapFrame.enableStatusBar(true);

		// Display the map frame. When it is closed the application will exit
		mapFrame.setSize(800, 600);
		mapFrame.setVisible(true);
	}

}
