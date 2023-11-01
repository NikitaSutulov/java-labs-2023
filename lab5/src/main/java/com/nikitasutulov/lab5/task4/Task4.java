package com.nikitasutulov.lab5.task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {
    public static void main(String[] args) {
        String url = "https://google.com";

        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            Map<String, Integer> tagFrequencyMap = new HashMap<>();

            Pattern tagPattern = Pattern.compile("<(\\w+)(\\s|>)");

            while ((line = reader.readLine()) != null) {
                Matcher matcher = tagPattern.matcher(line);
                while (matcher.find()) {
                    String tagName = matcher.group(1);
                    tagFrequencyMap.put(tagName, tagFrequencyMap.getOrDefault(tagName, 0) + 1);
                }
            }

            reader.close();

            Map.Entry<String, Integer>[] entryArray = tagFrequencyMap.entrySet().toArray(new Map.Entry[0]);

            Arrays.sort(entryArray, Map.Entry.comparingByKey());

            System.out.println("Tags in lexicographic order");
            for (Map.Entry<String, Integer> entry : entryArray) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            Arrays.sort(entryArray, Map.Entry.comparingByValue());

            System.out.println("\nTags in ascending order of appearance frequency");
            for (Map.Entry<String, Integer> entry : entryArray) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

