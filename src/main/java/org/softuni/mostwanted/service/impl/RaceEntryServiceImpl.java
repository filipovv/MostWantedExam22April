package org.softuni.mostwanted.service.impl;

import org.softuni.mostwanted.model.dto.xml.MostWantedEntryXMLExportDto;
import org.softuni.mostwanted.model.dto.xml.MostWantedWrapperXMLExportDto;
import org.softuni.mostwanted.model.dto.xml.MostWantedXMLExportDto;
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

import java.util.Comparator;
import java.util.List;

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

    @Override
    public MostWantedWrapperXMLExportDto getMostWantedRacer() {
        MostWantedWrapperXMLExportDto mostWantedWrapperDto = new MostWantedWrapperXMLExportDto();
        List<RaceEntry> raceEntries = this.raceEntryRepository.getMostWantedRacerEntries();
        MostWantedXMLExportDto mostWantedRacerDto = new MostWantedXMLExportDto();
        mostWantedRacerDto.setName(raceEntries.get(0).getRacer().getName());
        for (RaceEntry raceEntry : raceEntries) {
            MostWantedEntryXMLExportDto raceEntryDto = new MostWantedEntryXMLExportDto();
            Car car = raceEntry.getCar();
            String carAsString = String.format("%s %s @ %d",
                    car.getBrand(), car.getModel(), car.getYearOfProduction());
            raceEntryDto.setFinishTime(raceEntry.getFinishTime().doubleValue());
            raceEntryDto.setCar(carAsString);
            mostWantedRacerDto.getEntries().add(raceEntryDto);
        }
        mostWantedRacerDto.getEntries().sort(Comparator.comparing(MostWantedEntryXMLExportDto::getFinishTime));
        mostWantedWrapperDto.setRacer(mostWantedRacerDto);
        return mostWantedWrapperDto;
    }
}
