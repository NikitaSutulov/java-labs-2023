package com.nikitasutulov;

public abstract class Shape implements Drawable {
    public final String shapeColor;
    public Shape (String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public abstract float calcArea();

    @Override
    public String toString() {
        return super.toString();
    }
}