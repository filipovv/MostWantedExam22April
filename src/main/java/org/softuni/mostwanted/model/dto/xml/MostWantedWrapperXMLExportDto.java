package org.softuni.mostwanted.model.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "most-wanted")
@XmlAccessorType(XmlAccessType.FIELD)
public class MostWantedWrapperXMLExportDto {
    @XmlElement(name = "racer")
    private MostWantedXMLExportDto racer;

    public MostWantedWrapperXMLExportDto() {
    }

    public MostWantedXMLExportDto getRacer() {
        return racer;
    }

    public void setRacer(MostWantedXMLExportDto racer) {
        this.racer = racer;
    }
}
