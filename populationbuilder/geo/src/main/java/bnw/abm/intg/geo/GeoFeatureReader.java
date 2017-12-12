package bnw.abm.intg.geo;

import org.geotools.feature.FeatureCollection;

import java.io.IOException;
import java.nio.file.Path;

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

}
