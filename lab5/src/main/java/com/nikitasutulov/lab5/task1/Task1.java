package com.nikitasutulov.lab5.task1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        System.out.println("Please enter the file name:");
        String filePath;
        try (Scanner scanner = new Scanner(System.in)) {
            filePath = scanner.nextLine();
        }
        String[] lines;
        try {
            lines = readLinesFromFile(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
        String result = getLineWithTheMostWords(lines);
        System.out.println("The line with the most words is:\n" + result + "\n" + result.split(" ").length + " words");
    }

    private static String getLineWithTheMostWords(String[] lines) {
        int maxWords = -1;
        int maxWordsIndex = -1;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int currentLineLength = line.split(" ").length;
            if (currentLineLength > maxWords) {
                maxWords = currentLineLength;
                maxWordsIndex = i;
            }
        }
        return lines[maxWordsIndex];
    }

    private static String[] readLinesFromFile(String filePath) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> lines = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            return lines.toArray(new String[]{});
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}