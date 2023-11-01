package com.nikitasutulov.utilities;

import com.nikitasutulov.shapes.Shape;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DatasetWriter {
    public void writeDatasetToFile(Shape[] dataset, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(dataset);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
