package repository;

import models.Book;
import models.Invoice;
import models.Reader;
import models.Transaction;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private List<Transaction> transactions = new ArrayList<>();

    public void save(Transaction transaction) {
        transactions.add(transaction);
        System.out.println("Transaction saved: " + transaction);
    }

    public List<Transaction> findAll() {
        return new ArrayList<>(transactions);
    }

    public Transaction findTransactionByBookAndReader(Book book, Reader reader) {
        for (Transaction transaction : transactions) {
            if (transaction.getBook().equals(book) && transaction.getReader().equals(reader) && transaction.getReturnDate() == null) {
                return transaction;
            }
        }
        return null;
    }

    public Invoice findInvoiceByTransaction(Transaction transaction) {
        for (Invoice invoice : Invoice.getInvoicesByReader(transaction.getReader())) {
            if (invoice.getBook().equals(transaction.getBook())) {
                return invoice;
            }
        }
        return null;
    }

    public void update(Transaction transaction) {

    }

    public void removeInvoice(Invoice invoice) {
        Invoice.getInvoicesByReader(invoice.getReader()).remove(invoice);
    }
}