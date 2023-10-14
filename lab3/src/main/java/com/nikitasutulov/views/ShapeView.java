package com.nikitasutulov.views;

import com.nikitasutulov.shapes.Shape;

public class ShapeView {
    public static final String INPUT_COMMAND_TEXT = "Enter the command.";
    public static final String UNKNOWN_COMMAND_TEXT = "Unknown command!";
    public static final String HELP_ADVICE = "Type 'help' to get the list of all commands available";
    public static final String SHOW_DATASET = "Showing the dataset";
    public static final String AREA_SUM_ALL = "The sum of areas of all the shapes: ";
    public static final String AREA_SUM_CERTAIN = "The sum of areas of all ";
    public static final String TRIANGLES = "triangles: ";
    public static final String RECTANGLES = "rectangles: ";
    public static final String CIRCLES = "circles: ";

    public static final String SORT_AREA = "Showing the dataset sorted by area ascending";
    public static final String SORT_COLOR = "Showing the dataset sorted by shape color";
    public static final String HELP = """
            List of all commands:
            print;
            sum (all, t, r, c);
            sort (area, color);
            exit;
            help.
            """;
    public static final String EXITING = "Exiting...";
    public static final String INVALID_COMMAND = "Invalid command. Type 'help' for a list of available commands.";

    public void printDatasetWithMessage(String startingMessage, Shape[] dataset) {
        System.out.println(startingMessage);
        for (Shape s: dataset) {
            System.out.println(s.toString());
        }
        System.out.println();
    }

    public void printNumberWithMessage(String message, float num) {
        System.out.println(message + num);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
