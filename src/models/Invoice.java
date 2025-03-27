package models;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private static List<Invoice> invoices = new ArrayList<>();
    private int id;
    private Reader reader;
    private Book book;
    private double amount;

    public Invoice(int id, Reader reader, Book book, double amount) {
        this.id = id;
        this.reader = reader;
        this.book = book;
        this.amount = amount;
        invoices.add(this);
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

    public static List<Invoice> getInvoicesByReader(Reader reader){
        List<Invoice> readerInvoices = new ArrayList<>();
        for(Invoice invoice : invoices){
            if(invoice.reader.equals(reader)){
                readerInvoices.add(invoice);
            }
        }
        return readerInvoices;
    }

    public void generateInvoice() {
        System.out.println("Invoice generated for " + reader.getName() + " for book " + book.getTitle() + ": $" + amount);
    }

    public void refundInvoice() {
        System.out.println("Invoice refunded for " + reader.getName() + " for book " + book.getTitle() + ": $" + amount);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", reader=" + reader.getName() +
                ", book=" + book.getTitle() +
                ", amount=" + amount +
                '}';
    }
}
