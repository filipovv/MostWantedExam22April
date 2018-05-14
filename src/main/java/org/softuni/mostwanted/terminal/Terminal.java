package org.softuni.mostwanted.terminal;

import org.softuni.mostwanted.config.Config;
import org.softuni.mostwanted.controller.*;
import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class Terminal implements CommandLineRunner {
    private final TownController townController;
    private final DistrictController districtController;
    private final RacerController racerController;
    private final CarController carController;
    private final RaceEntryController raceEntryController;
    private final RaceController raceController;
    private final FileIO fileIO;
    private final ConsoleIO consoleIO;

    @Autowired
    public Terminal(TownController townController,
                    DistrictController districtController,
                    RacerController racerController,
                    CarController carController,
                    RaceEntryController raceEntryController,
                    RaceController raceController,
                    FileIO fileIO,
                    ConsoleIO consoleIO) {
        this.townController = townController;
        this.districtController = districtController;
        this.racerController = racerController;
        this.carController = carController;
        this.raceEntryController = raceEntryController;
        this.raceController = raceController;
        this.fileIO = fileIO;
        this.consoleIO = consoleIO;
    }

    @Override
    public void run(String... args) throws Exception {
        this.consoleIO.write(this.townController.importDataFromJSON(this.fileIO.read(Config.TOWNS_IMPORT_JSON)));
        this.consoleIO.write(this.districtController.importDataFromJSON(this.fileIO.read(Config.DISTRICTS_IMPORT_JSON)));
        this.consoleIO.write(this.racerController.importDataFromJSON(this.fileIO.read(Config.RACERS_IMPORT_JSON)));
        this.consoleIO.write(this.carController.importDataFromJSON(this.fileIO.read(Config.CARS_IMPORT_JSON)));
        this.consoleIO.write(this.raceEntryController.importDataFromXML(this.fileIO.read(Config.RACE_ENTRIES_IMPORT_XML)));
        this.consoleIO.write(this.raceController.importDataFromXML(this.fileIO.read(Config.RACES_IMPORT_XML)));

        this.fileIO.write(this.townController.exportRacingTowns(), "racingTowns", "json");
        this.fileIO.write(this.raceEntryController.exportMostWantedRacer(), "mostWanted", "xml");
    }
}
