package bnw.abm.intg.BuildingProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import bnw.abm.intg.filemanager.BNWFiles;
import bnw.abm.intg.filemanager.zip.Zip;
import bnw.abm.intg.geo.FeatureProcessing;
import bnw.abm.intg.geo.ShapefileGeoFeatureReader;
import bnw.abm.intg.geo.ShapefileGeoFeatureWriter;
import bnw.abm.intg.util.BNWLogger;
import bnw.abm.intg.util.BNWProperties;
import bnw.abm.intg.util.ConsoleProgressBar;
import bnw.abm.intg.util.LIFOLinkedHashSet;

/**
 * Hello world!
 *
 */
public class BuildingsToSA1Mapper {
	private static final Logger LOGGER = BNWLogger.getLogger("bnw.phd.geo");

	private static Path tempOutputDir = null;
	private static String ResultOutputDir = null;

	public static void main(String[] args) {

		/* Read all the properties */
		Path sa1Path = null, zipDir = null;
		String sa1FilterProp = null, newFKInAddrRecord = null, localKeyInSA1ShpFile = null;
		String[] sa1FilterVals = null;

		if (args.length > 0) {
			BNWProperties props = null;
			try {
				props = new BNWProperties(args[0]);
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.toString(), e);
			}
			sa1Path = props.readFileOrDirectoryPath("SA1shapefile");
			zipDir = props.readFileOrDirectoryPath("AddressFilesDirectory");
			sa1FilterProp = props.getProperty("SA1FilterPropertyName").trim();
			sa1FilterVals = props.getProperty("SA1FilterPropertyValues").trim().split(",");
			newFKInAddrRecord = props.getProperty("NewForeignKeyInAddressRecord").trim();
			localKeyInSA1ShpFile = props.getProperty("LocalKeyInSA1Shapefile").trim();
			tempOutputDir = Paths.get(props.getProperty("TemporaryOutputDirectory").trim().equals("system")
					? System.getProperty("java.io.tmpdir")
					: props.getProperty("TemporaryOutputDirectory").trim() + File.separator);
			ResultOutputDir = props.readFileOrDirectoryPath("ResultOutputDirectory") + File.separator;

		} else {
			System.err.println("Give path to config.properties as the first argument");
		}

		/*
		 * Read all the address shape files one by one and see which SA1 each
		 * building address belongs to
		 */
		ShapefileGeoFeatureReader geoPoints = new ShapefileGeoFeatureReader();
		SimpleFeatureCollection sa1Collection = null, allAddressCollection = null;

		String zipPattern = "*zip";
		List<Path> shpZipPaths = null;
		shpZipPaths = BNWFiles.find(zipDir, zipPattern);

		for (Path zipFile : shpZipPaths) {

			List<Path> shpFiles = new ArrayList<Path>();
			/*
			 * We expect ADDRESS.shp to be address shape file's name
			 */
			try {
				shpFiles.addAll(Zip.findFiles(zipFile.toAbsolutePath(), "ADDRESS.shp"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			if (shpFiles.size() > 1) {
				System.out.println("Multiple matching shape files inside the zip. Accepting only the 0th file");
				System.out.println(shpFiles);
			}

			Path zipName = zipFile.getFileName();
			try {
				geoPoints.loadFeatures(shpFiles.get(0));
				allAddressCollection = (SimpleFeatureCollection) geoPoints.getFeatures();
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.toString(), e);
			}

			/* Read the interested SA1 polygons */
			if (sa1Collection == null) {
				sa1Collection = getSA1Collection(sa1Path.toAbsolutePath(),
						sa1FilterProp,
						sa1FilterVals,
						allAddressCollection.getSchema().getCoordinateReferenceSystem());
			}

			FeatureProcessing fp = new FeatureProcessing();
			FeatureCollection copyAddresses = null;
			try {
				copyAddresses = fp.addNewAttributeType(allAddressCollection, newFKInAddrRecord, String.class);
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.toString(), e);
			}
			// Now we start matching polygons to points

			/*
			 * Going through all the polygons every time we check a point is
			 * very time consuming. So we keep a set of recently matched
			 * polygons to see if the new point is within one of these polygons.
			 * Rationale is points are likely to be clustered together
			 * spatially. So we don't have to check full polygons list unless
			 * the new point is outside the current cluster. Every time we find
			 * a matching polygon we move it to the top of the recently matched
			 * set. Size of recently matched polygon is set to 15 because that
			 * is a likely number of polygons encompassed in an address shape
			 * file (postal area)
			 */
			LIFOLinkedHashSet<Feature> recentMatches = new LIFOLinkedHashSet<>(15);
			SimpleFeature matchingSA1 = null;
			int add = 1;
			try (FeatureIterator<SimpleFeature> allPoints = copyAddresses.features()) {
				while (allPoints.hasNext()) {
					ConsoleProgressBar.updateProgress(zipName.toString(),
							add / (float) copyAddresses.size(),
							new String((add++) + "/" + copyAddresses.size() + " addresses"));
					Feature point = allPoints.next();
					try {
						if (!recentMatches.isEmpty()) {
							matchingSA1 = (SimpleFeature) fp.getContainingPolygon(recentMatches, point);
						}
						if (matchingSA1 == null) {
							matchingSA1 = (SimpleFeature) fp.getContainingPolygon(sa1Collection, point);
						}

						if (matchingSA1 != null) {
							((SimpleFeature) point).setAttribute(newFKInAddrRecord,
									matchingSA1.getAttribute(localKeyInSA1ShpFile));
							recentMatches.add(matchingSA1);
							LOGGER.log(Level.INFO, matchingSA1.toString());
						}

					} catch (Exception e) {
						LOGGER.log(Level.SEVERE, e.toString(), e);
					}

				}
			}

			// Save the updated addresses in zipped shapefile
			Path outFile = null;
			try {
				outFile = new ShapefileGeoFeatureWriter().writeFeatures(DataUtilities.simple(copyAddresses),
						tempOutputDir.toAbsolutePath());
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.toString(), e);
				e.printStackTrace();
			}
			String outFileName = outFile.getFileName().toString();
			List<Path> filesToZip = BNWFiles.find(tempOutputDir.toAbsolutePath(), outFileName.split("\\.")[0] + ".*");
			try {
				Zip.create(Paths.get(ResultOutputDir + zipName).toAbsolutePath(), filesToZip);
				LOGGER.log(Level.ALL, "Completed Address zip: " + zipName.toString());
			} catch (Throwable e) {
				LOGGER.log(Level.SEVERE, e.toString(), e);
				e.printStackTrace();
			}
			System.out.println();
		}
	}

	private static SimpleFeatureCollection getSA1Collection(Path sa1Path,
			String sa1FilterProp,
			String[] sa1FilterVals,
			CoordinateReferenceSystem crs) {
		SimpleFeatureCollection sa1Collection = null;
		ShapefileGeoFeatureReader geoFeatReader = new ShapefileGeoFeatureReader();
		try {
			geoFeatReader.loadFeaturesByProperty(sa1Path, sa1FilterProp, sa1FilterVals, crs);
			sa1Collection = DataUtilities.simple(geoFeatReader.getFeatures());
			new ShapefileGeoFeatureWriter().writeFeatures(sa1Collection, tempOutputDir.toAbsolutePath());
			String featureName = ((SimpleFeatureCollection) sa1Collection).getSchema().getName().toString();
			List<Path> filesToZip = BNWFiles.find(tempOutputDir.toAbsolutePath(), featureName + ".*");
			try {
				Path sa1OutLoc = Paths.get(ResultOutputDir + featureName + "_Selected_SA1s.zip").toAbsolutePath();
				Zip.create(sa1OutLoc, filesToZip);
				LOGGER.log(Level.INFO, "Saved selected SA1s to: " + sa1OutLoc.toString());
			} catch (Throwable e) {
				LOGGER.log(Level.SEVERE, e.toString(), e);
				e.printStackTrace();
			}

		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
		return sa1Collection;
	}

}
