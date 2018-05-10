package org.softuni.mostwanted.repository;

import org.softuni.mostwanted.model.entity.RaceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaceEntryRepository extends JpaRepository<RaceEntry, Integer> {
}
