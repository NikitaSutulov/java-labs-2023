package com.nikitasutulov;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return Float.compare(shape1.calcArea(), shape2.calcArea());
    }
}
