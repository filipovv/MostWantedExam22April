package org.softuni.mostwanted.repository;

import org.softuni.mostwanted.model.entity.RaceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceEntryRepository extends JpaRepository<RaceEntry, Integer> {
    @Query(value =
            "SELECT re.id FROM race_entries AS re " +
                    "WHERE re.racer_id = " +
                    "(SELECT nre.racer_id FROM race_entries AS nre " +
                    "GROUP BY nre.racer_id \n" +
                    "ORDER BY COUNT(nre.id) DESC " +
                    "LIMIT 1)", nativeQuery = true)
    List<RaceEntry> getMostWantedRacerEntries();
}
