package org.softuni.mostwanted.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "races")
public class Race {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "laps", nullable = false)
    private int laps;

    @ManyToOne(optional = false)
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private Set<RaceEntry> entries;

    public Race() {
        this.laps = 0;
        this.entries = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Set<RaceEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<RaceEntry> entries) {
        this.entries = entries;
    }
}
