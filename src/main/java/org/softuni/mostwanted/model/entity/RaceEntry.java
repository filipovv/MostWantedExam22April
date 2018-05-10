package org.softuni.mostwanted.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "race_entries")
public class RaceEntry {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "has_finished")
    private boolean hasFinished;

    @Column(name = "finish_time")
    private Integer finishTime;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "racer_id", referencedColumnName = "id")
    private Racer racer;

    @Transient
    private Race race;

    public RaceEntry() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public Integer getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Integer finishTime) {
        this.finishTime = finishTime;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }
}
