package org.softuni.mostwanted.model.dto.json;

import com.google.gson.annotations.Expose;

public class TownJSONExportDto {
    @Expose
    private String name;
    @Expose
    private Long racers;

    public TownJSONExportDto() {
    }

    public TownJSONExportDto(String name, Long racers) {
        this.name = name;
        this.racers = racers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRacers() {
        return racers;
    }

    public void setRacers(Long racers) {
        this.racers = racers;
    }
}
