package com.campusdual.racecontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StandardRace extends Race{
    private int duration;

    public StandardRace(String name, int duration) {
        super(name);
        this.duration = duration;
    }
    public StandardRace(String name) {
        super(name);
    }

    @Override
    public Map<Car, Integer> startRace() {

        System.out.println("Start race: " + this.getName());
        Map<Car, Integer> distanceByCar = new HashMap<>();
        for (Car c : this.getCarsInRace()) {
            distanceByCar.put(c, 0);
        }
        for (int i = 0; i < this.duration; i++) {
            System.out.println("Hour " + (i + 1));
            for (Car c : this.getCarsInRace()) {
                for (int j = 0; j < 60; j++) {
                    c.randomSpeed();
                }
                int distanceByHour = c.getSpeed();
                int totalDistanceByCar = distanceByCar.get(c) + distanceByHour;
                distanceByCar.put(c,totalDistanceByCar);
                System.out.println(c.getBrand() + " " + c.getModel() + " from " + c.getStickGarage()
                        + " Garage, speed: " + c.getSpeed() + " Km/h");
            }
        }
        return distanceByCar;
    }

    @Override
    public void showDetailsRace() {
        System.out.println("Race " + this.getName()+ " duration: " + this.duration + " hours");
        System.out.println("List of garages:");
        for (Garage g : this.getGaragesInList()) {
            System.out.println(g.getName());
        }
        System.out.println("List of cars:");
        for (Car c : this.getCarsInRace()) {
            System.out.println(c.getBrand() + " " + c.getModel() + " from " + c.getStickGarage() + " Garage");
        }
    }

    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Yaris");
        Car car2 = new Car("Ford", "Mondeo");
        Car car3 = new Car("Ford", "Fiesta");
        Car car4 = new Car("Toyota", "Rav4");
        Car car5 = new Car("Kia", "Niro");
        Car car6 = new Car("Honda", "Jazz");

        List<Car> carsList1 = new ArrayList<>();
        Garage garage1 = new Garage("Motor", carsList1);
        garage1.addCars(car1);
        garage1.addCars(car3);


        List<Car> carsList2 = new ArrayList<>();
        Garage garage2 = new Garage("Gas", carsList2);
        garage2.addCars(car2);
        garage2.addCars(car4);

        List<Car> carsList3 = new ArrayList<>();
        Garage garage3 = new Garage("Auto", carsList3);
        garage3.addCars(car5);
        garage3.addCars(car6);

        StandardRace race1 = new StandardRace("Race1", 4);
        StandardRace race2 = new StandardRace("Race2", 5);

        race1.addMoreThanOneGarage(garage1);
        race1.addMoreThanOneGarage(garage2);
        race1.addMoreThanOneGarage(garage3);
        race1.showDetailsRace();
        Map<Car, Integer> start1= race1.startRace();
        race1.showPodium(start1);
        System.out.println("#################");
        race2.addOneGarage(garage1);
        Map<Car, Integer> start2= race2.startRace();
        race2.showPodium(start2);

    }
}
