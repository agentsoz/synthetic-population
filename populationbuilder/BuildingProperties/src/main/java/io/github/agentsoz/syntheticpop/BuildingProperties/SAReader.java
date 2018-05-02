package io.github.agentsoz.syntheticpop.BuildingProperties;

import io.github.agentsoz.syntheticpop.geo.ShapefileGeoFeatureReader;
import io.github.agentsoz.syntheticpop.util.Log;
import org.geotools.data.DataUtilities;
import org.geotools.data.FeatureSource;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class SAReader {

	static SimpleFeatureCollection getSA1Collection(Path sa1Path, String sa1FilterProp, String[] sa1FilterVals) {
		SimpleFeatureCollection sa1Collection = null;
		ShapefileGeoFeatureReader geoPoly = new ShapefileGeoFeatureReader();

		try {
			FeatureSource<SimpleFeatureType, SimpleFeature> ftSrc =  geoPoly.getFeatureSource(sa1Path);
			sa1Collection = DataUtilities.simple(geoPoly.loadFeaturesByProperty(ftSrc, sa1FilterProp, sa1FilterVals));
			//Path sa1OutLoc = Paths.get(ResultOutputDir + "ABS_2011_Selected_SA1s.zip").toAbsolutePath();
			//saveShapeFile(sa1Collection, sa1OutLoc, null);
		} catch (IOException e) {
			Log.error("SA1s reading failed", e);
		}
		Log.info("Reading SA1 shapes complete");
		return sa1Collection;
	}
}
