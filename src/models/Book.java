package models;

import java.time.LocalDate;


public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private double price;
    private LocalDate dateOfPurchase;
    private Reader currentBorrower;
    private String edition;


    public Book(int id, String title, String author, String genre, double price, LocalDate dateOfPurchase, String edition) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.dateOfPurchase = dateOfPurchase;
        this.currentBorrower = null;
        this.edition = edition;

    }


    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return currentBorrower == null;
    }

    public void setBorrower(Reader borrower) {
        this.currentBorrower = borrower;
    }

    public Reader getCurrentBorrower() {
        return currentBorrower;
    }

    public String getTitle() {
        return title;
    }


    public String getAuthor() {
        return author;
    }


    public String getGenre() {
        return genre;
    }


    public double getPrice() {
        return price;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public String getEdition() {
        return edition;
    }


    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", dateOfPurchase=" + dateOfPurchase +
                ", status=" + (isAvailable() ? "Available" : "Borrowed by " + currentBorrower.getName()) +
                ", edition='" + edition + '\'' +
                '}';
    }
}
