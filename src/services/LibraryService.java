package services;

import models.Book;
import models.Invoice;
import models.Reader;
import models.Transaction;
import repository.BookRepository;
import repository.ReaderRepository;
import repository.TransactionRepository;

import java.time.LocalDate;
import java.util.List;

public class LibraryService {

    private BookRepository bookRepository;
    private ReaderRepository readerRepository;
    private TransactionRepository transactionRepository;

    public LibraryService(BookRepository bookRepository, ReaderRepository readerRepository, TransactionRepository transactionRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
        this.transactionRepository = transactionRepository;
    }

    public boolean borrowBook(int bookId, int readerId) {
        Book book = bookRepository.findBooksById(bookId);
        if (book == null) {
            System.out.println("Error: Book not found with ID: " + bookId);
            return false;
        }

        Reader reader = readerRepository.findReaderById(readerId);
        if (reader == null) {
            System.out.println("Error: Reader not found with ID: " + readerId);
            return false;
        }

        if (!reader.borrowBook(book)) {
            return false;
        }

        Transaction transaction = new Transaction(book.getId(), book, reader, LocalDate.now());
        Invoice invoice = new Invoice(transaction.getTransactionId(), reader, book, book.getPrice());
        invoice.generateInvoice();
        transactionRepository.save(transaction);

        return true;
    }

    public boolean returnBook(int bookId, int readerId) {
        Book book = bookRepository.findBooksById(bookId);
        if (book == null) {
            System.out.println("Error: Book not found with ID: " + bookId);
            return false;
        }

        Reader reader = readerRepository.findReaderById(readerId);
        if (reader == null) {
            System.out.println("Error: Reader not found with ID: " + readerId);
            return false;
        }

        if (book.isAvailable()) {
            System.out.println("Error: Book is not currently borrowed.");
            return false;
        }

        if (book.getCurrentBorrower().getId() != readerId) {
            System.out.println("Error: This book was not borrowed by this reader.");
            return false;
        }

        book.setBorrower(null);

        Transaction transaction = transactionRepository.findTransactionByBookAndReader(book, reader);
        if (transaction != null) {
            transaction.setReturnDate(LocalDate.now());
            Invoice invoice = transactionRepository.findInvoiceByTransaction(transaction);
            if (invoice != null) {
                invoice.refundInvoice();
                transactionRepository.removeInvoice(invoice);
            }
            transactionRepository.update(transaction);
        }
        return true;
    }

    private Invoice findInvoiceByTransaction(Transaction transaction) {
        for (Invoice invoice : Invoice.getInvoicesByReader(transaction.getReader())) {
            if (invoice.getBook().equals(transaction.getBook())) {
                return invoice;
            }
        }
        return null;
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
}
