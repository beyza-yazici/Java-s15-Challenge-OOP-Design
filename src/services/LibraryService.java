package services;

import models.Book;
import models.Invoice;
import models.Reader;
import repository.BookRepository;
import repository.ReaderRepository;

import java.util.List;

public class LibraryService {

    private BookRepository bookRepository;
    private ReaderRepository readerRepository;

    public LibraryService(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
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

        if (!book.isAvailable()) {
            System.out.println("Error: Book is already borrowed by: " + book.getCurrentBorrower().getName());
            return false;
        }

        book.setBorrower(reader);
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
        return true;
    }

    public void generateInvoice(int bookId, int readerId){
        Book book = bookRepository.findBooksById(bookId);
        Reader reader = readerRepository.findReaderById(readerId);
        if(book != null && reader != null){
            Invoice invoice = new Invoice(book.getId(), reader, book, book.getPrice());
            invoice.generateInvoice();

        }
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
