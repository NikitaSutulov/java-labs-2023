package com.nikitasutulov.lab4.passengers;

import com.nikitasutulov.lab4.vehicles.Vehicle;

public class Human {
    private final String name;

    private Vehicle currentVehicle = null;

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public void setCurrentVehicle(Vehicle vehicle) {
        this.currentVehicle = vehicle;
    }
}
