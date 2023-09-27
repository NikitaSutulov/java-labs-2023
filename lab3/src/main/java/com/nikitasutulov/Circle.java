package com.nikitasutulov;

public class Circle extends Shape {

    public float radius;

    public Circle(String shapeColor, float radius) {
        super(shapeColor);
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Circle draw method call");
    }

    @Override
    public float calcArea() {
        return (float) Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle | color: " + shapeColor + ", radius: " + radius;
    }
}
