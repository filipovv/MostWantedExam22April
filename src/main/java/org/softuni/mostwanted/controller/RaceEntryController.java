package org.softuni.mostwanted.controller;

import org.softuni.mostwanted.model.dto.xml.RaceEntryWrapperXMLImportDto;
import org.softuni.mostwanted.model.dto.xml.RaceEntryXMLImportDto;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.service.api.RaceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceEntryController {
    private final RaceEntryService raceEntryService;
    private final Parser xmlParser;

    @Autowired
    public RaceEntryController(RaceEntryService raceEntryService,
                               @Qualifier(value = "XMLParser") Parser xmlParser) {
        this.raceEntryService = raceEntryService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        RaceEntryWrapperXMLImportDto raceEntryWrapperXMLImportDto = this.xmlParser.read(RaceEntryWrapperXMLImportDto.class, jsonContent);
        for (RaceEntryXMLImportDto raceEntryXMLImportDto : raceEntryWrapperXMLImportDto.getRaceEntries()) {
            if (ValidationUtil.isValid(raceEntryXMLImportDto)) {
                try {
                    Integer entryId = this.raceEntryService.create(raceEntryXMLImportDto);
                    sb.append(String.format("Successfully imported %s – %d.%n", "RaceEntry", entryId))
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
