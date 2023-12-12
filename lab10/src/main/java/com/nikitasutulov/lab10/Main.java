package com.nikitasutulov.lab10;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String originalString = "Sample string 1";
        System.out.println("The original string: " + originalString);
        originalString = changeStringValue(originalString, "New Value");
        System.out.println("The string after changes: " + originalString);
        try (Scanner scanner = new Scanner(System.in)){
            System.out.print("Enter a new string: ");
            String userInputString = scanner.nextLine();
            System.out.println("The original string: " + userInputString);
            System.out.print("Enter a new value for the string: ");
            String newValue = scanner.nextLine();
            userInputString = changeStringValue(userInputString, newValue);
            System.out.println("The string after changes: " + userInputString);
        }
    }

    private static String changeStringValue(String str, String newValue) {
        try {
            Field valueField = String.class.getDeclaredField("value");
            valueField.setAccessible(true);
            char[] newValueArray = newValue.toCharArray();
            valueField.set(str, newValueArray);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return str;
    }
}
