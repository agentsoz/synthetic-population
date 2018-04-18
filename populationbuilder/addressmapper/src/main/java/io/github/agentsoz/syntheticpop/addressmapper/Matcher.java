package io.github.agentsoz.syntheticpop.addressmapper;

import io.github.agentsoz.syntheticpop.geo.FeatureProcessing;
import io.github.agentsoz.syntheticpop.util.LIFOLinkedHashSet;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.filter.text.cql2.CQL;
import org.geotools.filter.text.cql2.CQLException;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.Filter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.zip.DataFormatException;

/**
 * @author wniroshan 18 Apr 2018
 */
class Matcher {

    final private String saLookupKey;
    final private String addressLookupKey;
    final private FeatureProcessing featProcessor;

    final private HashSet<String> blackList;
    final private HashMap<String, SimpleFeature> whiteList;
    private LIFOLinkedHashSet<SimpleFeature> recentMatches = new LIFOLinkedHashSet<>(15);

    private int lookup = 0, contain = 0, blEliminate = 0, wlFound = 0;

    Matcher(FeatureProcessing featureProcessing, String saLookupKey, String addressLookupKey) {
        this.saLookupKey = saLookupKey;
        this.addressLookupKey = addressLookupKey;
        this.featProcessor = featureProcessing;
        blackList = new HashSet<>();
        whiteList = new HashMap<>();
    }

    SimpleFeature findSAMeshBlock(SimpleFeature address, SimpleFeatureCollection saMeshBlocks) throws CQLException, DataFormatException {
        SimpleFeature mb = null;
        if (!isInBlackList(address)) { //Eliminate quickly
            if (isInWhiteList(address)) {
                mb = getSAMeshBlockFromWhiteList(address);
                wlFound++;
            } else {
                mb = lookupMatchingSAMeshBlock(address, saMeshBlocks);
                if (mb == null) {
                    mb = locateContainingSAMeshBlockOfAddress(address, saMeshBlocks);
                    if (mb == null) {
                        blackList.add(address.getAttribute(addressLookupKey).toString());
                    } else {
                        whiteList.put(address.getAttribute(addressLookupKey).toString(), mb);
                        contain++;
                    }
                } else {
                    whiteList.put(address.getAttribute(addressLookupKey).toString(), mb);
                    lookup++;
                }
            }
        } else {
            blEliminate++;
        }
        return mb;
    }

    String printStats() {
        return "Final whitelist: " + whiteList.size() + " Successful lookup: " + lookup + " Successful polygon search: " + contain + " Whitelist detections: " + wlFound + " Final blacklist: " + blackList
                .size() + " Blacklist eliminations: " + blEliminate;
    }

    /**
     * Looks up the matching SA mesh block using address' mesh block ID
     *
     * @param address         The address feature
     * @param allSAMeshBlocks Collection of all SA mesh blocks
     * @return The matching SA mesh block if found. Else returns null
     * @throws CQLException If filter creation failed
     */
    private SimpleFeature lookupMatchingSAMeshBlock(SimpleFeature address, SimpleFeatureCollection allSAMeshBlocks) throws CQLException {
        Filter filter = CQL.toFilter(saLookupKey + "=" + address.getAttribute(addressLookupKey));

        SimpleFeatureCollection matchingMeshBlocks = allSAMeshBlocks.subCollection(filter);
        SimpleFeatureIterator matchingMeshBlockItr = matchingMeshBlocks.features();

        SimpleFeature mb = null;
        while (matchingMeshBlockItr.hasNext()) {
            mb = matchingMeshBlockItr.next();
        }
        matchingMeshBlockItr.close();
        return mb;
    }

    /**
     * Finds the SA mesh block that address is contained geographically
     *
     * @param address      The address
     * @param saMeshBlocks The collection of SA mesh blocks
     * @return The SA mesh block that the address is in. If the mesh block is not found returns null
     * @throws DataFormatException When searching for the address within a polygon.
     */
    private SimpleFeature locateContainingSAMeshBlockOfAddress(SimpleFeature address,
                                                               SimpleFeatureCollection saMeshBlocks) throws DataFormatException {


        SimpleFeature matchingMeshBlock = null;

        if (!recentMatches.isEmpty()) {
            matchingMeshBlock = featProcessor.getContainingPolygon(recentMatches, address);
        }
        if (matchingMeshBlock == null) {
            matchingMeshBlock = featProcessor.getContainingPolygon(saMeshBlocks, address);
        }

        /*
         * Going through all the polygons every time we check a point is
         * very time consuming. So we keep a set of recently matched
         * polygons to see if the new point is within one of these polygons.
         * Rationale is points are likely to be clustered together
         * spatially. So we don't have to check full polygons list unless
         * the new point is outside the current cluster. Every time we find
         * a matching polygon we move it to the top of the recently matched
         * set. Size of recently matched polygon is a set to 15 (decided arbitrarily).
         */
        if (matchingMeshBlock != null) {
            recentMatches.add(matchingMeshBlock);
        }
        return matchingMeshBlock;

    }

    /**
     * Checks whether the address is already known to be not in any of the SA mesh blocks
     *
     * @param address The address
     * @return True is it is not in a SA mesh block, else false
     */
    private boolean isInBlackList(SimpleFeature address) {
        return blackList.contains(address.getAttribute(addressLookupKey).toString());
    }

    /**
     * Checks whether the matching mesh block of the address is already known
     *
     * @param address The address
     * @return True is the address's mesh block is already known
     */
    private boolean isInWhiteList(SimpleFeature address) {
        return whiteList.containsKey(address.getAttribute(addressLookupKey).toString());
    }

    /**
     * Return the already matched SA mesh block of the specified address
     *
     * @param address The address
     * @return SA mesh block
     */
    private SimpleFeature getSAMeshBlockFromWhiteList(SimpleFeature address) {
        return whiteList.get(address.getAttribute(addressLookupKey).toString());
    }

}
