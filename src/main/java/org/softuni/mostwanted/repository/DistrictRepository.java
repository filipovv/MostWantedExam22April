package org.softuni.mostwanted.repository;

import org.softuni.mostwanted.model.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    District findByNameAndTownName(String districtName, String townName);

    District findByName(String districtName);
}
