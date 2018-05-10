package org.softuni.mostwanted.service.impl;

import org.softuni.mostwanted.model.dto.json.CarJSONImportDto;
import org.softuni.mostwanted.model.entity.Car;
import org.softuni.mostwanted.model.entity.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repository.CarRepository;
import org.softuni.mostwanted.service.api.CarService;
import org.softuni.mostwanted.service.api.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(noRollbackFor = Exception.class)
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelParser parser;
    private final RacerService racerService;

    @Autowired
    public CarServiceImpl(CarRepository carRepository,
                          ModelParser parser,
                          RacerService racerService) {
        this.carRepository = carRepository;
        this.parser = parser;
        this.racerService = racerService;
    }

    @Override
    public void create(CarJSONImportDto carJSONImportDto) {
        Car car = this.findByBrandAndModelAndYearOfProductionAndRacerName(carJSONImportDto.getBrand(), carJSONImportDto.getModel(), carJSONImportDto.getYearOfProduction(), carJSONImportDto.getRacerName());
        if (car!=null) {
            throw new IllegalArgumentException();
        } else {
            Racer racer = this.racerService.findByName(carJSONImportDto.getRacerName());
            car = this.parser.convert(carJSONImportDto, Car.class);
            car.setRacer(racer);
            racer.addCar(car);
            this.carRepository.save(car);
            this.racerService.save(racer);
        }
    }

    @Override
    public Car findByBrandAndModelAndYearOfProductionAndRacerName(String brand, String model, Integer yearOfProduction, String racerName) {
        return this.carRepository.findByBrandAndModelAndYearOfProductionAndRacerName(brand, model, yearOfProduction, racerName);
    }

    public Car findById(Integer id) {
        return this.carRepository.getOne(id);
    }

    @Override
    public void save(Car car) {
        this.carRepository.saveAndFlush(car);
    }
}
