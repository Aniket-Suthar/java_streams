package org.javastreams;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class BookLoader {
    private static int parseInteger(String value, int defaultValue) {
        try {
            return Integer.parseInt(value.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private static double parseDouble(String value, double defaultValue) {
        try {
            return Double.parseDouble(value.replaceAll("[^0-9.]", ""));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static List<Book> loadBooks(String filename) {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {
            br.readLine(); // Skip header line
            books = br.lines().map(line -> {
                String[] parts = line.split(",");

                return new Book(
                        parts[0],                          // Title
                        parts[1],                          // Author
                        parts[2],                          // Category
                        parseInteger(parts[3], 0),         // Price (handles non-numeric values)
                        parseInteger(parts[6], 0),         // Pages (handles non-numeric values)
                        parts[7],                          // Edition
                        parseInteger(parts[5], 0),         // Copies Left
                        parseInteger(parts[9], 0),         // Wished Users
                        "Yes".equalsIgnoreCase(parts[10]), // Discount (true if "Yes")
                        parseDouble(parts[11], 0.0),       // Ratings
                        parseDouble(parts[12], 0.0)        // Reviews
                );
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
}

