package com.nikitasutulov.lab4.vehicles;

import com.nikitasutulov.lab4.passengers.Human;

public abstract class Car<T extends Human> extends Vehicle<T> {
    public Car(String name, int maxSeats) {
        super(name, maxSeats);
    }
}
