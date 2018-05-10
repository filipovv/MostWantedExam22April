package org.softuni.mostwanted.controller;

import org.softuni.mostwanted.model.dto.json.CarJSONImportDto;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.service.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class CarController {
    private final CarService carService;
    private final Parser jsonParser;

    @Autowired
    public CarController(CarService carService,
                         @Qualifier(value = "JSONParser") Parser jsonParser) {
        this.carService = carService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        CarJSONImportDto[] carJSONImportDtos = this.jsonParser.read(CarJSONImportDto[].class, jsonContent);
        for (CarJSONImportDto carJSONImportDto : carJSONImportDtos) {
            if (ValidationUtil.isValid(carJSONImportDto)) {
                try {
                    this.carService.create(carJSONImportDto);
                    sb.append(String.format("Successfully imported %s – %s %s @ %d.%n", "Car", carJSONImportDto.getBrand(), carJSONImportDto.getModel(), carJSONImportDto.getYearOfProduction()))
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
