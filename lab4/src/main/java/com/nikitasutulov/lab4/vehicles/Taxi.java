package com.nikitasutulov.lab4.vehicles;

import com.nikitasutulov.lab4.passengers.Human;

public class Taxi<T extends Human> extends Car<T> {
    public Taxi(String name, int maxSeats) {
        super(name, maxSeats);
    }
}
