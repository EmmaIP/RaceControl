package com.campusdual.racecontrol;

import java.util.List;

public class Championship {
    private String name;
    private List<Race> racesList;
    public Championship(String name, List<Race> racesList) {
        this.name = name;
        this.racesList = racesList;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Race> getRacesList() {
        return racesList;
    }
    public void setRacesList(List<Race> racesList) {
        this.racesList = racesList;
    }

    public void addRaces(Race race) {
        if (race != null) {
            racesList.add(race);
        } else {
            System.out.println("Cannot add a null race.");
        }
    }

    public void startChampionship() {
        for (Race r: racesList) {
            r.startRace();
            r.showDetailsRace();
        }
    }
}
