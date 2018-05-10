package org.softuni.mostwanted.controller;

import org.softuni.mostwanted.model.dto.json.RacerJSONImportDto;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.service.api.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RacerController {
    private final RacerService racerService;
    private final Parser jsonParser;

    @Autowired
    public RacerController(RacerService racerService,
                           @Qualifier(value = "JSONParser") Parser jsonParser) {
        this.racerService = racerService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        RacerJSONImportDto[] racerJSONImportDtos = this.jsonParser.read(RacerJSONImportDto[].class, jsonContent);
        for (RacerJSONImportDto racerJSONImportDto : racerJSONImportDtos) {
            if (ValidationUtil.isValid(racerJSONImportDto)) {
                try {
                    this.racerService.create(racerJSONImportDto);
                    sb.append(String.format("Successfully imported %s – %s.%n", "Racer", racerJSONImportDto.getName()))
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
}
