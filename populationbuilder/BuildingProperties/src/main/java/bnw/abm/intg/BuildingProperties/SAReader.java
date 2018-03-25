package bnw.abm.intg.BuildingProperties;

import bnw.abm.intg.geo.ShapefileGeoFeatureReader;
import bnw.abm.intg.util.Log;
import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;

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
			geoPoly.loadFeaturesByProperty(sa1Path, sa1FilterProp, sa1FilterVals);
			sa1Collection = DataUtilities.simple(geoPoly.getFeatures());
			//Path sa1OutLoc = Paths.get(ResultOutputDir + "ABS_2011_Selected_SA1s.zip").toAbsolutePath();
			//saveShapeFile(sa1Collection, sa1OutLoc, null);
		} catch (IOException e) {
			Log.error("SA1s reading failed", e);
		}
		Log.info("Reading SA1 shapes complete");
		return sa1Collection;
	}
}
