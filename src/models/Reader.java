package models;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person{
    private List<Book> borrowedBooks;
    private static final int MAX_BOOKS = 5;

    public Reader(int id, String name, String email) {
        super(id, name, email);
        this.borrowedBooks = new ArrayList<>();
    }

    public boolean borrowBook(Book book){
        if(borrowedBooks.size() < MAX_BOOKS && book.isAvailable()){
            borrowedBooks.add(book);
            book.setAvailable(false);
            book.changeOwner(this);
            return true;
        }
        return false;
    }

    public void returnBook(Book book){
        if(borrowedBooks.remove(book)){
            book.setAvailable(true);
            book.changeOwner(null);
        }
    }

    public boolean purchaseBook(Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            book.changeOwner(this);
            return true;
        }
        return false;
    }

    public void showBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            for (Book book : borrowedBooks) {
                book.display();
            }
        }
    }

    public String whoYouAre() {
        return "Reader: " + super.whoYouAre();
    }

    public List<Book> getBorrowedBooks(){
        return borrowedBooks;
    }
}
