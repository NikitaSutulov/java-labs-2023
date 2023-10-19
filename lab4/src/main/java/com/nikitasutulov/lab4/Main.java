package com.nikitasutulov.lab4;

import com.nikitasutulov.lab4.passengers.Firefighter;
import com.nikitasutulov.lab4.passengers.Human;
import com.nikitasutulov.lab4.passengers.PoliceOfficer;
import com.nikitasutulov.lab4.roads.Road;
import com.nikitasutulov.lab4.vehicles.Bus;
import com.nikitasutulov.lab4.vehicles.FireTruck;
import com.nikitasutulov.lab4.vehicles.PoliceCar;
import com.nikitasutulov.lab4.vehicles.Taxi;

public class Main {
    public static void main(String[] args) {
        Bus<Human> bus = new Bus<>("Bus1", 20);
        Taxi<Human> taxi = new Taxi<>("Taxi1", 4);
        FireTruck<Firefighter> fireTruck = new FireTruck<>("FireTruck1", 6);
        PoliceCar<PoliceOfficer> policeCar = new PoliceCar<>("PoliceCar1", 2);

        Human terry = new Human("Terry Davis");
        Human linus = new Human("Linus Torvalds");
        Firefighter someFireFighter = new Firefighter("Firefighter 1");
        PoliceOfficer somePoliceOfficer = new PoliceOfficer("Police Officer 1");
        Firefighter firefighterOnVacation = new Firefighter("Free Firefighter");
        PoliceOfficer policeOfficerOnVacation = new PoliceOfficer("Free Police Officer");

        bus.embark(terry);
        bus.embark(linus);
        taxi.embark(firefighterOnVacation);
        taxi.embark(policeOfficerOnVacation);
        fireTruck.embark(someFireFighter);
        policeCar.embark(somePoliceOfficer);

        Road road = new Road();
        road.addVehicleToRoad(bus);
        road.addVehicleToRoad(taxi);
        road.addVehicleToRoad(fireTruck);
        road.addVehicleToRoad(policeCar);

        printCountOfHumans(road);

        road.removeVehicleFromRoad(taxi);
        printCountOfHumans(road);
        taxi.disembark(firefighterOnVacation);
        taxi.disembark(policeOfficerOnVacation);

        road.removeVehicleFromRoad(bus);
        printCountOfHumans(road);

        bus.disembark(terry);
        bus.disembark(linus);
        bus.embark(firefighterOnVacation);
        bus.embark(policeOfficerOnVacation);
        road.addVehicleToRoad(bus);

        printCountOfHumans(road);

        road.removeVehicleFromRoad(bus);
        printCountOfHumans(road);
        bus.disembark(firefighterOnVacation);
        bus.disembark(policeOfficerOnVacation);

        road.removeVehicleFromRoad(fireTruck);
        printCountOfHumans(road);
        fireTruck.embark(firefighterOnVacation);
        road.addVehicleToRoad(fireTruck);
        printCountOfHumans(road);

        road.removeVehicleFromRoad(policeCar);
        printCountOfHumans(road);
        policeCar.embark(policeOfficerOnVacation);
        road.addVehicleToRoad(policeCar);
        printCountOfHumans(road);

    }

    public static void printCountOfHumans(Road road) {
        int countOfHumans = road.getCountOfHumans();
        System.out.println("Total number of humans on the road: " + countOfHumans);
    }
}