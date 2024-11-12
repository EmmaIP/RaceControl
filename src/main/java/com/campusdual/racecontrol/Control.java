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
        System.out.println("\nBienvenido a RaceControl");
        System.out.println("Cómo se juega:");
        System.out.println("Da de alta los vehículos");
        System.out.println("Crea un garaje y añádeselos");
        System.out.println("Escoge una carrera");
        System.out.println("Que empiece el torneo");

        List<Car> carsList = new ArrayList<>();
        String brand = Utils.string("Escribe la marca del vehículo: ");
        String model = Utils.string("Escribe el modelo: ");
        Car car = new Car(brand, model);

        String nameG = Utils.string("Escribe el nombre del garaje al que pertenecen: ");
        Garage garage = new Garage(nameG, carsList);
        garage.addCars(car);

        int option = Utils.integer("Para carrera estándar escribe 1 para carrera eliminatoria escribe 2: ");
        if (option == 1) {
            System.out.println("Las carreras estándar tienen una duración determinada");
            System.out.println("Gana el vehículo que más distancia haya recorrido");
            String nameR = Utils.string("Qué nombre le das a esta carrera: ");
            int durationR = Utils.integer("Cúanto quieres que dure: ");
            StandardRace raceS = new StandardRace(nameR, durationR);
            int option1 = Utils.integer("Si solo participa un garaje escribe 1 si participa más de uno, escribe 2: ");
            if(option1 == 1){
                raceS.addOneGarage(garage);
            }
            else if (option1 == 2){
                raceS.addMoreThanOneGarage(garage);
            }
            else {
                System.out.println("Opción no válida.");
            }
        } else if (option == 2) {
            System.out.println("En las carreras eliminatorias tienen un tiempo de calentamiento");
            System.out.println("Al terminar este calentamiento, cada minuto se elimina el vehículo que quede en última posición");
            System.out.println("Gana el último vehículo en ser eliminado");
            String nameE = Utils.string("Qué nombre le das a esta carrera: ");
            int minutesE = Utils.integer("Cúantos minutos de calentamiento tiene: ");
            StandardRace raceE = new StandardRace(nameE, minutesE);
            int option1 = Utils.integer("Si solo participa un garaje escribe 1 si participa más de uno, escribe 2: ");
            if(option1 == 1){
                raceE.addOneGarage(garage);
            }
            else if (option1 == 2){
                raceE.addMoreThanOneGarage(garage);
            }
            else {
                System.out.println("Opción no válida.");
            }

        }else {
            System.out.println("Opción no válida.");
        }


    }
    public static void main(String[] args) {

    }
}
