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
    En la aplicación, se pretende guardar el estado del programa, de manera que no sea necesario insertar de nuevo la
    información de los Coches, Garajes, Carreras y Torneos en un fichero.
    Este fichero debe cargarse cada vez que se inicia el programa y debe actualizarse antes de finalizar el programa.
* */

import com.campusdual.Utils;
import java.util.ArrayList;
import java.util.List;



public class Control {

    public void menu() {
        System.out.println("#################################################################");
        System.out.println("#################### Welcome to RaceControl! ####################");
        System.out.println("########################## Playing Rules ########################");
        System.out.println("################## Register garages and their cars ##############");
        System.out.println("################## Choose the type of car racing ###############");
        System.out.println("################### Let the championship starts!#################");
        System.out.println("#################################################################");

        int choice = 0;
        List<Garage> listGarage = new ArrayList<>();
        List<Race> listRaces = new ArrayList<>();
        do {
            String nameG = Utils.string("Write a name for one garage: ");
            Garage garage = generateGarage(nameG);
            listGarage.add(garage);
            choice = Utils.integer("More garages write 2, no more garages write -1: ");
        }while (choice != -1);

        int choice1 = 0;
        do {
            System.out.println("############################################################");
            int option = Utils.integer("For Standard Race write 1, for Elimination Race write 2: ");
            if (option == 1) {
                createStandardRace(listGarage, listRaces);
            } else if (option == 2) {
                createEliminationRace(listGarage, listRaces);
            }
            choice1 = Utils.integer("More races write 2, no more races write -1: ");
        } while(choice1 != -1);

        String nameC = Utils.string("Chose a name for the Championship: ");
        Championship championship = new Championship(nameC, listRaces);
        championship.startChampionship();
    }

    public Garage generateGarage(String name){
        List<Car> carsList = new ArrayList<>();
        int number = 0;
        do {
            String brand = Utils.string("Car brand: ");
            String model = Utils.string("Car model: ");
            System.out.println("More cars write 2");
            number = Utils.integer("No more cars, write -1: ");
            Car car = new Car(brand, model);
            carsList.add(car);
        } while (number != -1);
        Garage garage = new Garage(name, carsList);
        return garage;
    }
    public void createStandardRace(List<Garage> listGarage, List<Race> listRaces) {
        System.out.println("Standard Races have a duration");
        System.out.println("The winner is the car with the longest distance covered");
        System.out.println("############################################################");
        String nameR = Utils.string("Choose a name for this race: ");
        int durationR = Utils.integer("Duration in hours?: ");
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
        System.out.println("All the cars are ready, Race cars " + raceS.getName() + " starts!");
        System.out.println("############################################################");
        listRaces.add(raceS);
    }

    private static void garagesInStandardRace(List<Garage> listGarage, StandardRace raceS) {
        int numberG = 0;
        do {
            System.out.println("No more garages, write -1");
            System.out.println("List of garages");
            for (int i = 0; i < listGarage.size(); i++) {
                System.out.println(i +". "+ listGarage.get(i).getName());
            }
            numberG = Utils.integer("Write de number of a chosen garage: ");
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
        int numberG = Utils.integer("Write de number of the chosen garage: ");
        return numberG;
    }

    public void createEliminationRace(List<Garage> listGarage, List<Race> listRaces) {
        System.out.println("Elimination races have a warming up time");
        System.out.println("After that time, every minute the car in the last position is out");
        System.out.println("The winner is the only car at the end of the race");
        System.out.println("############################################################");
        String nameE = Utils.string("Choose a name for this race: ");
        int minutesE = Utils.integer("How long is the warming up time in minutes?: ");
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
        System.out.println("All the cars are ready, Race cars " + raceE.getName() + " starts!");
        System.out.println("############################################################");
        listRaces.add(raceE);
    }

    private static void garagesInEliminationRace(List<Garage> listGarage, EliminationRace raceE) {
        int numberG = 0;
        do {
            System.out.println("No more garages, write -1");
            System.out.println("List of garages");
            for (int i = 0; i < listGarage.size(); i++) {
                System.out.println(i +". "+ listGarage.get(i).getName());
            }
            numberG = Utils.integer("Write de number of a chosen garage: ");
            if(numberG != -1)
            {
                raceE.addMoreThanOneGarage(listGarage.get(numberG));
            }
        } while (numberG != -1);
    }


    public static void main(String[] args) {
        Control control = new Control();
        control.menu();

    }
}
