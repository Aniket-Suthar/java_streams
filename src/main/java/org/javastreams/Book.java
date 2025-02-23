package org.javastreams;

public class Book {
    String title, author, category, edition;
    int price, pages, copiesLeft, wishedUsers;
    boolean discount;
    double ratings, reviews;

    public Book(String title, String author, String category, int price, int pages, String edition,
                int copiesLeft, int wishedUsers, boolean discount, double ratings, double reviews) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.pages = pages;
        this.edition = edition;
        this.copiesLeft = copiesLeft;
        this.wishedUsers = wishedUsers;
        this.discount = discount;
        this.ratings = ratings;
        this.reviews = reviews;
    }
}