package org.softuni.mostwanted.model.dto.xml;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "racer")
@XmlAccessorType(XmlAccessType.FIELD)
public class MostWantedXMLExportDto {
    @XmlAttribute
    private String name;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<MostWantedEntryXMLExportDto> entries;

    public MostWantedXMLExportDto() {
        this.entries = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MostWantedEntryXMLExportDto> getEntries() {
        return entries;
    }

    public void setEntries(List<MostWantedEntryXMLExportDto> entries) {
        this.entries = entries;
    }
}
