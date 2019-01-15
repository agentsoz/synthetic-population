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
    private String ABS_MB16; //Mesh block code in ABS census geographical data 2016
    private String VICMAP_MB;//Mesh block code in Vicmap address file

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

    public String getABS_MB16() {
        return ABS_MB16;
    }

    @JsonProperty("ABS_MB16")
    public void setABS_MB16(String ABS_MB16) {
        this.ABS_MB16 = ABS_MB16;
    }

    public String getVICMAP_MB() {
        return VICMAP_MB;
    }

    @JsonProperty("MESH_BLOCK")
    public void setVICMAP_MB(String VICMAP_MB) {
        this.VICMAP_MB = VICMAP_MB;
    }
}
