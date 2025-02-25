package org.javastreams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookAnalysis {
    public static String analyzeBooks(List<Book> books) {
        StringBuilder output = new StringBuilder();

        output.append("\n================ Popular Authors with Diverse Categories ================\n");
        Map<String, Set<String>> popularAuthors = books.stream()
                .filter(b -> b.ratings >= 10 && b.discount)
                .collect(Collectors.groupingBy(b -> b.author,
                        Collectors.mapping(b -> b.category, Collectors.toSet())));

        popularAuthors.entrySet().stream()
                .filter(e -> e.getValue().size() >= 3)
                .forEach(e -> output.append(e.getKey()).append(" -> ").append(e.getValue()).append("\n"));

        output.append("\n================ Most Reviewed Book per Category ================\n");
        books.stream().
                collect(Collectors.groupingBy(b -> b.category,
                        Collectors.maxBy(Comparator.comparing((Book b) -> b.reviews).thenComparing(b -> b.ratings))))
                .forEach((category, book) -> output.append(category).append(" -> ").append(book.get().title).append(" by ")
                        .append(book.get().author).append(" (Reviews: ").append(book.get().reviews).append(")\n"));

        output.append("\n================ Impact of Discount and Edition on Availability ================\n");
        books.stream().
                collect(Collectors.groupingBy(b -> b.edition))
                .forEach((edition, list) -> {

                    int totalDiscounted = list.stream().
                            filter(b -> b.discount).
                            mapToInt(b -> b.copiesLeft).sum();

                    double avgNonDiscounted = list.stream().
                            filter(b -> !b.discount).
                            mapToInt(b -> b.copiesLeft).average().orElse(0);

                    output.append("Edition: ").append(edition).append(" -> Discounted Total: ").append(totalDiscounted)
                            .append(", Avg Non-Discounted: ").append(avgNonDiscounted).append("\n");
                });

        output.append("\n================ Best-Selling Authors with High Stock ================\n");
        int top10Threshold = books.stream().
                mapToInt(b -> b.wishedUsers).
                sorted().
                skip((long) (books.size() * 0.9)).
                findFirst().
                orElse(0);

        books.stream().collect(Collectors.groupingBy(b -> b.author,
                        Collectors.summingInt(b -> b.wishedUsers)))
                .entrySet().stream().
                filter(e -> e.getValue() >= top10Threshold)
                .forEach(e -> output.append(e.getKey()).append(" -> Wished Users: ").append(e.getValue()).append("\n"));

        output.append("\n================ Categorizing Books by Price and Length ================\n");
        Function<Book, String> priceCategory = b -> b.price <= 800 ? "Low" : b.price <= 1500 ? "Medium" : "High";

        Function<Book, String> lengthCategory = b -> b.pages <= 300 ? "Short" : b.pages <= 600 ? "Medium" : "Long";

        books.stream().
                collect(Collectors.groupingBy(priceCategory, Collectors.groupingBy(lengthCategory)))
                .forEach((price, map) -> {
                    output.append("Price: ").append(price).append("\n");
                    map.forEach((length, list) -> output.append("  ").append(length).append(" -> ").append(list.size()).append(" books\n"));
                });

        return output.toString();
    }
}
