package com.nikitasutulov.utilities;

import com.nikitasutulov.views.ShapeView;

import java.util.Scanner;

public class InputManager {
    public static final String PRINT_COMMAND = "print";
    public static final String SUM_COMMAND_START = "sum ";
    public static final String ALL = "all";
    public static final String TRIANGLES = "t";
    public static final String CIRCLES = "c";
    public static final String RECTANGLES = "r";
    public static final String SORT_COMMAND_START = "sort ";
    public static final String COLOR = "color";
    public static final String AREA = "area";
    public static final String SAVE = "save";
    public static final String LOAD = "load";
    public static final String EXIT = "exit";
    public static final String HELP = "help";

    private final Scanner scanner = new Scanner(System.in);
    public String getCommandFromInput() {
//        Scanner scanner = new Scanner(System.in);
        String enteredText;

        while (true) {
            System.out.println(ShapeView.INPUT_COMMAND_TEXT);
            enteredText = scanner.nextLine();

            if (isValidCommand(enteredText)) {
                return enteredText;
            } else {
                System.out.println(ShapeView.UNKNOWN_COMMAND_TEXT);
                System.out.println(ShapeView.HELP_ADVICE);
            }
        }
    }

    private boolean isValidCommand(String command) {
        String[] validCommands = {PRINT_COMMAND, SUM_COMMAND_START + ALL, SUM_COMMAND_START + TRIANGLES,
                SUM_COMMAND_START + RECTANGLES, SUM_COMMAND_START + CIRCLES, SORT_COMMAND_START + AREA,
                SORT_COMMAND_START + COLOR, SAVE, LOAD, EXIT, HELP};

        for (String validCommand : validCommands) {
            if (command.matches(validCommand)) {
                return true;
            }
        }
        return false;
    }

    public String getFileNameFromInput(String query) {
        System.out.println(query);
        return scanner.nextLine();
    }
}
