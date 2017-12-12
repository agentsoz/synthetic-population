package bnw.abm.intg.BuildingProperties;

import bnw.abm.intg.geo.FeatureProcessing;
import bnw.abm.intg.util.LIFOLinkedHashSet;
import bnw.abm.intg.util.Log;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.util.*;
import java.util.logging.Level;
import java.util.zip.DataFormatException;

/**
 * @author Bhagya N. Wickramasinghe
 */
public class SA1Locater {

    private static LIFOLinkedHashSet<Feature> recentMatches = new LIFOLinkedHashSet<>(15);

    /**
     * Finds the SA1 each building belongs to based on geographical location
     *
     * @param allBuildings List of buildings
     * @param targetSA1s   List of SA1 matched against
     * @throws SchemaException
     */
    static void findSA1sOfBuildings(SimpleFeatureCollection allBuildings, SimpleFeatureCollection targetSA1s) throws SchemaException {

        Map<String, List<SimpleFeature>> buildingsBySA1 = new HashMap<>();
        SimpleFeature matchingSA1 = null;
        FeatureProcessing fp = new FeatureProcessing();
        try (SimpleFeatureIterator buildingsInterator = allBuildings.features()) {
            while (buildingsInterator.hasNext()) {
                SimpleFeature building = buildingsInterator.next();
                try {
                    if (!recentMatches.isEmpty()) {
                        matchingSA1 = (SimpleFeature) fp.getContainingPolygon(recentMatches, building);
                    }
                    if (matchingSA1 == null) {
                        matchingSA1 = (SimpleFeature) fp.getContainingPolygon(targetSA1s, building);
                    }

                    if (matchingSA1 != null) {
                        recentMatches.add(matchingSA1);
                        String sa1code = (String) matchingSA1.getAttribute("SA1_7DIG11");
                        if (buildingsBySA1.containsKey(sa1code))
                            buildingsBySA1.get(sa1code).add(building);
                        else
                            buildingsBySA1.put(sa1code, new ArrayList<>(Arrays.asList(building)));
                    }

                } catch (Exception e) {
                    Log.error("Failed when trying to read from buildings featureCollection", e);
                }

            }
        }
    }

    /**
     * Finds the SA1 the building belongs to based on geographical location
     *
     * @param building   the building
     * @param targetSA1s List of SA1 matched against
     * @return SA1_7DIG11 code of matching SA1
     * @throws SchemaException
     */
    static String findSA1ofBuilding(SimpleFeature building, SimpleFeatureCollection targetSA1s) throws DataFormatException {

        SimpleFeature matchingSA1 = null;
        FeatureProcessing fp = new FeatureProcessing();

        if (!recentMatches.isEmpty()) {
            matchingSA1 = (SimpleFeature) fp.getContainingPolygon(recentMatches, building);
        }
        if (matchingSA1 == null) {
            matchingSA1 = (SimpleFeature) fp.getContainingPolygon(targetSA1s, building);
        }

        if (matchingSA1 != null) {
            recentMatches.add(matchingSA1);
            return (String) matchingSA1.getAttribute("SA1_7DIG11");
        }
        return null;

    }

    /**
     * Creates a SimpleFeature using a Map object giving coordinates of a geographical point.
     * SimpleFeatureType is hard coded to type name:"Location" and typeSpec:"location:Point:srid=28355".
     *
     * @param coordinate Map with two entries for "easting" and "northing"
     * @return SimpleFeature representing the point
     * @throws SchemaException
     */
    static SimpleFeature makeFeature(Map<String, Double> coordinate) throws SchemaException {
        final SimpleFeatureType TYPE = DataUtilities.createType("Location", "location:Point:srid=28355"); // Point type

        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);
        SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(TYPE);
        Point point = geometryFactory.createPoint(new Coordinate(coordinate.get("easting"), coordinate.get("northing")));
        featureBuilder.add(point);
        SimpleFeature sfeature = featureBuilder.buildFeature(null);
        return sfeature;
    }
}
