package org.softuni.mostwanted.service.impl;

import org.softuni.mostwanted.model.dto.json.DistrictJSONImportDto;
import org.softuni.mostwanted.model.entity.District;
import org.softuni.mostwanted.model.entity.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repository.DistrictRepository;
import org.softuni.mostwanted.service.api.DistrictService;
import org.softuni.mostwanted.service.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(noRollbackFor = Exception.class)
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final TownService townService;
    private final ModelParser parser;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository,
                               TownService townService,
                               ModelParser parser) {
        this.districtRepository = districtRepository;
        this.townService = townService;
        this.parser = parser;
    }

    @Override
    public void create(DistrictJSONImportDto districtJSONImportDto) {
        District district = this.findByName(districtJSONImportDto.getName());
        if (district!=null) {
            throw new IllegalArgumentException();
        } else {
            Town town = this.townService.findByName(districtJSONImportDto.getTownName());
            District districtToSave = this.parser.convert(districtJSONImportDto, District.class);
            districtToSave.setTown(town);
            town.addDistrict(districtToSave);
            this.districtRepository.saveAndFlush(districtToSave);
            this.townService.save(town);
        }
    }

    @Override
    public District findByNameAndTownName(String districtName, String townName) {
        return this.districtRepository.findByNameAndTownName(districtName, townName);
    }

    @Override
    public District findByName(String districtName) {
        return this.districtRepository.findByName(districtName);
    }

    @Override
    public void save(District district) {
        this.districtRepository.saveAndFlush(district);
    }
}
