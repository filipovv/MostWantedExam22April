package org.softuni.mostwanted.model.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceWrapperXMLImportDto {

    @XmlElement(name = "race")
    private List<RaceXMLImportDto> races;

    public RaceWrapperXMLImportDto() {
    }

    public List<RaceXMLImportDto> getRaces() {
        return races;
    }

    public void setRaces(List<RaceXMLImportDto> races) {
        this.races = races;
    }
}
