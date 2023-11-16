package com.nikitasutulov.lab7;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static Consumer<String[]> printFilteredArray = (array) -> {
        System.out.println("The filtered array is:");
        Arrays.stream(array).forEach(Main::printWordWithTab);
    };

    public static Predicate<Character> isCharLatin = ch -> Character.toString(ch).matches("[A-Za-z]");

    public static Function<String[], String[]> filterLatin = (words) ->
            Arrays.stream(words)
                    .filter(Main::isWordLatin)
                    .toArray(String[]::new);

    public static Predicate<Character> isCharVowel = ch -> Character.toString(ch).matches("[AIEOUaieou]");

    public static Function<String[], String[]> filterVowelsEqualConsonants = (words) ->
            Arrays.stream(words)
                    .filter(Main::isVowelsEqualConsonants)
                    .toArray(String[]::new);

    public static Function<String, String[]> filterLine = (line) ->
            filterVowelsEqualConsonants.apply(filterLatin.apply(line.split(" ")));

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            String[] filteredWords = filterLine.apply(line);
            printFilteredArray.accept(filteredWords);
        }
    }

    // methods to show the ability to pass pointers to them as arguments
    public static boolean isVowelsEqualConsonants(String word) {
        long vowelCount = word.chars().filter(c -> isCharVowel.test((char) c)).count();
        long consonantCount = word.length() - vowelCount;
        return vowelCount == consonantCount;
    }

    public static boolean isWordLatin(String word) {
        return word.chars().allMatch(c -> isCharLatin.test((char) c));
    }

    public static void printWordWithTab(String word) {
        System.out.print(word + "\t");
    }
}