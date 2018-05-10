package org.softuni.mostwanted.service.impl;

import org.softuni.mostwanted.model.dto.xml.RaceEntryXMLImportDto;
import org.softuni.mostwanted.model.entity.Car;
import org.softuni.mostwanted.model.entity.RaceEntry;
import org.softuni.mostwanted.model.entity.Racer;
import org.softuni.mostwanted.repository.RaceEntryRepository;
import org.softuni.mostwanted.service.api.CarService;
import org.softuni.mostwanted.service.api.RaceEntryService;
import org.softuni.mostwanted.service.api.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(noRollbackFor = Exception.class)
public class RaceEntryServiceImpl implements RaceEntryService {
    private final RaceEntryRepository raceEntryRepository;
    private final CarService carService;
    private final RacerService racerService;

    @Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository,
                                CarService carService,
                                RacerService racerService) {
        this.raceEntryRepository = raceEntryRepository;
        this.carService = carService;
        this.racerService = racerService;
    }

    @Override
    public Integer create(RaceEntryXMLImportDto raceEntryXMLImportDto) {
        Car car = this.carService.findById(raceEntryXMLImportDto.getCarId());
        Racer racer = this.racerService.findByName(raceEntryXMLImportDto.getRacer());
        Integer idToReturn = null;
        if (car == null || racer == null) {
            throw new IllegalArgumentException();
        } else {
            RaceEntry raceEntry = new RaceEntry();
            raceEntry.setHasFinished(raceEntryXMLImportDto.isHasFinished());
            raceEntry.setFinishTime(raceEntryXMLImportDto.getFinishTime().intValue());
            raceEntry.setCar(car);
            raceEntry.setRacer(racer);
            idToReturn = this.raceEntryRepository.saveAndFlush(raceEntry).getId();
        }

        return idToReturn;
    }

    public RaceEntry findById(Integer id) {
        return this.raceEntryRepository.getOne(id);
    }

    @Override
    public void save(RaceEntry raceEntry) {
        this.raceEntryRepository.saveAndFlush(raceEntry);
    }
}
