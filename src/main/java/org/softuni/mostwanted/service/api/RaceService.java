package org.softuni.mostwanted.service.api;

import org.softuni.mostwanted.model.dto.xml.RaceXMLImportDto;
import org.softuni.mostwanted.model.entity.Race;

public interface RaceService {
    Integer create(RaceXMLImportDto raceEntryXMLImportDto);

    void save(Race race);
}
