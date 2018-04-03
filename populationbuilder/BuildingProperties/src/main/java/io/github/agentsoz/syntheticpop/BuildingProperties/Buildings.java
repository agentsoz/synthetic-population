package io.github.agentsoz.syntheticpop.BuildingProperties;

import io.github.agentsoz.syntheticpop.geo.ShapefileGeoFeatureReader;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.geotools.data.DataUtilities;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.geojson.feature.FeatureJSON;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.util.List;

/**
 * @author Bhagya N. Wickramasinghe
 */
public class Buildings {

    /**
     * Reads building shapes from input file
     *
     * @param inputFile        Path of file
     * @param targetCRS        Converts geometry to this coordinate reference system when reading
     * @return SimpleFeatureCollection object of all the buildings in given file
     * @throws IOException
     */
    static SimpleFeatureCollection getBuildings(Path inputFile,
                                                String filterByProperty,
                                                String[] filterByValues,
                                                CoordinateReferenceSystem targetCRS) throws IOException {
        SimpleFeatureCollection buildingsCollection = null;
        ShapefileGeoFeatureReader buildingPolygonsReader = new ShapefileGeoFeatureReader();
        buildingPolygonsReader.loadFeatures(inputFile, targetCRS);
        buildingsCollection = DataUtilities.simple(buildingPolygonsReader.getFeatures());

        return buildingsCollection;
    }

    /**
     * Converts building SimpleFeature to a Building POJO
     *
     * @param building SimpleFeature to be converted
     * @return Building POJO after converting
     * @throws IOException
     */
    static Building map2POJO(SimpleFeature building) throws IOException {
        FeatureJSON fjson = new FeatureJSON();
        StringWriter writer = new StringWriter();
        fjson.writeFeature(building, writer);
        String json = writer.toString();
        ObjectMapper mapper = new ObjectMapper();
        Building pojoBuilding = mapper.readValue(json, Building.class);
        return pojoBuilding;
    }

}

/**
 * Building POJO - Used to convert building SimpleFeature
 *
 * @author bhagya
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Building {

    private BuildingProperty properties;
    private BuildingGeometry geometry;

    public BuildingProperty getProperties() {
        return properties;
    }

    public void setProperties(BuildingProperty buildingProperties) {
        this.properties = buildingProperties;
    }

    public BuildingGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(BuildingGeometry geometry) {
        this.geometry = geometry;
    }

}

/**
 * Geometry POJO for ijts Geometry in building SimpleFeature
 *
 * @author bhagya
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class BuildingGeometry {
    private String type;
    private List coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List coordinates) {
        this.coordinates = coordinates;
    }

}

/**
 * Property POJO for properties in building SimpleFeature
 *
 * @author bhagya
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class BuildingProperty {

    private String PFI; //Persistent Feature Identifier uniquely identifies each address record
    private String EZI_ADD; // e.g., "14 FAIRWAY COURT BUNDOORA 3083"
    private String STATE; // e.g., "VIC"
    private String POSTCODE; // e.g., "3083"
    private String LGA_CODE; // e.g., 373
    private String LOCALITY; // e.g., "BUNDOORA"
    private String ADD_CLASS; // e.g., "S", or "M"
    private String SA1_7DIG11 = ""; // SA1 code e.g., "2120241"
    private String BEDD;// Bedrooms
    private String TENLLD;// Tenure and landlord type
    private String STRD;// Dweling structure
    private String BUILDING_TYPE;// residential or non residential
    private String CENSUS_HHSIZE;
    private String SA1_MAINCODE_2011;// SA1 longer code
    private String DUPLICATE_OF; //PFI of the duplicate building with the same EZI_ADD

    public BuildingProperty() {
    }

    public String getBEDD() {
        return BEDD;
    }

    @JsonProperty("BEDD")
    public void setBEDD(String bEDD) {
        BEDD = bEDD;
    }

    public String getTENLLD() {
        return TENLLD;
    }

    @JsonProperty("TENLLD")
    public void setTENLLD(String tENLLD) {
        TENLLD = tENLLD;
    }

    public String getSTRD() {
        return STRD;
    }

    @JsonProperty("STRD")
    public void setSTRD(String sTRD) {
        STRD = sTRD;
    }

    public String getBUILDING_TYPE() {
        return BUILDING_TYPE;
    }

    @JsonProperty("BUILDING_TYPE")
    public void setBUILDING_TYPE(String bUILDING_TYPE) {
        BUILDING_TYPE = bUILDING_TYPE;
    }

    public String getCENSUS_HHSIZE() {
        return CENSUS_HHSIZE;
    }

    @JsonProperty("CENSUS_HHSIZE")
    public void setCENSUS_HHSIZE(String cENSUS_HHSIZE) {
        CENSUS_HHSIZE = cENSUS_HHSIZE;
    }

    public String getSA1_MAINCODE_2011() {
        return SA1_MAINCODE_2011;
    }

    @JsonProperty("SA1_MAINCODE_2011")
    public void setSA1_MAINCODE_2011(String sA1_MAINCODE_2011) {
        SA1_MAINCODE_2011 = sA1_MAINCODE_2011;
    }

    public String getEZI_ADD() {
        return EZI_ADD;
    }

    @JsonProperty("EZI_ADD")
    public void setEZI_ADD(String eZI_ADD) {
        EZI_ADD = eZI_ADD;
    }

    public String getSTATE() {
        return STATE;
    }

    @JsonProperty("STATE")
    public void setSTATE(String sTATE) {
        STATE = sTATE;
    }

    public String getPOSTCODE() {
        return POSTCODE;
    }

    @JsonProperty("POSTCODE")
    public void setPOSTCODE(String pOSTCODE) {
        POSTCODE = pOSTCODE;
    }

    public String getLGA_CODE() {
        return LGA_CODE;
    }

    @JsonProperty("LGA_CODE")
    public void setLGA_CODE(String lGA_CODE) {
        LGA_CODE = lGA_CODE;
    }

    public String getLOCALITY() {
        return LOCALITY;
    }

    @JsonProperty("LOCALITY")
    public void setLOCALITY(String lOCALITY) {
        LOCALITY = lOCALITY;
    }

    public String getADD_CLASS() {
        return ADD_CLASS;
    }

    @JsonProperty("ADD_CLASS")
    public void setADD_CLASS(String aDD_CLASS) {
        ADD_CLASS = aDD_CLASS;
    }

    public String getSA1_7DIG11() {
        return SA1_7DIG11;
    }

    @JsonProperty("SA1_7DIG11")
    public void setSA1_7DIG11(String sA1_7DIG11) {
        SA1_7DIG11 = sA1_7DIG11;
    }

    public String getDUPLICATE_OF() {
        return DUPLICATE_OF;
    }

    @JsonProperty("DUPLICATE_OF")
    public void setDUPLICATE(String DUPLICATE_OF) {
        this.DUPLICATE_OF = DUPLICATE_OF;
    }

    public String getPFI() {
        return PFI;
    }

    @JsonProperty("PFI")
    public void setPFI(String PFI) {
        this.PFI = PFI;
    }
}