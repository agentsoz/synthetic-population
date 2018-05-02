package io.github.agentsoz.syntheticpop.addressmapper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Property POJO for properties in building SimpleFeature
 *
 * @author wniroshan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressProperty {

    private String PFI; //Persistent Feature Identifier uniquely identifies each address record
    private String EZI_ADD; // e.g., "14 FAIRWAY COURT BUNDOORA 3083"
    private String STATE; // e.g., "VIC"
    private String POSTCODE; // e.g., "3083"
    private String LGA_CODE; // e.g., 373
    private String LOCALITY; // e.g., "BUNDOORA"
    private String MESH_BLOCK; //e.g. Mesh block ID used in Vicmap data
    private String ADD_CLASS; // e.g., "S", or "M"
    private String BEDD;// Bedrooms
    private String TENLLD;// Tenure and landlord type
    private String STRD;// Dweling structure
    private String BUILDING_TYPE;// residential or non residential
    private String CENSUS_HHSIZE;
    private String SA1_MAIN16;// SA1 code
    private String ABS_MB16; //ABS 2016 mesh block id
    private List<String> HOUSEHOLD_ID;

    public AddressProperty() {
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

    public String getPFI() {
        return PFI;
    }

    @JsonProperty("PFI")
    public void setPFI(String PFI) {
        this.PFI = PFI;
    }

    public String getMESH_BLOCK() {
        return MESH_BLOCK;
    }

    @JsonProperty("MESH_BLOCK")
    public void setMESH_BLOCK(String MESH_BLOCK) {
        this.MESH_BLOCK = MESH_BLOCK;
    }

    public String getSA1_MAIN16() {
        return SA1_MAIN16;
    }

    @JsonProperty("SA1_MAIN16")
    public void setSA1_MAIN16(String SA1_MAIN16) {
        this.SA1_MAIN16 = SA1_MAIN16;
    }

    public String getABS_MB16() {
        return ABS_MB16;
    }

    @JsonProperty("ABS_MB16")
    public void setABS_MB16(String ABS_MB16) {
        this.ABS_MB16 = ABS_MB16;
    }

    public List<String> getHOUSEHOLD_ID() {
        return HOUSEHOLD_ID;
    }

    @JsonProperty("HOUSEHOLD_ID")
    public void setHOUSEHOLD_ID(List<String> HOUSEHOLD_ID) {
        this.HOUSEHOLD_ID = HOUSEHOLD_ID;
    }
}
