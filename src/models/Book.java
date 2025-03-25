package models;

import java.time.LocalDate;


public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private double price;
    private boolean isAvailable;
    private LocalDate dateOfPurchase;
    private Reader owner;
    private String edition;


    public Book(int id, String title, String author, String genre, double price, LocalDate dateOfPurchase, String edition) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.isAvailable = true;
        this.dateOfPurchase = dateOfPurchase;
        this.owner = null;
        this.edition = edition;
    }


    public int getId() {
        return id;
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


    public boolean isAvailable() {
        return isAvailable;
    }


    public void setAvailable(boolean available) {
        isAvailable = available;
    }


    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }


    public Reader getOwner() {
        return owner;
    }


    public void changeOwner(Reader newOwner) {
        this.owner = newOwner;
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
                ", isAvailable=" + isAvailable +
                ", dateOfPurchase=" + dateOfPurchase +
                ", owner=" + (owner != null ? owner.getName() : "None") +
                ", edition='" + edition + '\'' +
                '}';
    }

}
