package com.campusdual.racecontrol;

import com.campusdual.Utils;

public class Car {
    private String brand;
    private String model;
    private String stickGarage;
    private int speed;
    private int points;
    private static final int MAX_SPEED = 250;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.speed = 0;
        this.points = 0;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStickGarage() {
        return this.stickGarage;
    }

    public void setStickGarage(String stickGarage) {
        this.stickGarage = stickGarage;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void accelerate() {
        this.speed += 30;
        if (this.speed > this.MAX_SPEED) {
            this.speed = this.MAX_SPEED;
        }
    }

    public void brake() {
        this.speed -= 10;
        if (this.speed < 0) {
            this.speed = 0;
        }
    }

    public void randomSpeed() {
        int action = Utils.getRandomNumberInRange(1,3);
        if (action == 1 || action == 3) {
            accelerate();
        } else {
            brake();
        }
    }

    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Yaris");
        car1.accelerate();
        System.out.println("The car: " + car1.getBrand() + " "
        + car1.getModel() + " is moving at a speed of " + car1.getSpeed() + " km/h");
        car1.accelerate();
        System.out.println("The car: " + car1.getBrand() + " "
                + car1.getModel() + " is moving at a speed of " + car1.getSpeed() + " km/h");
        car1.accelerate();
        System.out.println("The car: " + car1.getBrand() + " "
                + car1.getModel() + " is moving at a speed of " + car1.getSpeed() + " km/h");
        car1.brake();
        System.out.println("The car: " + car1.getBrand() + " "
                + car1.getModel() + " is moving at a speed of " + car1.getSpeed() + " km/h");
        car1.randomSpeed();
        System.out.println("The car: " + car1.getBrand() + " "
                + car1.getModel() + " has a speed of " + car1.getSpeed() + " km/h");
    }
}
