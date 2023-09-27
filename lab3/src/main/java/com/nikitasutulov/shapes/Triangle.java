package com.nikitasutulov.shapes;

public class Triangle extends Shape {
    public float side1;
    public float side2;
    public float side3;
    public Triangle(String shapeColor, float side1, float side2, float side3) {
        super(shapeColor);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public float calcArea() {
        float p = halfPerimeter();
        return (float) Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }

    private float halfPerimeter() {
        return 0.5f * (side1 + side2 + side3);
    }

    @Override
    public void draw() {
        System.out.println("Triangle draw method call");
    }

    @Override
    public String toString() {
        return "Triangle\t| color: " + shapeColor + ", sides: " + side1 + ", " + side2 + ", " + side3 + ", area: " + calcArea();
    }
}
