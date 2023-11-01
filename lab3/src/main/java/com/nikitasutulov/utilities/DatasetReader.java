package com.nikitasutulov.utilities;

import com.nikitasutulov.shapes.Shape;

import java.io.*;

public class DatasetReader {
    public Shape[] readDatasetFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Shape[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
