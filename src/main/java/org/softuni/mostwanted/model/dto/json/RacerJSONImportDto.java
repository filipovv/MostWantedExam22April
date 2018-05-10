package org.softuni.mostwanted.model.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RacerJSONImportDto {
    @Expose
    @NotNull
    private String name;
    @Expose
    private Integer age;
    @Expose
    private BigDecimal bounty;
    @Expose
    @SerializedName(value = "homeTown")
    private String homeTownName;

    public RacerJSONImportDto() {
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

    public String getHomeTownName() {
        return this.homeTownName;
    }

    public void setHomeTownName(String homeTownName) {
        this.homeTownName = homeTownName;
    }
}
