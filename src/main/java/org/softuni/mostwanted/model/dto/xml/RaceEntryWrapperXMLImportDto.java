package org.softuni.mostwanted.model.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryWrapperXMLImportDto {
    @XmlElement(name = "race-entry")
    private List<RaceEntryXMLImportDto> raceEntries;

    public RaceEntryWrapperXMLImportDto() {
    }

    public List<RaceEntryXMLImportDto> getRaceEntries() {
        return raceEntries;
    }

    public void setRaceEntries(List<RaceEntryXMLImportDto> raceEntries) {
        this.raceEntries = raceEntries;
    }
}
