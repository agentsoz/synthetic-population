package io.github.agentsoz.syntheticpop.addressmapper.models;

/*-
 * #%L
 * Synthetic Population Construction for Australia
 * %%
 * Copyright (C) 2016 - 2019 by its authors. See AUTHORS file.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

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

