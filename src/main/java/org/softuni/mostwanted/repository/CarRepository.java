package org.softuni.mostwanted.repository;

import org.softuni.mostwanted.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Car findByBrandAndModelAndYearOfProductionAndRacerName(String brand, String model, Integer yearOfProduction, String racerName);
}
