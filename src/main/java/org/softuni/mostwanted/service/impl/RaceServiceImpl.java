package org.softuni.mostwanted.service.impl;

import org.softuni.mostwanted.model.dto.xml.RaceXMLImportDto;
import org.softuni.mostwanted.model.entity.District;
import org.softuni.mostwanted.model.entity.Race;
import org.softuni.mostwanted.model.entity.RaceEntry;
import org.softuni.mostwanted.repository.RaceRepository;
import org.softuni.mostwanted.service.api.DistrictService;
import org.softuni.mostwanted.service.api.RaceEntryService;
import org.softuni.mostwanted.service.api.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(noRollbackFor = Exception.class)
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;
    private final DistrictService districtService;
    private final RaceEntryService raceEntryService;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository,
                           DistrictService districtService,
                           RaceEntryService raceEntryService) {
        this.raceRepository = raceRepository;
        this.districtService = districtService;
        this.raceEntryService = raceEntryService;
    }

    @Override
    public Integer create(RaceXMLImportDto raceEntryXMLImportDto) {
        District district = this.districtService.findByName(raceEntryXMLImportDto.getDistrictName());
        Integer idToReturn = null;
        if (district == null) {
            throw new IllegalArgumentException();
        } else {
            Race race = new Race();
            race.setLaps(raceEntryXMLImportDto.getLaps());
            race.setDistrict(district);
            Set<RaceEntry> raceEntries = new HashSet<>();
            for (Integer integer : raceEntryXMLImportDto.getEntries()) {
                raceEntries.add(this.raceEntryService.findById(integer));
            }
            race.setEntries(raceEntries);
            idToReturn = this.raceRepository.saveAndFlush(race).getId();
        }
        return idToReturn;
    }

    @Override
    public void save(Race race) {
        this.raceRepository.saveAndFlush(race);
    }
}
