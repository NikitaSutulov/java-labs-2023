package com.nikitasutulov.lab4.vehicles;

import com.nikitasutulov.lab4.passengers.PoliceOfficer;

public class PoliceCar<T extends PoliceOfficer> extends Car<T> {
    public PoliceCar(String name, int maxSeats) {
        super(name, maxSeats);
    }
}
