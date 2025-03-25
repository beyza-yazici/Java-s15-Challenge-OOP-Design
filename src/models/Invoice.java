package models;

public class Invoice {

    private int id;
    private Reader reader;
    private Book book;
    private double amount;

    public Invoice(int id, Reader reader, Book book, double amount) {
        this.id = id;
        this.reader = reader;
        this.book = book;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return book;
    }

    public double getAmount() {
        return amount;
    }

    public void generateInvoice() {
        System.out.println("Invoice generated for " + reader.getName() + " for book " + book.getTitle() + ": $" + amount);
    }

    public void refundInvoice() {
        System.out.println("Invoice refunded for " + reader.getName() + " for book " + book.getTitle() + ": $" + amount);
    }
}
