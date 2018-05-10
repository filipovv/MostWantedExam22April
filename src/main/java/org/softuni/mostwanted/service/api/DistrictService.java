package org.softuni.mostwanted.service.api;

import org.softuni.mostwanted.model.dto.json.DistrictJSONImportDto;
import org.softuni.mostwanted.model.entity.District;

public interface DistrictService {
    void create(DistrictJSONImportDto districtJSONImportDto);

    District findByNameAndTownName(String districtName, String townName);

    District findByName(String districtName);

    void save(District district);
}
