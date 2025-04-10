package models;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private List<Book> borrowedBooks;
    private static final int MAX_BOOKS = 5;
    private String email;

    public Reader(int id, String name, String email) {
        super(id, name);
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public boolean borrowBook(Book book) {
        if (borrowedBooks.size() >= MAX_BOOKS) {
            System.out.println("Error: Reader has reached maximum number of books (" + MAX_BOOKS + ")");
            return false;
        }

        if (!book.isAvailable()) {
            System.out.println("Error: Book is not available");
            return false;
        }

        borrowedBooks.add(book);
        book.setBorrower(this);
        return true;
    }

    public boolean returnBook(Book book) {
        if (!borrowedBooks.contains(book)) {
            System.out.println("Error: This book was not borrowed by this reader");
            return false;
        }

        borrowedBooks.remove(book);
        book.setBorrower(null);
        return true;
    }

    public boolean purchaseBook(Book book) {
        if (!book.isAvailable()) {
            System.out.println("Error: Book is not available for purchase");
            return false;
        }

        book.setBorrower(this);
        return true;
    }

    public void showBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            System.out.println("\nBorrowed books by " + getName() + ":");
            for (Book book : borrowedBooks) {
                System.out.println(book);
            }
        }
    }

    public String getEmail() {
        return email;
    }

    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks); // Defensive copy
    }

    public String whoYouAre() {
        return "Reader: " + super.whoYouAre();
    }

    @Override
    public String toString() {
        return "Reader{" +
                "borrowedBooks=" + borrowedBooks +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
