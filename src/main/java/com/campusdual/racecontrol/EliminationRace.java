package com.campusdual.racecontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EliminationRace extends Race{

    private int warmingUpMinutes;
    private static final int ELIMINATION_MINUTE = 1;

    public EliminationRace(String name, int warmingUpMinutes) {
        super(name);
        this.warmingUpMinutes = warmingUpMinutes;
    }

    public int getWarmingUpMinutes() {
        return this.warmingUpMinutes;
    }

    public void setWarmingUpMinutes(int warmingUpMinutes) {
        this.warmingUpMinutes = warmingUpMinutes;
    }

    @Override
    public void showDetailsRace() {
        System.out.println("Race " + this.getName() + " warming up: " + this.warmingUpMinutes + " minutes");

        System.out.println("List of garages:");
        for (Garage g : this.getGaragesInList()) {
            System.out.println(g.getName());
        }
        System.out.println("List of cars:");
        for (Car c : this.getCarsInRace()) {
            System.out.println(c.getBrand() + " " + c.getModel() + " from " + c.getStickGarage() + " Garage");
        }

    }
    public void warmingUp(){
        for (int i = 0; i < this.warmingUpMinutes; i++) {
            System.out.println("Warming up minute " + (i + 1)  + ": ");
            for (Car c : this.getCarsInRace()) {
                c.randomSpeed();
                System.out.println( c.getBrand() + " " + c.getModel() + " from "
                        + c.getStickGarage() + " Garage speed: " + c.getSpeed() + " Km/h");
            }
        }
    }

    @Override
    public Map<Car, Integer> startRace() {

        System.out.println("Warming Up");
        warmingUp();
        System.out.println("Start race: " + this.getName());

        List<Car> remainingCars = new ArrayList<>(this.getCarsInRace());

        Map<Car, Integer> distanceByCar = new HashMap<>();
        for (Car c : this.getCarsInRace()) {
            distanceByCar.put(c, 0);
        }

        while (remainingCars.size() > 1) {
            for (Car c : remainingCars) {
                c.randomSpeed();
                int distanceByMinute = (int)Math.floor((double)c.getSpeed()/60);
                distanceByCar.put(c, distanceByCar.get(c) + distanceByMinute);

                System.out.println(c.getBrand() + " " + c.getModel() + " from " + c.getStickGarage()
                        + " Garage, speed: " + c.getSpeed() + " Km/h, total distance: " + distanceByCar.get(c) + " km");
            }
            System.out.println(ELIMINATION_MINUTE + " minute passed");

            Car lastCar = remainingCars.get(0);
            for (Car c : remainingCars) {
                if (c.getSpeed() < lastCar.getSpeed()) {
                    lastCar = c;
                }
            }
            remainingCars.remove(lastCar);
            System.out.println(lastCar.getBrand() + " " + lastCar.getModel() + " from "
                    + lastCar.getStickGarage() + " Garage is out");
        }

        return distanceByCar;
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

        EliminationRace race1 = new EliminationRace("Race1", 4);
        race1.addMoreThanOneGarage(garage1);
        race1.addMoreThanOneGarage(garage2);
        race1.addMoreThanOneGarage(garage3);
        race1.showDetailsRace();
        Map<Car, Integer> start= race1.startRace();
        race1.showPodium(start);
        System.out.println("#################");
        EliminationRace race2 = new EliminationRace("Race2", 10);
        race2.addOneGarage(garage1);
        race2.showDetailsRace();
        Map<Car, Integer> start2= race2.startRace();
        race2.showPodium(start2);


    }

}
