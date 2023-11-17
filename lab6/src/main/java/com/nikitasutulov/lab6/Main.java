package com.nikitasutulov.lab6;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static final String COMMAND_ADD = "add";
    public static final String COMMAND_PTRANSLATE = "ptranslate";
    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_HELP = "help";
    public static final String COMMAND_PRINT = "print";
    public static final String HELP = "Available commands: add, ptranslate, exit, help, print";

    public static void main(String[] args) {
        Translator translator = new Translator();
        addDefaultWordPairs(translator);

        try(Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println(HELP);
                System.out.print("Enter command: ");
                String command = scanner.nextLine();
                switch (command) {
                    case COMMAND_ADD -> {
                        try {
                            addWordPair(translator, scanner);
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                    }
                    case COMMAND_PTRANSLATE -> {
                        try {
                            pTranslate(translator, scanner);
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                    }
                    case COMMAND_EXIT -> {}
                    case COMMAND_HELP -> {
                        continue;
                    }
                    case COMMAND_PRINT -> printDictionary(translator);
                    default -> System.out.println("Unknown command!");
                }
                if (command.equals(COMMAND_EXIT)) {
                    break;
                }
            }
        }
    }

    private static void printDictionary(Translator translator) {
        // ANSI escape codes for colors
        String reset = "\u001B[0m";
        String yellow = "\u001B[33m";
        String blue = "\u001B[34m";
        Map<String, String> dictionary = translator.getDictionary();
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
            System.out.print(blue + "English: " + reset + entry.getKey() + "; ");
            System.out.println(yellow + "Ukrainian: " + reset + entry.getValue());
        }
    }

    private static void pTranslate(Translator translator, Scanner scanner) {
        System.out.print("Enter phrase in English: ");
        String englishPhrase = scanner.nextLine();
        String ukrainianTranslation = translator.translatePhrase(englishPhrase);
        System.out.println("Ukrainian translation: " + ukrainianTranslation);
    }

    private static void addWordPair(Translator translator, Scanner scanner) {
        System.out.print("Enter word in English: ");
        String englishWord = scanner.nextLine();
        System.out.print("Enter word in Ukrainian: ");
        String ukrainianWord = scanner.nextLine();
        translator.addWordPair(englishWord, ukrainianWord);
    }

    private static void addDefaultWordPairs(Translator translator) {
        translator.addWordPair("hello", "привіт");
        translator.addWordPair("world", "світ");
        translator.addWordPair("java", "джава");
        translator.addWordPair("programming", "програмування");
        translator.addWordPair("language", "мова");
        translator.addWordPair("computer", "комп'ютер");
        translator.addWordPair("science", "наука");
        translator.addWordPair("code", "код");
        translator.addWordPair("developer", "розробник");
        translator.addWordPair("keyboard", "клавіатура");
        translator.addWordPair("mouse", "миша");
        translator.addWordPair("algorithm", "алгоритм");
        translator.addWordPair("is", "є");
        translator.addWordPair("fun", "цікаво");
        translator.addWordPair("learning", "вивчення");
        translator.addWordPair("new", "новий");
        translator.addWordPair("skills", "навички");
    }
}
