package org.softuni.mostwanted.service.api;

import org.softuni.mostwanted.model.dto.json.CarJSONImportDto;
import org.softuni.mostwanted.model.entity.Car;

public interface CarService {
    void create(CarJSONImportDto carJSONImportDto);

    Car findByBrandAndModelAndYearOfProductionAndRacerName(String brand, String model, Integer yearOfProduction, String racerName);

    Car findById(Integer id);

    void save(Car car);
}
