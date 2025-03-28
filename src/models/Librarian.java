package models;

import java.util.List;

public class Librarian extends Person{

    private String password;

    public Librarian(int id, String name, String email, String password) {
        super(id, name);
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public boolean issueBook(Book book, Reader reader){
        return reader.borrowBook(book);
    }

    public double calculateFine(int daysLate){
        double finePerDay = 1.0;
        return daysLate * finePerDay;
    }

    public void createBill(Reader reader, Book book, double amount){
        System.out.println("Bill created for " + reader.getName() + " for book " + book.getTitle() + ": $" + amount);
    }

    public void returnBook(Book book, Reader reader) {
        reader.returnBook(book);
    }

}
