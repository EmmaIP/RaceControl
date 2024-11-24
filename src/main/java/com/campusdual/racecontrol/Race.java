package com.campusdual.racecontrol;

import com.campusdual.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Race {

    private String name;
    private List<Garage> garagesInList;
    private List<Car> carsInRace;

    public Race(String name) {
        this.name = name;
        this.garagesInList = new ArrayList<>();
        this.carsInRace = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Garage> getGaragesInList() {
        return garagesInList;
    }

    public void setGaragesInList(List<Garage> garagesInList) {
        this.garagesInList = garagesInList;
    }

    public List<Car> getCarsInRace() {
        return carsInRace;
    }

    public void setCarsInRace(List<Car> carsInRace) {
        this.carsInRace = carsInRace;
    }

    public void addOneGarage(Garage garage){
        if (garage != null) {
            this.garagesInList.add(garage);
            for (Car c : garage.getCars()) {
                this.carsInRace.add(c);
            }
        }
        else{
            System.out.println("Cannot add a null garage");
        }
    }
    public void addMoreThanOneGarage(Garage garage) {
        if (garage != null) {
            this.garagesInList.add(garage);
            List<Car> cars = garage.getCars();
            int randomIndex = Utils.getRandomNumberInRange(0, cars.size() - 1);
            Car carIn = cars.get(randomIndex);
            this.carsInRace.add(carIn);

        }
        else{
            System.out.println("Cannot add a null garage");
        }
    }

    public void showPodium(Map<Car, Integer> distanceByCar) {

        List<Map.Entry<Car, Integer>> sortedCars = new ArrayList<>(distanceByCar.entrySet());
        sortedCars.sort((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()));

        System.out.println("Podium:");
        for (int i = 0; i < Math.min(3, sortedCars.size()); i++) {
            Map.Entry<Car, Integer> entry = sortedCars.get(i);
            Car car = entry.getKey();
            int distance = entry.getValue();
            System.out.println((i + 1) + ". " + car.getBrand() + " " + car.getModel() + " from "
                    + car.getStickGarage() + ", total distance: " + distance + " km");
        }
    }

    public abstract Map<Car, Integer> startRace();
    public abstract void showDetailsRace();

}
