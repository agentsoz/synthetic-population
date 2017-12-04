package bnw.abm.intg.BuildingProperties;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;

import bnw.abm.intg.geo.ShapefileGeoFeatureReader;
import bnw.abm.intg.util.BNWLogger;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class SA1Reader {
	private static final Logger logger = BNWLogger.getLogger("bnw.phd.intg.buildingproperties");

	static SimpleFeatureCollection getSA1Collection(Path sa1Path, String sa1FilterProp, String[] sa1FilterVals) {
		SimpleFeatureCollection sa1Collection = null;
		ShapefileGeoFeatureReader geoPoly = new ShapefileGeoFeatureReader();
		try {
			geoPoly.loadFeaturesByProperty(sa1Path, sa1FilterProp, sa1FilterVals);
			sa1Collection = DataUtilities.simple(geoPoly.getFeatures());
			//Path sa1OutLoc = Paths.get(ResultOutputDir + "ABS_2011_Selected_SA1s.zip").toAbsolutePath();
			//saveShapeFile(sa1Collection, sa1OutLoc, null);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "SA1s reading failed", e);
		}
		logger.log(Level.INFO, "Reading SA1 shapes complete");
		return sa1Collection;
	}
}
