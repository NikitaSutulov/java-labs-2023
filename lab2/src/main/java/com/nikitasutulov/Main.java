package com.nikitasutulov;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        Person terry = new Person("Terry", "Davis", 48);

        Gson gson = new Gson();
        String terrySerialized = gson.toJson(terry);
        System.out.println("Serialization result: " + terrySerialized);
        Person terryDeserialized = gson.fromJson(terrySerialized, Person.class);

        boolean isEqual = terry.equals(terryDeserialized);
        System.out.println("Is object equal to the deserialized object? " + isEqual);
    }
}