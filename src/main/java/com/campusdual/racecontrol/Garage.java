package com.campusdual.racecontrol;

import java.util.ArrayList;
import java.util.List;

public class Garage {

    private String name;
    private List<Car> carsList;

    public Garage(String name, List<Car> cars) {
        this.name = name;
        this.carsList = cars;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return this.carsList;
    }

    public void setCars(List<Car> cars) {
        this.carsList = cars;
    }

    public void addCars(Car car) {
        if (car != null) {
            this.carsList.add(car);
            car.setStickGarage(this.name);
        } else {
            System.out.println("Cannot add a null car.");
        }
    }

    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Yaris");
        Car car2 = new Car("Ford", "Mondeo");
        Car car3 = new Car("Ford", "Fiesta");

        List<Car> carsList = new ArrayList<>();

        Garage garage1 = new Garage("Motor", carsList);
        garage1.addCars(car1);
        garage1.addCars(car2);
        garage1.addCars(car3);

        System.out.println(garage1.name);
        for (Car car : garage1.getCars()) {
            System.out.println(car.getBrand() + " " + car.getModel() + " " + car.getStickGarage());
        }
    }
}
