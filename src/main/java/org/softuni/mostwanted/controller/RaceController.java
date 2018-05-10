package org.softuni.mostwanted.controller;

import org.softuni.mostwanted.model.dto.xml.RaceWrapperXMLImportDto;
import org.softuni.mostwanted.model.dto.xml.RaceXMLImportDto;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.service.api.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceController {
    private final RaceService raceService;
    private final Parser xmlParser;

    @Autowired
    public RaceController(RaceService raceService,
                          @Qualifier(value = "XMLParser") Parser xmlParser) {
        this.raceService = raceService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        RaceWrapperXMLImportDto raceWrapperXMLImportDto = this.xmlParser.read(RaceWrapperXMLImportDto.class, jsonContent);
        for (RaceXMLImportDto raceEntryXMLImportDto : raceWrapperXMLImportDto.getRaces()) {
            if (ValidationUtil.isValid(raceEntryXMLImportDto)) {
                try {
                    Integer entryId = this.raceService.create(raceEntryXMLImportDto);
                    sb.append(String.format("Successfully imported %s – %d.%n", "Race", entryId))
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
