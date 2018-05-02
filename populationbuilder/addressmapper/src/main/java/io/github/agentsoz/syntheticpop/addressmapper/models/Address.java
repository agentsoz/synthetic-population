package io.github.agentsoz.syntheticpop.addressmapper.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.agentsoz.syntheticpop.addressmapper.models.AddressGeometry;
import io.github.agentsoz.syntheticpop.addressmapper.models.AddressProperty;

import java.util.List;

/**
 * Address POJO - Used to convert building SimpleFeature
 *
 * @author bhagya
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

    private AddressProperty properties;
    private AddressGeometry geometry;

    public AddressProperty getProperties() {
        return properties;
    }

    public void setProperties(AddressProperty buildingProperties) {
        this.properties = buildingProperties;
    }

    public AddressGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(AddressGeometry geometry) {
        this.geometry = geometry;
    }

}

