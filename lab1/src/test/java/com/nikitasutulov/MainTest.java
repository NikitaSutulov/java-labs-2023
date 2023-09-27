package com.nikitasutulov;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.nikitasutulov.Main.*;

public class MainTest {
    @Test
    public void testFilterLatin() {
        String[] words = {"Terry", "DAVIS", "123", "TempleOS", "програмування", "234*&/"};
        String[] filtered = filterLatin(words);
        String[] expected = {"Terry", "DAVIS", "TempleOS"};
        assertArrayEquals(expected, filtered);
    }

    @Test
    public void testIsCharLatin() {
        char latinChar = 'A';
        char nonLatinChar = '1';
        assertTrue(isCharLatin(latinChar));
        assertFalse(isCharLatin(nonLatinChar));
    }

    @Test
    public void testFilterVowelsEqualConsonants() {
        String[] words = {"Baobab", "GitHub", "remote", "Bartholomew", "Java"};
        String[] filtered = filterVowelsEqualConsonants(words);
        String[] expected = {"Baobab", "remote", "Java"};
        assertArrayEquals(expected, filtered);
    }

    @Test
    public void testIsCharVowel() {
        char vowelChar = 'A';
        char nonVowelChar = 'B';
        assertTrue(isCharVowel(vowelChar));
        assertFalse(isCharVowel(nonVowelChar));
    }

    @Test
    public void testRemoveNulls() {
        String[] array = {"hello", "world", "Java", null, null, null};
        String[] cleaned = getArrayWithoutNullsAtTheEnd(array);
        String[] expected = {"hello", "world", "Java"};
        assertArrayEquals(expected, cleaned);
    }
}
