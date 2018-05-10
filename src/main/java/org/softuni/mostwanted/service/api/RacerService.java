package org.softuni.mostwanted.service.api;

import org.softuni.mostwanted.model.dto.json.RacerJSONImportDto;
import org.softuni.mostwanted.model.entity.Racer;

public interface RacerService {
    void create(RacerJSONImportDto racerJSONImportDto);

    Racer findByName(String name);

    Racer findByNameAndHomeTownName(String racerName, String townName);

    void save(Racer racer);
}
