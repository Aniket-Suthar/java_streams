package org.javastreams;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Book> books = BookLoader.loadBooks("/home/aniketsuthar/Documents/Java_Streams/Book Store Inventory/updated_books_data.csv");
        String analysisResult = BookAnalysis.analyzeBooks(books);
        OutputWriter.saveOutput("output.txt", analysisResult);
        System.out.println("✅ Analysis complete! Results saved in output.txt");
    }
}