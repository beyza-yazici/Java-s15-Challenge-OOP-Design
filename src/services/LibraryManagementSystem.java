package services;

import models.Book;
import models.Reader;
import repository.BookRepository;
import repository.ReaderRepository;

public class LibraryManagementSystem {
    private static BookRepository bookRepository;
    private static ReaderRepository readerRepository;

    public LibraryManagementSystem(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public static void addBook(Book book) {
        bookRepository.save(book);
    }

    public static void addReader(Reader reader) {
        readerRepository.save(reader);
    }
}