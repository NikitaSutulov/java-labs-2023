package com.nikitasutulov;

import java.util.Objects;

public final class Person {
    private final String name;
    private final String surname;
    private final int age;

    public Person(String name, String surname, int age) {
        this.name = (name == null) ? "" : name;
        this.surname = (surname == null) ? "" : surname;
        this.age = Math.max(age, 0); // if negative or null age sent to constructor, the object field will be set to 0
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.getAge() &&
                (surname == null ? person.getSurname() == null : surname.equals(person.getSurname())) &&
                (name == null ? person.getName() == null : name.equals(person.getName()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age);
    }
}
