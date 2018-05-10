package org.softuni.mostwanted.repository;

import org.softuni.mostwanted.model.dto.json.TownJSONExportDto;
import org.softuni.mostwanted.model.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
    Town findByName(String name);

    @Query("SELECT new org.softuni.mostwanted.model.dto.json.TownJSONExportDto(t.name, COUNT(r.id)) " +
            "FROM Town AS t INNER JOIN t.racers AS r " +
            "WHERE t.racers.size>0 " +
            "GROUP BY t.id " +
            "ORDER BY t.racers.size DESC, t.name ASC")
    List<TownJSONExportDto> findRacingTowns();
}
