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

    public Garage generateGarage(String name){
        List<Car> carsList = new ArrayList<>();
        int number = 0;
        do {
            String brand = Utils.string("Car brand: ");
            String model = Utils.string("Car model: ");
            System.out.println("More cars write 2");
            number = Utils.integer("No more cars, write 1: ");
            Car car = new Car(brand, model);
            carsList.add(car);
        } while (number != 1);
        Garage garage = new Garage(name, carsList);
        return garage;
    }

    public void menu() {
        System.out.println("\nWelcome to RaceControl");
        System.out.println("Playing rules");
        System.out.println("Register garages and their cars");
        System.out.println("Choose a type of  car racing");
        System.out.println("Let the game begins");
        System.out.println("####################");


        int choice = 0;
        List<Garage> listGarage= new ArrayList<>();
        do {
            String nameG = Utils.string("Write a name for the garage: ");
            Garage garage = generateGarage(nameG);
            listGarage.add(garage);
            System.out.println("More garages write 2");
            choice = Utils.integer("No more garages write 1: ");
        }while (choice != 1);

        System.out.println("####################");
        int option = Utils.integer("For standard race write 1, for elimination race write 2: ");
        if (option == 1) {
            System.out.println("Standard races have a duration");
            System.out.println("The winner is the car with the longest distance");
            String nameR = Utils.string("Choose a name for this race: ");
            int durationR = Utils.integer("How long is the duration in hours?: ");
            StandardRace raceS = new StandardRace(nameR, durationR);
            int option1 = Utils.integer("For one participating garage write 1, for more than one participating garage write 2: ");
            if(option1 == 1){
                for (int i = 0; i < listGarage.size(); i++) {
                    System.out.println(i +". "+ listGarage.get(i).getName());
                }
                int numberG = Utils.integer("Write de number of the chosen garage: ");
                raceS.addOneGarage(listGarage.get(numberG));
                System.out.println("All the cars are ready, Race cars " + raceS.getName() + " starts!");
                raceS.startRace();
            }
            else if(option1 == 2){
                int numberG = 0;
                do {
                    System.out.println("No more garages write 1");
                    numberG = Utils.integer("Write de number of a chosen garage: ");
                    raceS.addMoreThanOneGarage(listGarage.get(numberG));
                } while (numberG != 1);
            }
            System.out.println("All the cars are ready, Race cars " + raceS.getName() + " starts!");
                raceS.startRace();

        } else if (option == 2) {
            System.out.println("Elimination races have a warming up time");
            System.out.println("After that time, every minute the car in the last position is eliminated");
            System.out.println("The winner is the car that is not eliminated");
            String nameE = Utils.string("Choose a name for this race: ");
            int minutesE = Utils.integer("How long is the warming up time in minutes?: ");
            StandardRace raceE = new StandardRace(nameE, minutesE);
            int option2 = Utils.integer("For one participating garage write 1, for more than one participating garage write 2: ");
            if(option2 == 1){
                for (int i = 0; i < listGarage.size(); i++) {
                    System.out.println(i +". "+ listGarage.get(i).getName());
                }
                int numberG = Utils.integer("Write de number of the chosen garage: ");
                raceE.addOneGarage(listGarage.get(numberG));
                System.out.println("All the cars are ready, Race cars " + raceE.getName() + " starts!");
                raceE.startRace();
            }
            else if (option2 == 2){
                int numberG = 0;
                do {
                    System.out.println("No more garages write 1");
                    numberG = Utils.integer("Write de number of a chosen garage: ");
                    raceE.addMoreThanOneGarage(listGarage.get(numberG));
                } while (numberG != 1);
                System.out.println("All the cars are ready, Race cars " + raceE.getName() + " starts!");
                raceE.startRace();
            }
        }

        List<Race> racesList = new ArrayList<>();
        String nameC = Utils.string("Qué nombre le quieres dar al torneo: ");

        Championship championship = new Championship(nameC,racesList);


    }



    public static void main(String[] args) {
        Control control = new Control();
        control.menu();
    }
}
