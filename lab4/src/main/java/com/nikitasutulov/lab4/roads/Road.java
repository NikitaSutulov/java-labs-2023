package com.nikitasutulov.lab4.roads;

import com.nikitasutulov.lab4.passengers.Human;
import com.nikitasutulov.lab4.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Road {
    public List<Vehicle<? extends Human>> vehiclesInRoad = new ArrayList<>();

    public void addVehicleToRoad(Vehicle<? extends Human> vehicle) {
        if (vehicle.getCurrentRoad() != null) {
            throw new IllegalArgumentException("Vehicle is already on a road.");
        }
        vehiclesInRoad.add(vehicle);
        vehicle.setCurrentRoad(this);
        System.out.println("Successfully added a vehicle to the road " + vehicle.getName());
    }

    public void removeVehicleFromRoad(Vehicle vehicle) {
        if (vehiclesInRoad.remove(vehicle)) {
            vehicle.setCurrentRoad(null);
            System.out.println("Successfully removed a vehicle from the road " + vehicle.getName());
        } else {
            throw new IllegalArgumentException("Vehicle is not on the road.");
        }
    }

    public int getCountOfHumans() {
        int count = 0;
        for (Vehicle<? extends Human> vehicle : vehiclesInRoad) {
            count += vehicle.getOccupiedSeats();
        }
        return count;
    }
}