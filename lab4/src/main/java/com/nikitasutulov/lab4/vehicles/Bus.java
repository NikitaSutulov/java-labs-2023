package com.nikitasutulov.lab4.vehicles;

import com.nikitasutulov.lab4.passengers.Human;

public class Bus<T extends Human> extends Vehicle<T> {
    public Bus(String name, int maxSeats) {
        super(name, maxSeats);
    }
}
