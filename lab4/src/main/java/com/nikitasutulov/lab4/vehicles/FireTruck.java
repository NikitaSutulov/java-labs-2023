package com.nikitasutulov.lab4.vehicles;

import com.nikitasutulov.lab4.passengers.Firefighter;

public class FireTruck<T extends Firefighter> extends Car<T> {
    public FireTruck(String name, int maxSeats) {
        super(name, maxSeats);
    }
}