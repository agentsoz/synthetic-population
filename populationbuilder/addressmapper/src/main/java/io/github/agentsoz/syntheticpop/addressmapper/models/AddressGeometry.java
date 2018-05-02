package io.github.agentsoz.syntheticpop.addressmapper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Geometry POJO for ijts Geometry in building SimpleFeature
 *
 * @author wniroshan
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressGeometry {
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
