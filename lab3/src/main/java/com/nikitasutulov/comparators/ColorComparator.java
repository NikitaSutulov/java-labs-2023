package com.nikitasutulov.comparators;

import com.nikitasutulov.shapes.Shape;

import java.util.Comparator;

public class ColorComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return shape1.shapeColor.compareTo(shape2.shapeColor);
    }
}
