package org.softuni.mostwanted.repository;

import org.softuni.mostwanted.model.entity.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Integer> {
    Racer findByName(String name);

    Racer findByNameAndHomeTownName(String racerName, String townName);
}
