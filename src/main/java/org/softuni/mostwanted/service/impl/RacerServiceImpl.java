package org.softuni.mostwanted.service.impl;

import org.softuni.mostwanted.model.dto.json.RacerJSONImportDto;
import org.softuni.mostwanted.model.entity.Racer;
import org.softuni.mostwanted.model.entity.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repository.RacerRepository;
import org.softuni.mostwanted.service.api.RacerService;
import org.softuni.mostwanted.service.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(noRollbackFor = Exception.class)
public class RacerServiceImpl implements RacerService {
    private final RacerRepository racerRepository;
    private final TownService townService;
    private final ModelParser parser;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository,
                            TownService townService,
                            ModelParser parser) {
        this.racerRepository = racerRepository;
        this.townService = townService;
        this.parser = parser;
    }

    @Override
    public void create(RacerJSONImportDto racerJSONImportDto) {
        Racer racer = this.findByNameAndHomeTownName(racerJSONImportDto.getName(), racerJSONImportDto.getHomeTownName());
        if (racer!=null) {
            throw new IllegalArgumentException();
        } else {
            Town town = this.townService.findByName(racerJSONImportDto.getHomeTownName());
            Racer racerToSave = this.parser.convert(racerJSONImportDto, Racer.class);
            racerToSave.setHomeTown(town);
            town.addRacer(racerToSave);
            this.townService.save(town);
            this.racerRepository.saveAndFlush(racerToSave);
        }
    }

    @Override
    public Racer findByName(String name) {
        return this.racerRepository.findByName(name);
    }

    @Override
    public Racer findByNameAndHomeTownName(String racerName, String townName) {
        return this.racerRepository.findByNameAndHomeTownName(racerName, townName);
    }

    public void save(Racer racer) {
        this.racerRepository.saveAndFlush(racer);
    }
}
