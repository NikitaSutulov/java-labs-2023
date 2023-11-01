package com.nikitasutulov.models;

import com.nikitasutulov.comparators.AreaComparator;
import com.nikitasutulov.comparators.ColorComparator;
import com.nikitasutulov.shapes.Circle;
import com.nikitasutulov.shapes.Rectangle;
import com.nikitasutulov.shapes.Shape;
import com.nikitasutulov.shapes.Triangle;

import java.util.Arrays;
import java.util.Random;

public class ShapeModel {
    public static final int DATASET_SIZE = 15;
    public static final int SHAPES_TYPES_COUNT = 3;
    public static final float SIZE_RANGE = 25f;
    public static final String[] COLORS = {"Red", "Blue", "Green", "Yellow", "Orange", "Purple"};
    private Shape[] dataset;
    public ShapeModel() {
        dataset = new Shape[DATASET_SIZE];
        generateRandomDataset();
    }

    private void generateRandomDataset() {
        Random random = new Random();

        for (int i = 0; i < DATASET_SIZE; i++) {
            int choice = random.nextInt(SHAPES_TYPES_COUNT);
            String randomColor = generateRandomColor();

            switch (choice) {
                case 0:
                    float side1, side2, side3;
                    do {
                        side1 = random.nextFloat() * SIZE_RANGE;
                        side2 = random.nextFloat() * SIZE_RANGE;
                        side3 = random.nextFloat() * SIZE_RANGE;
                    } while (!isValidTriangle(side1, side2, side3));
                    dataset[i] = new Triangle(randomColor, side1, side2, side3);
                    break;
                case 1:
                    dataset[i] = new Circle(randomColor, random.nextFloat() * SIZE_RANGE);
                    break;
                case 2:
                    dataset[i] = new Rectangle(randomColor, random.nextFloat() * SIZE_RANGE, random.nextFloat() * SIZE_RANGE);
                    break;
            }
        }
    }

    private String generateRandomColor() {
        Random random = new Random();
        int index = random.nextInt(COLORS.length);
        return COLORS[index];
    }

    private static boolean isValidTriangle(float side1, float side2, float side3) {
        return side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1;
    }

    public Shape[] getDataset() {
        return dataset;
    }

    public void setDataset(Shape[] dataset) {
        this.dataset = dataset;
    }

    public float calculateAllShapesAreaSum() {
        float areaSum = 0f;
        for (Shape s: dataset) {
           areaSum += s.calcArea();
        }
        return areaSum;
    }

    public float calculateTrianglesAreaSum() {
        float areaSum = 0f;
        for (Shape s: dataset) {
            if (s instanceof Triangle) {
                areaSum += s.calcArea();
            }
        }
        return areaSum;
    }

    public float calculateRectanglesAreaSum() {
        float areaSum = 0f;
        for (Shape s: dataset) {
            if (s instanceof Rectangle) {
                areaSum += s.calcArea();
            }
        }
        return areaSum;
    }

    public float calculateCirclesAreaSum() {
        float areaSum = 0f;
        for (Shape s: dataset) {
            if (s instanceof Circle) {
                areaSum += s.calcArea();
            }
        }
        return areaSum;
    }

    public Shape[] getDatasetSortedByColor() {
        Shape[] datasetCopy = Arrays.copyOf(dataset, dataset.length);
        Arrays.sort(datasetCopy, new ColorComparator());
        return datasetCopy;
    }

    public Shape[] getDatasetSortedByArea() {
        Shape[] datasetCopy = Arrays.copyOf(dataset, dataset.length);
        Arrays.sort(datasetCopy, new AreaComparator());
        return datasetCopy;
    }

}
