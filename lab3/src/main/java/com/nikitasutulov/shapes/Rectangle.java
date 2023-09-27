package com.nikitasutulov.shapes;

public class Rectangle extends Shape {
    public float length;
    public float width;
    public Rectangle(String shapeColor, float length, float width) {
        super(shapeColor);
        this.length = length;
        this.width = width;
    }

    @Override
    public float calcArea() {
        return length * width;
    }

    @Override
    public void draw() {
        System.out.println("Rectangle draw method call");
    }

    @Override
    public String toString() {
        return "Rectangle\t| color: " + shapeColor + ", length: " + length + ", width: " + width + ", area: " + calcArea();
    }
}
