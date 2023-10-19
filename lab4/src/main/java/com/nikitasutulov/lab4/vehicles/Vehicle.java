package com.nikitasutulov.lab4.vehicles;

import com.nikitasutulov.lab4.passengers.Human;
import com.nikitasutulov.lab4.roads.Road;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle<T extends Human> {
    private final String name;
    private final int maxSeats;
    private final List<T> passengers;
    private Road currentRoad = null;

    public Vehicle(String name, int maxSeats) {
        this.name = name;
        this.maxSeats = maxSeats;
        this.passengers = new ArrayList<>();
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public void embark(T passenger) {
        if (passenger.getCurrentVehicle() != null) {
            throw new IllegalArgumentException("Passenger is already embarked.");
        }
        if (passengers.size() == maxSeats) {
            throw new IllegalStateException("All seats are occupied.");
        }
        passengers.add(passenger);
        passenger.setCurrentVehicle(this);
        System.out.println("Successfully embarked passenger " + passenger.getName() + " to " + name);
    }

    public void disembark(T passenger) {
        if (passengers.remove(passenger)) {
            passenger.setCurrentVehicle(null);
            System.out.println("Successfully disembarked passenger " + passenger.getName() + " from " + name);
        } else {
            throw new IllegalArgumentException("Passenger is not on board.");
        }
    }

    public String getName() {
        return name;
    }

    public Road getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Road road) {
        this.currentRoad = road;
    }

    public List<T> getPassengers() {
        return passengers;
    }
}
