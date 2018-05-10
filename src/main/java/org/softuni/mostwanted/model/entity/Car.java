package org.softuni.mostwanted.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "year_of_production", nullable = false)
    private Integer yearOfProduction;

    @Column(name = "max_speed")
    private Double maxSpeed;

    @Column(name = "zero_to_sixty")
    private Double zeroToSixty;

    @ManyToOne(targetEntity = Racer.class)
    @JoinColumn(name = "racer_id", referencedColumnName = "id")
    private Racer racer;

    public Car() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getZeroToSixty() {
        return zeroToSixty;
    }

    public void setZeroToSixty(Double zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }
}
