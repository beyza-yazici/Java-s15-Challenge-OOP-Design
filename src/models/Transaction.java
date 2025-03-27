package models;

import java.time.LocalDate;

public class Transaction {
    private int transactionId;
    private Book book;
    private Reader reader;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public Transaction(int transactionId, Book book, Reader reader, LocalDate issueDate) {
        this.transactionId = transactionId;
        this.book = book;
        this.reader = reader;
        this.issueDate = issueDate;
        this.returnDate = null;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", book=" + book.getTitle() +
                ", reader=" + reader.getName() +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}