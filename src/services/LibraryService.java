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

    public boolean borrowBook(int bookId, int readerId){
        Book book = bookRepository.findBooksById(bookId);
        Reader reader = readerRepository.findReaderById(readerId);
        if(book != null && reader != null && book.isAvailable()){
            if(reader.borrowBook(book)){
                generateInvoice(bookId, readerId);
                return true;
            }
        }
        return false;
    }

    public void returnBook(int bookId, int readerId){
        Book book = bookRepository.findBooksById(bookId);
        Reader reader = readerRepository.findReaderById(readerId);
        if(book != null && reader != null){
            reader.returnBook(book);
            Invoice invoice = new Invoice(book.getId(), reader, book, book.getPrice());
            invoice.refundInvoice();
        }
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
