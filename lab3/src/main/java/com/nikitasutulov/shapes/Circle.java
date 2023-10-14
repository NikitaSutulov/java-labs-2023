package com.nikitasutulov.shapes;

public class Circle extends Shape {

    public float radius;

    public Circle(String shapeColor, float radius) {
        super(shapeColor);
        this.radius = radius;
    }

    @Override
    public float calcArea() {
        return (float) Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle\t\t| color: " + shapeColor + ", radius: " + radius + ", area: " + calcArea();
    }
}
