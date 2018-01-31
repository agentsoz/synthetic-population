package bnw.abm.intg.BuildingProperties;

import bnw.abm.intg.filemanager.BNWFiles;
import bnw.abm.intg.filemanager.zip.Zip;
import bnw.abm.intg.geo.FeatureProcessing;
import bnw.abm.intg.geo.ShapefileGeoFeatureReader;
import bnw.abm.intg.geo.ShapefileGeoFeatureWriter;
import bnw.abm.intg.util.BNWProperties;
import bnw.abm.intg.util.ConsoleProgressBar;
import bnw.abm.intg.util.LIFOLinkedHashSet;
import bnw.abm.intg.util.Log;
import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class BuildingsToSA1Mapper {

    private static Path tempOutputDir = null;
    private static String ResultOutputDir = null;

    public static void main(String[] args) {
        Log.createLogger("BuildingsToSA1Mapper", "BuildingsToSA1Mapper.log");
        /* Read all the properties */
        Path sa1Path = null, zipDir = null;
        String sa1FilterProp = null, newFKInAddrRecord = null, localKeyInSA1ShpFile = null;
        String[] sa1FilterVals = null;

        if (args.length > 0) {
            BNWProperties props = null;
            try {
                props = new BNWProperties(args[0]);
            } catch (IOException e) {
                Log.error("When reading properties file", e);
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
                Log.error("When loading geo points from address.shp file", e);
            }

            /* Read the interested SA1 polygons */
            if (sa1Collection == null) {
                try {
                    sa1Collection = getSA1Collection(sa1Path.toAbsolutePath(),
                            sa1FilterProp,
                            sa1FilterVals,
                            allAddressCollection.getSchema().getCoordinateReferenceSystem(),
                            ResultOutputDir);
                } catch (IOException e) {
                    Log.error("When loading SA1s from shape files", e);
                } catch (URISyntaxException e) {
                    Log.error("When creating the zip file containing selected SA1 geo feature polygons", e);
                }
            }

            FeatureProcessing fp = new FeatureProcessing();
            FeatureCollection copyAddresses = null;
            try {
                copyAddresses = fp.addNewAttributeType(allAddressCollection, newFKInAddrRecord, String.class);
            } catch (IOException e) {
                Log.error("When adding the new attribute to copy of address collection", e);
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
             * set. Size of recently matched polygon is a set to 15 because that
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
                            (add++) + "/" + copyAddresses.size() + " addresses");
                    SimpleFeature point = allPoints.next();
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
                            Log.info(matchingSA1.toString());
                        }

                    } catch (Exception e) {
                        Log.error("When finding the SA1 polygon that an address belongs to", e);
                    }

                }
            }

            // Save the updated addresses in zipped shapefile
            Path outFile = null;
            try {
                outFile = new ShapefileGeoFeatureWriter().writeFeatures(DataUtilities.simple(copyAddresses),
                        tempOutputDir.toAbsolutePath());
            } catch (IOException e) {
                Log.error("When writing updated addresses shape file", e);
                e.printStackTrace();
            }
            String outFileName = outFile.getFileName().toString();
            List<Path> filesToZip = BNWFiles.find(tempOutputDir.toAbsolutePath(), outFileName.split("\\.")[0] + ".*");
            try {
                Zip.create(Paths.get(ResultOutputDir + zipName).toAbsolutePath(), filesToZip);
                Log.info("Completed Address zip: " + zipName.toString());
            } catch (Throwable e) {
                Log.error("When creating " + zipName.toString() + " file", e);
                e.printStackTrace();
            }
            System.out.println();
        }
    }

    /**
     * Filters and loads SA1 geo feature polygons from a shape file based on a property and an array of values that the property can have.
     * It also saves the selected SA1 features in a zip file.
     *
     * @param sa1Path                      The path of the SA1 shape file
     * @param sa1FilterProp                The name of the property
     * @param sa1FilterVals                The array of values that the sa1FilterProp can take
     * @param crs                          Coordinate reference system of the shape file
     * @param dirToSaveSelectedSA1Features The location to save selected SA1 geo feature polygons
     * @return a list of SA1 geo features polygons
     * @throws IOException        When reading SA1 geo features from the SA1 shape file
     * @throws URISyntaxException When creating a zip file with selected SA1 geo features
     */
    private static SimpleFeatureCollection getSA1Collection(Path sa1Path,
                                                            String sa1FilterProp,
                                                            String[] sa1FilterVals,
                                                            CoordinateReferenceSystem crs,
                                                            String dirToSaveSelectedSA1Features) throws IOException, URISyntaxException {
        SimpleFeatureCollection sa1Collection = null;
        ShapefileGeoFeatureReader geoFeatReader = new ShapefileGeoFeatureReader();

        geoFeatReader.loadFeatures(sa1Path, sa1FilterProp, sa1FilterVals, crs);
        sa1Collection = DataUtilities.simple(geoFeatReader.getFeatures());
        new ShapefileGeoFeatureWriter().writeFeatures(sa1Collection, tempOutputDir.toAbsolutePath());
        String featureName = ((SimpleFeatureCollection) sa1Collection).getSchema().getName().toString();
        List<Path> filesToZip = BNWFiles.find(tempOutputDir.toAbsolutePath(), featureName + ".*");

        Path sa1OutLoc = Paths.get(dirToSaveSelectedSA1Features + featureName + "_Selected_SA1s.zip").toAbsolutePath();
        Zip.create(sa1OutLoc, filesToZip);
        Log.info("Saved selected SA1s to: " + sa1OutLoc.toString());

        return sa1Collection;
    }

}
