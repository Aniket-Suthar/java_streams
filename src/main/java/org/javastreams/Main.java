package org.javastreams;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<Book> books = BookLoader.loadBooks("/home/aniketsuthar/Documents/Java_Streams/Book Store Inventory/updated_books_data.csv");
        String analysisResult = BookAnalysis.analyzeBooks(books);
        System.out.println(analysisResult);
        System.out.println("✅ Analysis complete! Results saved in output.txt");
    }
}