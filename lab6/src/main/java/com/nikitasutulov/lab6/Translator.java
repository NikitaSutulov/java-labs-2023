package com.nikitasutulov.lab6;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class Translator {
    private final Map<String, String> dictionary;

    public Translator() {
        this.dictionary = new HashMap<>();
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }

    public void addWordPair(String englishWord, String ukrainianTranslation) {
        validateEnglishWord(englishWord);
        validateUkrainianWord(ukrainianTranslation);
        dictionary.put(englishWord, ukrainianTranslation);
    }

    private void validateUkrainianWord(String ukrainianTranslation) {
        if (!ukrainianTranslation.matches("[А-Яа-яҐґЄєЇїІі']+")) {
            throw new InputMismatchException("Ukrainian word was expected to be written with cyrillic characters");
        }
    }

    private void validateEnglishWord(String englishWord) {
        if (!englishWord.matches("[A-Za-z]+")) {
            throw new InputMismatchException("English word was expected to be written with latin characters");
        }
    }

    public String translatePhrase(String englishPhrase) {
        StringBuilder translatedPhrase = new StringBuilder();
        String[] words = englishPhrase.split("\\s+");
        for (String word : words) {
            String translatedWord = dictionary.get(word);
            if (translatedWord == null) {
                throw new InputMismatchException("No translation found for word '" + word + "'");
            }
            translatedPhrase.append(translatedWord).append(" ");
        }
        return translatedPhrase.toString().trim();
    }
}
