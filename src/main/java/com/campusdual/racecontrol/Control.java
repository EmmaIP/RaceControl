package com.campusdual.racecontrol;

/*Se desea crear un programa para controlar el ciclo de la celebración de eventos automovilísticos.

    ✓ El Control central del programa tiene que gestionar la realización de Torneos y de Carreras,
      además de los diferentes Garajes que participan en ambas competiciones automovilísticas.
    ✓ Los Coches tienen una Marca y un Modelo que los identifica y se registran en los Garajes.
    ✓ El Garaje al que pertenece un coche, le pone una pegatina con el nombre del garaje para identificarlo.
    ✓ Todos los coches tienen la misma velocidad máxima, de tal manera que la velocidad que adquiera durante una vuelta
      está marcada por la pericia del piloto, que parece que acelera y frena el coche aleatoriamente una vez por minuto.
    ✓ Se aplicará un valor de aceleración o deceleración instantáneo, por ejemplo, en intervalos de 10 Km/h.
    ✓ Las Carreras tienen un nombre para poder identificarlas. Dichas Carreras son de dos tipos: Estándar (que puede durar
      cualquier número de horas, pero que normalmente son 3 horas) o carreras de Eliminación (que tienen una serie de
      minutos previos para que los pilotos se hagan a la pista, y al terminar esos minutos de calentamiento, se irá
      retirando el coche que va en la última posición, cada minuto, hasta que sólo quede un coche).
    ✓ Aunque, las carreras sean diferentes, todas ellas tienen varias características en común; como puede ser registros
      de los garajes que participan en la carrera, que coches de cada garaje compiten, el podio de cada carrera, etc.
    ✓ Los Torneos son agrupaciones de Carreras. Los Torneos tienen un nombre que los identifica y, normalmente, están
      formados por 10 carreras, aunque este número puede variar. En los Torneos, se registran los Garajes que van a
      participar en cada una de las carreras.
    ✓ Todas las Carreras y Torneos se disputan en circuitos de fórmula NASCAR que, dada su estructura, hace que los coches
      no necesiten girar.
    ✓ En los Torneos y las Carreras, pueden participar uno o varios Garajes. Si participa uno, todos sus coches entrarán
      en la competición. En el caso de ser más de uno, solo competirá un coche de cada Garaje, elegido de forma aleatoria.
    ✓ El resultado de cualquier carrera dependerá de la distancia recorrida en el mismo tiempo por cada coche que
      participa en la carrera.
    ✓ El podio de una carrera son los 3 Coches que más distancia hayan recorrido en el mismo tiempo.
    ✓ Un Torneo tendrá puntuaciones para cada coche que participe. Se entregarán puntuaciones a los coches del podio de
      cada carrera, de mayor a menor, de manera proporcional. Estas puntuaciones son acumulativas para cada coche.
    ✓ El ganador del torneo será el coche que más puntos tenga al finalizar el torneo. En caso de empate, se divide el premio.
    ✓ En la aplicación, se pretende guardar el estado del programa, de manera que no sea necesario insertar de nuevo la
      información de los Coches, Garajes, Carreras y Torneos en un fichero.
    ✓ Este fichero debe cargarse cada vez que se inicia el programa y debe actualizarse antes de finalizar el programa.
* */

import com.campusdual.Utils;
import com.google.gson.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Control {

    private static final String CAR = "Cars";
    private static final String CAR_BRAND = "Brand";
    private static final String CAR_MODEL = "Model";
    private static final String GARAGE = "Name Garage";
    private static final String RACE = "Name Race";
    private static final String CHAMPIONSHIP = "Name Championship";

    public void menu() {
        System.out.println("#################################################################");
        System.out.println("#################### Welcome to RaceControl! ####################");
        System.out.println("########################## Playing Rules: ########################");
        System.out.println("################## Register garages and their cars ##############");
        System.out.println("################## Choose the type of car racing ###############");
        System.out.println("############################ Let´s go ###########################");
        System.out.println("#################################################################");


        List<Garage> listGarage = new ArrayList<>();
        List<Race> listRaces = new ArrayList<>();
        List<Championship> listChampionships = new ArrayList<>();

        int number = 0;
        do{
            System.out.println("1. Load Championships");
            System.out.println("2. Load Garages and their cars");
            System.out.println("3. Create Garages and Cars");
            System.out.println("4. Create Races");
            System.out.println("5. Start Championship and create others");
            System.out.println("6. Exit");
            number = Utils.integer("Choose an option: ");

            switch (number) {
                case 1:
                    System.out.println("Load Championships");
                    loadChampionships(listRaces, listChampionships);
                    break;
                case 2:
                    System.out.println("Load Garages and their cars");
                    loadGarages(listGarage);
                    break;
                case 3:
                    System.out.println("Create Garages and Cars");
                    createGarages(listGarage);
                    break;
                case 4:
                    System.out.println("Create Races");
                    if(!listGarage.isEmpty()){
                        createRaces(listGarage, listRaces);
                    }
                    else{
                        System.out.println("There must be garages created");
                    }
                    break;
                case 5:
                    System.out.println("Start Championship and create others");
                    if(!listRaces.isEmpty() && !listGarage.isEmpty()) {
                        createChampionship(listGarage, listRaces, listChampionships);
                    }
                    else{
                        System.out.println("There must be races created");
                    }
                    break;
                case 6:
                    System.out.println("Bye, see you!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        while(number != 6);

    }

    private void createChampionship(List<Garage> listGarage, List<Race> listRaces, List<Championship> listChampionships) {
        int moreChampionships = 0;
        do {
            String nameC = Utils.string("Chose a name for the Championship: ");
            Championship championship = new Championship(nameC, listRaces);
            championship.startChampionship();
            listChampionships.add(championship);
            saveInJson(listGarage, listChampionships);
            moreChampionships = Utils.integer("More championships write 2, no more championships write -1: ");

            if (moreChampionships == 2) {
                listRaces.clear();
                int moreRace = 0;
                do {
                    int option = Utils.integer("For Standard Race write 1, for Elimination Race write 2: ");
                    if (option == 1) {
                        createStandardRace(listGarage, listRaces);
                    } else if (option == 2) {
                        createEliminationRace(listGarage, listRaces);
                    }
                    moreRace = Utils.integer("More races write 2, no more races write -1: ");
                } while (moreRace != -1);
            }
        } while (moreChampionships != -1);
    }

    private void createRaces(List<Garage> listGarage, List<Race> listRaces) {
        int moreRaces = 0;
        do {
            int option = Utils.integer("For Standard Race write 1, for Elimination Race write 2: ");
            if (option == 1) {
                createStandardRace(listGarage, listRaces);
            } else if (option == 2) {
                createEliminationRace(listGarage, listRaces);
            }
            moreRaces = Utils.integer("More races write 2, no more races write -1: ");
        } while(moreRaces != -1);
    }

    private void createGarages(List<Garage> listGarage) {
        int moreGarages = 0;
        do {
            String nameG = Utils.string("Write a name for one garage: ");
            Garage garage = generateGarage(nameG);
            listGarage.add(garage);
            moreGarages = Utils.integer("More garages write 2, no more garages write -1: ");
        }while (moreGarages != -1);
    }

    private void loadGarages(List<Garage> listGarage) {
        List<Garage> loadedGarages = openGaragesJson();
        if (!loadedGarages.isEmpty()) {
            listGarage.addAll(loadedGarages);
            System.out.println("Garages loaded successfully from status file.");
        } else {
            System.out.println("No garages found");
        }
    }

    private void loadChampionships(List<Race> listRaces, List<Championship> listChampionships) {
        Championship loadedChampionship = openChampionshipsJson();
        listChampionships.add(loadedChampionship);
        if (!listChampionships.isEmpty()) {
            listRaces = loadedChampionship.getRacesList();
            listChampionships.add(loadedChampionship);
            System.out.println("Championships loaded successfully from status file.");
        } else {
            System.out.println("No championships found");
        }
    }


    public void saveInJson(List<Garage> listGarage, List<Championship> listChampionships){
        JsonObject status = new JsonObject();
        JsonArray componentsArrayG = new JsonArray();
        for (Garage g: listGarage) {
            JsonObject garages = new JsonObject();
            garages.addProperty("Name Garage", g.getName());
            JsonArray componentsArrayC = new JsonArray();
            for (Car c : g.getCars()) {
                JsonObject cars = new JsonObject();
                cars.addProperty("Brand",c.getBrand());
                cars.addProperty("Model",c.getModel());
                componentsArrayC.add(cars);
            }
            garages.add("Cars", componentsArrayC);
            componentsArrayG.add(garages);
        }
        JsonArray componentsArrayCh = new JsonArray();
        for (Championship ch: listChampionships) {
            JsonObject championships = new JsonObject();
            championships.addProperty("Name Championship", ch.getName());
            JsonArray componentsArrayR = new JsonArray();
            for (Race r : ch.getRacesList()){
                JsonObject races = new JsonObject();
                races.addProperty("Name Race",r.getName());
                componentsArrayR.add(races);
            }
            championships.add("Races", componentsArrayR);
            componentsArrayCh.add(championships);
        }
        status.add("Garages", componentsArrayG);
        status.add("Championships", componentsArrayCh);

        try(FileWriter fw = new FileWriter("src/main/resources/status.json")){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(status);
            fw.write(json);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Garage> openGaragesJson() {
        Path filePath = Paths.get("src/main/resources/status.json");
        List<Garage> garageList = new ArrayList<>();
        Garage garageOneToOne = null;
        try{
            Object obj = new JsonParser().parse(new FileReader(filePath.toFile()));
            JsonObject jo = (JsonObject) obj;
            JsonArray arrayGarages = jo.getAsJsonArray("Garages");
            for (int i = 0; i < arrayGarages.size(); i++) {
                JsonElement elementG = arrayGarages.get(i);
                String nameGarage = elementG.getAsJsonObject().get("Name Garage").getAsString();
                garageOneToOne = new Garage(nameGarage);
                garageList.add(garageOneToOne);
                JsonArray arrayCars = elementG.getAsJsonObject().get("Cars").getAsJsonArray();
                for (int j = 0; j < arrayCars.size(); j++) {
                    JsonElement elementoC = arrayCars.get(j);
                    String brand = elementoC.getAsJsonObject().get("Brand").getAsString();
                    String model = elementoC.getAsJsonObject().get("Model").getAsString();
                    Car car = new Car(brand, model);
                    garageOneToOne.addCars(car);
                }
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return garageList;
    }
    public Championship openChampionshipsJson() {
        Path filePath = Paths.get("src/main/resources/status.json");
        List<Race> raceList = new ArrayList<>();
        Championship championship = null;
        try{
            Object obj = new JsonParser().parse(new FileReader(filePath.toFile()));
            JsonObject jo = (JsonObject) obj;
            JsonArray arrayChampionships = jo.getAsJsonArray("Championships");
            for (int i = 0; i < arrayChampionships.size(); i++) {
                JsonElement elementCh = arrayChampionships.get(i);
                String nameChampionship = elementCh.getAsJsonObject().get("Name Championship").getAsString();
                JsonArray arrayRaces = elementCh.getAsJsonObject().get("Races").getAsJsonArray();
                for (int j = 0; j < arrayRaces.size(); j++) {
                    JsonElement elementoR = arrayRaces.get(j);
                    String raceName = elementoR.getAsJsonObject().get("Name Race").getAsString();
                    StandardRace race = new StandardRace(raceName);
                    raceList.add(race);
                }
                championship = new Championship(nameChampionship, raceList);
            }
            return championship;
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Garage generateGarage(String name){
        List<Car> carsList = new ArrayList<>();
        int moreCars = 0;
        do {
            String brand = Utils.string("Car brand: ");
            String model = Utils.string("Car model: ");
            System.out.println("More cars write 2");
            moreCars = Utils.integer("No more cars, write -1: ");
            Car car = new Car(brand, model);
            carsList.add(car);
        } while (moreCars != -1);
        Garage garage = new Garage(name, carsList);
        return garage;
    }
    public void createStandardRace(List<Garage> listGarage, List<Race> listRaces) {
        System.out.println("Standard Races have a duration");
        System.out.println("The winner is the car with the longest distance covered");
        System.out.println("#############");
        String nameR = Utils.string("Choose a name for this race: ");
        int durationR = Utils.integer("Duration in hours: ");
        StandardRace raceS = new StandardRace(nameR, durationR);
        int option1 = Utils.integer("For only one garage write 1, for more than one write 2: ");
        if(option1 == 1){
            int numberG = oneGarageInRace(listGarage);
            System.out.println("You have chosen the garage " + listGarage.get(numberG).getName());
            raceS.addOneGarage(listGarage.get(numberG));
        }
        else if(option1 == 2){
            garagesInStandardRace(listGarage, raceS);
        }
        listRaces.add(raceS);
        System.out.println("All the cars are ready, " + raceS.getName() + " starts!");
        raceS.showPodium(raceS.startRace());
        System.out.println("############################################################");

    }

    private static void garagesInStandardRace(List<Garage> listGarage, StandardRace raceS) {
        int numberG = 0;
        do {
            System.out.println("No more garages, write -1");
            System.out.println("List of garages");
            for (int i = 0; i < listGarage.size(); i++) {
                System.out.println(i +". "+ listGarage.get(i).getName());
            }
            numberG = Utils.integer("Write the number of a chosen garage: ");
            if(numberG != -1)
            {
                raceS.addMoreThanOneGarage(listGarage.get(numberG));
            }

        } while (numberG != -1);
    }

    public int oneGarageInRace(List<Garage> listGarage) {
        System.out.println("List of garages");
        for (int i = 0; i < listGarage.size(); i++) {
            System.out.println(i +". "+ listGarage.get(i).getName());
        }
        int numberG = Utils.integer("Write the number of the chosen garage: ");
        return numberG;
    }

    public void createEliminationRace(List<Garage> listGarage, List<Race> listRaces) {
        System.out.println("Elimination races have a warming up time");
        System.out.println("After that time, every minute the car in the last position is out");
        System.out.println("The winner is the only car at the end of the race");
        System.out.println("############################################################");
        String nameE = Utils.string("Choose a name for this race: ");
        int minutesE = Utils.integer("Duration of warming up time in minutes: ");
        EliminationRace raceE = new EliminationRace(nameE, minutesE);
        int option2 = Utils.integer("For one participating garage write 1, for more than one write 2: ");
        if(option2 == 1){
            int numberG = oneGarageInRace(listGarage);
            System.out.println("You have chosen the garage " + listGarage.get(numberG).getName());
            raceE.addOneGarage(listGarage.get(numberG));

        }
        else if (option2 == 2){
            garagesInEliminationRace(listGarage, raceE);
        }
        listRaces.add(raceE);
        System.out.println("All the cars are ready " + raceE.getName() + " starts!");
        raceE.showPodium(raceE.startRace());
        System.out.println("############################################################");

    }

    private static void garagesInEliminationRace(List<Garage> listGarage, EliminationRace raceE) {
        int numberG = 0;
        do {
            System.out.println("List of garages");
            for (int i = 0; i < listGarage.size(); i++) {
                System.out.println(i +". "+ listGarage.get(i).getName());
            }
            System.out.println("No more garages, write -1");
            numberG = Utils.integer("Write the number of a chosen garage: ");
            if(numberG != -1)
            {
                raceE.addMoreThanOneGarage(listGarage.get(numberG));
            }
        } while (numberG != -1);
    }


    public static void main(String[] args) throws FileNotFoundException {
        Control control = new Control();
        control.menu();

        /*List<Garage> listGarage = control.openGaragesJson();
        for (int i = 0; i <listGarage.size() ; i++) {
            System.out.println(listGarage.get(i).getName());
        }
        Championship champ = control.openChampionshipsJson();
        System.out.println(champ.getName());*/

    }
}
