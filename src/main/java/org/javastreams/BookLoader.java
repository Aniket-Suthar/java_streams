package org.javastreams;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BookLoader {
    private static int parseInteger(String value) {
        try {
            return Integer.parseInt(value.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static double parseDouble(String value) {
        try {
            return Double.parseDouble(value.replaceAll("[^0-9.]", ""));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static List<Book>loadBooks(String filename) throws IOException {
        List<Book> books;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine(); // skipping the header line
            books = br.lines().map(line -> {
                String[] parts = line.split(",");

                return new Book(
                        parts[0],                          // Title
                        parts[1],                          // Author
                        parts[2],                          // Category
                        parseInteger(parts[3]),         // Price (handling non-numeric values)
                        parseInteger(parts[6]),
                        parts[7],                          // Edition
                        parseInteger(parts[5]),         // Copies Left
                        parseInteger(parts[9]),         // Wished Users
                        "Yes".equalsIgnoreCase(parts[10]), // Discount (true if "Yes")
                        parseDouble(parts[11]),       // Ratings
                        parseDouble(parts[12])        // Reviews
                );
            }).collect(Collectors.toList());
        }
        return books;
    }
}

