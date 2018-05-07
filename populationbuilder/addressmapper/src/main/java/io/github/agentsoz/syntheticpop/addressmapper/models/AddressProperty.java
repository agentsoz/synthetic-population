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
    private String SA1_MAIN16;// SA1 code
    private List<String> HOUSEHOLD_ID;

    public AddressProperty() {
    }

    public String getEZI_ADD() {
        return EZI_ADD;
    }

    @JsonProperty("EZI_ADD")
    public void setEZI_ADD(String eZI_ADD) {
        EZI_ADD = eZI_ADD;
    }

    public String getPFI() {
        return PFI;
    }

    @JsonProperty("PFI")
    public void setPFI(String PFI) {
        this.PFI = PFI;
    }

}
