package org.softuni.mostwanted.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "town")
    private Set<District> districts;

    @OneToMany(mappedBy = "homeTown")
    private Set<Racer> racers;

    public Town() {
        this.districts = new HashSet<>();
        this.racers = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    public void addDistrict(District district) {
        this.districts.add(district);
    }

    public Set<Racer> getRacers() {
        return racers;
    }

    public void setRacers(Set<Racer> racers) {
        this.racers = racers;
    }

    public void addRacer(Racer racer) {
        this.racers.add(racer);
    }
}

