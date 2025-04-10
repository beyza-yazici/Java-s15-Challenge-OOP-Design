package models;

import repository.BookRepository;
import services.LibraryService;
import repository.ReaderRepository;
import repository.TransactionRepository;


public class Library {
    private BookRepository bookRepository;
    private ReaderRepository readerRepository;
    private LibraryService libraryService;
    private TransactionRepository transactionRepository;

    public Library() {
        this.bookRepository = new BookRepository();
        this.readerRepository = new ReaderRepository();
        this.transactionRepository = new TransactionRepository();
        this.libraryService = new LibraryService(bookRepository, readerRepository, transactionRepository);
    }

    }

