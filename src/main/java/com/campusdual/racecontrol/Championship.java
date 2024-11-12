package com.campusdual.racecontrol;

import java.util.*;

public class Championship {
    private String name;
    private List<Race> racesList;
    private List<Garage> garagesInList;
    private Map<Car, Integer> carPoints;
    public Championship(String name, List<Race> racesList) {
        this.name = name;
        this.racesList = racesList;
        this.garagesInList = new ArrayList<>();
        this.carPoints= new HashMap<>();
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Race> getRacesList() {
        return this.racesList;
    }
    public void setRacesList(List<Race> racesList) {
        this.racesList = racesList;
    }
    public List<Garage> getGaragesInList() {
        return garagesInList;
    }
    public void setGaragesInList(List<Garage> garagesInList) {
        this.garagesInList = garagesInList;
    }

    public Map<Car, Integer> getCarPoints() {
        return carPoints;
    }

    public void setCarPoints(Map<Car, Integer> carPoints) {
        this.carPoints = carPoints;
    }

    public void addRaces(Race race) {
        if (race != null) {
            racesList.add(race);
        } else {
            System.out.println("Cannot add a null race.");
        }
    }
    public void garagesInChampionship() {
        for (Race r: racesList) {
            System.out.println(r.getName() + " race, list of garages:");
            for (Garage g : r.getGaragesInList()) {
                System.out.println(g.getName());
            }
        }
    }
    public void startChampionship() {
        for (Race r: racesList) {
            Map<Car, Integer> start= r.startRace();
            r.showPodium(start);
            scoreEveryRace(start);
        }
    }

    public void scoreEveryRace(Map<Car, Integer> start){
        List<Map.Entry<Car, Integer>> sortedCars = new ArrayList<>(start.entrySet());
        sortedCars.sort((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()));
        for (int i = 0; i < Math.min(3, sortedCars.size()); i++) {
            Car car = sortedCars.get(i).getKey();
            int points;
            if (i == 0) {
                points = 10;
            } else if (i == 1) {
                points = 5;
            } else {
                points = 1;
            }
            this.carPoints.put(car, carPoints.getOrDefault(car, 0) + points);
        }
    }

    public void showFinalScores() {
        System.out.println("Final Scores:");
        Map<Car, Integer> sortedMap = new LinkedHashMap<>();
        this.carPoints.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .forEachOrdered(entry -> {
                    sortedMap.put(entry.getKey(), entry.getValue());
                    System.out.println(entry.getKey().getBrand()+ " " + entry.getKey().getModel() + " from Garage "
                            + entry.getKey().getStickGarage() + ": " + entry.getValue() + " points");
                });
        int value = 0;
        int count = 0;
        for (Map.Entry<Car, Integer> entry: sortedMap.entrySet()) {
            if(count == 1 && entry.getValue() == value) {
                System.out.println("There is a tie, two winners for the prize");
            }
            value = entry.getValue();
            count++;
        }
    }

    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Yaris");
        Car car2 = new Car("Honda", "Civic");
        Car car3 = new Car("Ford", "Focus");
        Car car4 = new Car("Chevrolet", "Cruze");
        Car car5 = new Car("Nissan", "Sentra");
        Car car6 = new Car("Mazda", "Mazda3");
        Car car7 = new Car("Hyundai", "Elantra");
        Car car8 = new Car("Volkswagen", "Golf");
        Car car9 = new Car("BMW", "3 Series");
        Car car10 = new Car("Mercedes-Benz", "C-Class");
        Car car11 = new Car("Audi", "A4");
        Car car12 = new Car("Kia", "Forte");
        Car car13 = new Car("Subaru", "Impreza");
        Car car14 = new Car("Peugeot", "308");
        Car car15 = new Car("Renault", "Megane");

        List<Car> carsList1 = new ArrayList<>();
        List<Car> carsList2 = new ArrayList<>();
        List<Car> carsList3 = new ArrayList<>();
        List<Car> carsList4 = new ArrayList<>();
        List<Car> carsList5 = new ArrayList<>();

        Garage garage1 = new Garage("Garage1", carsList1);
        Garage garage2 = new Garage("Garage2", carsList2);
        Garage garage3 = new Garage("Garage3", carsList3);
        Garage garage4 = new Garage("Garage4", carsList4);
        Garage garage5 = new Garage("Garage5", carsList5);

        garage1.addCars(car1);
        garage1.addCars(car2);
        garage1.addCars(car3);

        garage2.addCars(car4);
        garage2.addCars(car5);
        garage2.addCars(car6);

        garage3.addCars(car7);
        garage3.addCars(car8);
        garage3.addCars(car9);

        garage4.addCars(car10);
        garage4.addCars(car11);
        garage4.addCars(car12);

        garage5.addCars(car13);
        garage5.addCars(car14);
        garage5.addCars(car15);

        EliminationRace race1 = new EliminationRace("Race1", 4);
        EliminationRace race2 = new EliminationRace("Race2", 10);
        EliminationRace race3 = new EliminationRace("Race3", 8);

        StandardRace race4 = new StandardRace("Race4", 4);
        StandardRace race5 = new StandardRace("Race5", 5);

        race1.addMoreThanOneGarage(garage1);
        race1.addMoreThanOneGarage(garage2);
        race1.addMoreThanOneGarage(garage3);
        race1.addMoreThanOneGarage(garage4);
        race1.addMoreThanOneGarage(garage5);


        race2.addMoreThanOneGarage(garage4);
        race2.addMoreThanOneGarage(garage5);
        race2.addMoreThanOneGarage(garage3);

        race3.addMoreThanOneGarage(garage1);
        race3.addMoreThanOneGarage(garage4);
        race3.addMoreThanOneGarage(garage2);

        race4.addMoreThanOneGarage(garage1);
        race4.addMoreThanOneGarage(garage2);
        race4.addMoreThanOneGarage(garage3);
        race4.addMoreThanOneGarage(garage4);
        race4.addMoreThanOneGarage(garage5);

        race5.addOneGarage(garage5);

        List<Race> racesList = new ArrayList<>();

        Championship championship1 = new Championship("championship1",racesList);
        championship1.addRaces(race1);
        championship1.addRaces(race2);
        championship1.addRaces(race3);
        championship1.addRaces(race4);
        championship1.addRaces(race5);

        championship1.garagesInChampionship();
        championship1.startChampionship();
        championship1.showFinalScores();
    }
}
