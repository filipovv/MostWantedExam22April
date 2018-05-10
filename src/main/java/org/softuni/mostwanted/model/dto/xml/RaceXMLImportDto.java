package org.softuni.mostwanted.model.dto.xml;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceXMLImportDto {

    @XmlElement(name = "laps")
    @NotNull
    private Integer laps;

    @XmlElement(name = "district-name")
    @NotNull
    private String districtName;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<Integer> entries;

    public RaceXMLImportDto() {
    }

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<Integer> getEntries() {
        return entries;
    }

    public void setEntries(List<Integer> entries) {
        this.entries = entries;
    }
}
