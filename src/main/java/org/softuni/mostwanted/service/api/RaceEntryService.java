package org.softuni.mostwanted.service.api;

import org.softuni.mostwanted.model.dto.xml.RaceEntryXMLImportDto;
import org.softuni.mostwanted.model.entity.RaceEntry;

public interface RaceEntryService {
    Integer create(RaceEntryXMLImportDto raceEntryXMLImportDto);

    RaceEntry findById(Integer id);

    void save(RaceEntry raceEntry);
}
