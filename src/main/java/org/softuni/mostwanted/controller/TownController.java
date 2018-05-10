package org.softuni.mostwanted.controller;

import org.softuni.mostwanted.model.dto.json.TownJSONImportDto;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.service.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class TownController {
    private final TownService townService;
    private final Parser jsonParser;

    @Autowired
    public TownController(TownService townService,
                          @Qualifier(value = "JSONParser") Parser jsonParser) {
        this.townService = townService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        TownJSONImportDto[] townJSONImportDtos = this.jsonParser.read(TownJSONImportDto[].class, jsonContent);
        for (TownJSONImportDto townJSONImportDto : townJSONImportDtos) {
            if (ValidationUtil.isValid(townJSONImportDto)) {
                try {
                    this.townService.create(townJSONImportDto);
                    sb.append(String.format("Successfully imported %s – %s.%n", "Town", townJSONImportDto.getName()))
                            .append(System.lineSeparator());
                } catch (IllegalArgumentException e) {
                    sb.append("Error: Duplicate Data!")
                            .append(System.lineSeparator());
                }
            } else {
                sb.append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String exportRacingTowns() throws IOException, JAXBException {
        return this.jsonParser.write(this.townService.findRacingTowns());
    }
}
