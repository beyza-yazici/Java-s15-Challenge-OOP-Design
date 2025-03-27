package models;

import java.time.LocalDate;

public class Magazines extends Book {
    private String issue;

    public Magazines(int id, String title, String author, String genre, double price, LocalDate dateOfPurchase, String edition, String issue) {
        super(id, title, author, genre, price, dateOfPurchase, edition);
        this.issue = issue;
    }

    public String getIssue() {
        return issue;
    }

    @Override
    public String toString() {
        return "Magazines{" +
                super.toString() +
                ", issue='" + issue + '\'' +
                '}';
    }
}