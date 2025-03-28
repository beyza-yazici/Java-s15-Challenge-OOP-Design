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

    public Book searchBook(List<Book> books, String title){
        for(Book book : books){
            if(book.getTitle().equalsIgnoreCase(title)){
                return book;
            }
        }
        return null;
    }

    public boolean verifyMember(List<Reader> readers, int memberId){
        for(Reader reader : readers){
            if(reader.getId() == memberId){
                return true;
            }
        }
        return false;
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
