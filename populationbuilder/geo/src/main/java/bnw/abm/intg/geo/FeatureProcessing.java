package bnw.abm.intg.geo;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;

import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTS;
import org.opengis.feature.Feature;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;

import bnw.abm.intg.util.BNWLogger;
import bnw.abm.intg.util.LIFOLinkedHashSet;

/**
 * @author Bhagya N. Wickramasinghe
 *
 */
public class FeatureProcessing {
	private static final Logger LOGGER = BNWLogger.getLogger("bnw.abm.intg.geo");

	/**
	 * Returns the feature that contains the input point
	 * 
	 * @param featurePolygon
	 *            Collection of feature polygons e.g. collection of potential
	 *            SA1s
	 * @param point
	 *            The point which need to be checked e.g. Building location
	 * @return The list of feature polygons which contain the point
	 * @throws DataFormatException
	 *             If CRS of featureCollection and point do not match
	 */
	public Feature getContainingPolygon(FeatureCollection featurePolygon, Feature point) throws DataFormatException {

		// if (!point.getType().equals(featureCollection.getSchema())){
		// throw new DataFormatException("Coordinate Reference Systems of
		// featureCollection and point do not match. Convert them to a common
		// CRS. ");
		// }

		SimpleFeatureCollection sp = DataUtilities.simple(featurePolygon);
		Feature containingPolygon = null;
		try (FeatureIterator<SimpleFeature> featureIterator = sp.features()) {
			while (featureIterator.hasNext()) {
				SimpleFeature fPolygon = featureIterator.next();
				Geometry jtsGeoPolygon = (Geometry) fPolygon.getDefaultGeometry();
				Geometry oPoint = (Geometry) ((SimpleFeature) point).getDefaultGeometry();

				Point jtsPoint = ((Geometry) oPoint).getCentroid();
				// Geometry pointGeom = (Geometry)
				// ((SimpleFeature)point).getDefaultGeometry();

				if (jtsGeoPolygon.contains(jtsPoint)) {
					containingPolygon = fPolygon;
					break;
				}
			}
		}

		return containingPolygon;
	}

	public Feature getContainingPolygon(LIFOLinkedHashSet<Feature> featurePolygons, Feature point) {

		Iterator<Feature> itr = featurePolygons.lifoiterator();
		while (itr.hasNext()) {
			SimpleFeature polygon = (SimpleFeature) itr.next();
			Geometry jtsGeoPolygon = (Geometry) ((SimpleFeature) polygon).getDefaultGeometry();
			Geometry oPoint = (Geometry) ((SimpleFeature) point).getDefaultGeometry();

			Point jtsPoint = ((Geometry) oPoint).getInteriorPoint();
			// Geometry pointGeom = (Geometry)
			// ((SimpleFeature)point).getDefaultGeometry();

			if (jtsGeoPolygon.contains(jtsPoint)) {
				return polygon;
			}
		}
		return null;
	}

	/**
	 * Point in polygon in geotools also works by creating a filter that
	 * directly select polygons from FeatureSource. FilterFactory2 ff =
	 * CommonFactoryFinder.getFilterFactory2( GeoTools.getDefaultHints() );
	 *
	 */

	/**
	 * Transforms Coordinate Reference System (CRS) of the features in
	 * featureCollection
	 * 
	 * @param featureCollection
	 *            features
	 * @param transformer
	 *            MathTransform object for converting feature's CRS to target
	 *            CRS
	 * @throws MismatchedDimensionException
	 * @throws TransformException
	 */
	public SimpleFeatureCollection transformGeometryCRS(SimpleFeatureCollection featureCollection,
			MathTransform transformer) throws MismatchedDimensionException, TransformException {
		// SimpleFeatureCollection sp = DataUtilities.simple(
		// (FeatureCollection<SimpleFeatureType, SimpleFeature>)
		// featureCollection);
		try (FeatureIterator<SimpleFeature> featureItr = featureCollection.features()) {
			while (featureItr.hasNext()) {
				SimpleFeature feature = featureItr.next();
				feature = transformGeometryCRS(feature, transformer);
			}

		}
		return featureCollection;
	}

	/**
	 * Transforms Coordinate Reference System (CRS) of the feature
	 * 
	 * @param feature
	 *            Current Feature
	 * @param transformer
	 *            MathTransform object for converting feature's CRS to target
	 *            CRS
	 * @return
	 * @throws MismatchedDimensionException
	 * @throws TransformException
	 */
	public SimpleFeature transformGeometryCRS(SimpleFeature feature, MathTransform transformer)
			throws MismatchedDimensionException, TransformException {
		Geometry geometry = (Geometry) feature.getDefaultGeometry();
		Geometry geometry2 = JTS.transform(geometry, transformer);
		feature.setDefaultGeometry(geometry2);
		return feature;
	}

	/**
	 * Add new attribute to a copy of the feature collection. Value of the new
	 * attribute is set to null
	 * 
	 * @param featureCollection
	 *            Original feature collection
	 * @param attributeName
	 *            String name of the attribute
	 * @param valueClass
	 *            Object class of the values
	 * @return Copy of the original feature collection with new attribute added
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public FeatureCollection addNewAttributeType(FeatureCollection featureCollection, String attributeName,
			Class valueClass) throws IOException {
		SimpleFeatureType sfeatureType = DataUtilities.simple(featureCollection.getSchema());

		// Create the new type using the former as a template
		SimpleFeatureTypeBuilder sfeatureTypeBuilder = new SimpleFeatureTypeBuilder();
		sfeatureTypeBuilder.init(sfeatureType);
		sfeatureTypeBuilder.setName(sfeatureType.getName());

		// Add the new attribute
		sfeatureTypeBuilder.add(attributeName, valueClass);
		SimpleFeatureType newFeatureType = sfeatureTypeBuilder.buildFeatureType();

		// Create the collection of new Features
		SimpleFeatureBuilder sfeatureBuilder = new SimpleFeatureBuilder(newFeatureType);
		SimpleFeatureCollection newFeatureCollection = new DefaultFeatureCollection();

		SimpleFeatureIterator itr = (SimpleFeatureIterator) featureCollection.features();
		try {
			while (itr.hasNext()) {
				SimpleFeature sfeature = itr.next();
				sfeatureBuilder.addAll(sfeature.getAttributes());
				sfeatureBuilder.add(null);
				((DefaultFeatureCollection) newFeatureCollection).add(sfeatureBuilder.buildFeature(null));
			}
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, ex.toString(), ex);
		} finally {
			itr.close();
		}
		return newFeatureCollection;
	}

	public Geometry getRandomPointIn(Feature mask, Random random) {
		
		ControlledRandomPointsBuilder ranPointBuilder = new ControlledRandomPointsBuilder(random);
		ranPointBuilder.setExtent((Geometry) ((SimpleFeature) mask).getDefaultGeometry());
		ranPointBuilder.setNumPoints(1);
		Geometry ranP = ranPointBuilder.getGeometry();
		return ranP;
	}

}
