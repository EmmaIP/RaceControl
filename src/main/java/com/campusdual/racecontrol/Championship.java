package com.campusdual.racecontrol;


import java.util.*;

public class Championship {
    private String name;
    private List<Race> racesList;
    private List<Garage> garagesInList;

    public Championship(String name, List<Race> racesList) {
        this.name = name;
        this.racesList = racesList;
        this.garagesInList = new ArrayList<>();

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
            System.out.println("###########");
        }
        showFinalScores();
    }

    public void scoreEveryRace(Map<Car, Integer> start){
        List<Map.Entry<Car, Integer>> sortedCars = new ArrayList<>(start.entrySet());
        sortedCars.sort((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()));
        for (int i = 0; i < Math.min(3, sortedCars.size()); i++) {
            Car car = sortedCars.get(i).getKey();
            if (i == 0) {
                car.setPoints(car.getPoints() + 10);
            } else if (i == 1) {
                car.setPoints(car.getPoints() + 5);
            } else {
                car.setPoints(car.getPoints() + 1);
            }
        }
    }

    public void showFinalScores() {
        System.out.println("#################### Final Scores for the Championship " + this.name +" #####################:");

        List<Car> sortedCars = new ArrayList<>();
        for (Race r: racesList) {
            for (Garage g : r.getGaragesInList()) {
                sortedCars.addAll(g.getCars()); //intelli
            }
        }
        sortedCars.sort((entry1, entry2) -> Integer.compare(entry2.getPoints(), entry1.getPoints()));
        Set<String> notRepeatedCars = new HashSet<>();
        for (Car sortedCar : sortedCars) {
            String carIdentifier = sortedCar.getBrand() + " " + sortedCar.getModel();
            if (!notRepeatedCars.contains(carIdentifier)) {
                System.out.println("- " + sortedCar.getBrand() + " " + sortedCar.getModel() + " from garage " + sortedCar.getStickGarage()
                        + " " + sortedCar.getPoints() + " points");

                notRepeatedCars.add(carIdentifier);
            }
        }
        int value = 0;
        String value1 = null;
        int count = 0;
        for (int i = 0; i < Math.max(2, sortedCars.size()); i++) {
            if(count == 1 && sortedCars.get(i).getPoints() == value && !Objects.equals(sortedCars.get(i).getModel(), value1)) {
                System.out.println("There is a tie, two winners for the prize: ");
                System.out.println(sortedCars.get(0).getBrand() + " " + sortedCars.get(0).getModel() + " from Garage " + sortedCars.get(0).getStickGarage());
                System.out.println(sortedCars.get(1).getBrand() + " " + sortedCars.get(1).getModel() + " from Garage " + sortedCars.get(1).getStickGarage());
            }
            value = sortedCars.get(i).getPoints();
            value1 = sortedCars.get(i).getModel();
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
        System.out.println("#################");
        championship1.startChampionship();

    }
}
