package org.softuni.mostwanted.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "racers")
public class Racer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "bounty")
    private BigDecimal bounty;

    @ManyToOne(targetEntity = Town.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town homeTown;

    @OneToMany(mappedBy = "racer")
    private Set<Car> cars;

    public Racer() {
        this.cars = new HashSet<>();
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBounty() {
        return bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public Town getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }
}
