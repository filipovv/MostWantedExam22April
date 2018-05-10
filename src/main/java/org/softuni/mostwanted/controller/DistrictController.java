package org.softuni.mostwanted.controller;

import org.softuni.mostwanted.model.dto.json.DistrictJSONImportDto;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.service.api.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class DistrictController {
    private final DistrictService districtService;
    private final Parser jsonParser;

    @Autowired
    public DistrictController(DistrictService districtService,
                              @Qualifier(value = "JSONParser") Parser jsonParser) {
        this.districtService = districtService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        DistrictJSONImportDto[] districtJSONImportDtos = this.jsonParser.read(DistrictJSONImportDto[].class, jsonContent);
        for (DistrictJSONImportDto districtJSONImportDto : districtJSONImportDtos) {
            if (ValidationUtil.isValid(districtJSONImportDto)) {
                try {
                    this.districtService.create(districtJSONImportDto);
                    sb.append(String.format("Successfully imported %s – %s.%n", "District", districtJSONImportDto.getName()))
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
        return sb.toString();    }
}
