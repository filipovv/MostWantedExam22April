package org.softuni.mostwanted.service.impl;

import org.softuni.mostwanted.model.dto.json.TownJSONExportDto;
import org.softuni.mostwanted.model.dto.json.TownJSONImportDto;
import org.softuni.mostwanted.model.entity.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repository.TownRepository;
import org.softuni.mostwanted.service.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(noRollbackFor = Exception.class)
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final ModelParser modelParser;

    @Autowired
    public TownServiceImpl(TownRepository townRepository,
                           ModelParser modelParser) {
        this.townRepository = townRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(TownJSONImportDto townJSONImportDto) {
        Town town = this.findByName(townJSONImportDto.getName());
        if (town!=null) {
            throw new IllegalArgumentException();
        } {
            this.townRepository.saveAndFlush(this.modelParser.convert(townJSONImportDto, Town.class));
        }
    }

    @Override
    public Town findByName(String name) {
        return this.townRepository.findByName(name);
    }

    @Override
    public void save(Town town) {
        this.townRepository.saveAndFlush(town);
    }

    @Override
    public List<TownJSONExportDto> findRacingTowns() {
        return this.townRepository.findRacingTowns();
    }
}
