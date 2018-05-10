package org.softuni.mostwanted.service.api;

import org.softuni.mostwanted.model.dto.json.TownJSONExportDto;
import org.softuni.mostwanted.model.dto.json.TownJSONImportDto;
import org.softuni.mostwanted.model.entity.Town;

import java.util.List;

public interface TownService {
    void create(TownJSONImportDto townJSONImportDto);

    Town findByName(String name);

    void save(Town town);

    List<TownJSONExportDto> findRacingTowns();
}
