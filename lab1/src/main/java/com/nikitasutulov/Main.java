package com.nikitasutulov;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] filteredWords = filterLine(line);
        System.out.println("The filtered array is:");
        for (String word: filteredWords) {
            System.out.print(word + "\t");
        }
    }

    public static String[] filterLine(String line) {
        String[] words = line.split(" ");
        words = filterLatin(words);
        words = filterVowelsEqualConsonants(words);
        return words;
    }


    public static String[] filterLatin(String[] words) {
        String[] latinWords = new String[words.length];
        int validIndex = 0;
        for (String word: words) {
            char[] chars = word.toCharArray();
            boolean isWordLatin = true;
            for (char ch : chars) {
                if (!isCharLatin(ch)) {
                    isWordLatin = false;
                    break;
                }
            }
            if (isWordLatin) {
                latinWords[validIndex++] = word;
            }
        }
        return removeNulls(latinWords);
    }

    public static boolean isCharLatin(char ch) {
        return Character.toString(ch).matches("[A-Za-z]");
    }

    public static String[] filterVowelsEqualConsonants(String[] words) {
        String[] filteredWords = new String[words.length];
        int validIndex = 0;
        for (String word: words) {
            char[] chars = word.toCharArray();
            int vowelCounter = 0;
            int consonantCounter = 0;
            for (char ch : chars) {
                if (isCharVowel(ch)) vowelCounter++;
                else consonantCounter++;
            }
            if (vowelCounter == consonantCounter) {
                filteredWords[validIndex++] = word;
            }
        }
        return removeNulls(filteredWords);
    }

    public static boolean isCharVowel(char ch) {
        return Character.toString(ch).matches("[AIEOUaieou]");
    }

    public static String[] removeNulls(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                return Arrays.copyOfRange(array, 0, i);
            }
        }
        return array;
    }
}