package models;

import repository.BookRepository;
import services.LibraryService;
import repository.ReaderRepository;

public class Library {
    private BookRepository bookRepository;
    private ReaderRepository readerRepository;
    private LibraryService libraryService;

    public Library() {
        this.bookRepository = new BookRepository();
        this.readerRepository = new ReaderRepository();
        this.libraryService = new LibraryService(bookRepository, readerRepository);
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }


    public void listAllBooks() {
        bookRepository.findAll().forEach(System.out::println);
    }

    public void listAllReaders() {
        readerRepository.findAll().forEach(System.out::println);
    }

    public void addReader(Reader reader) {
        readerRepository.save(reader);
    }


    public boolean lendBook(int bookId, int readerId) {
        return libraryService.borrowBook(bookId, readerId);
    }

    public void takeBackBook(int bookId, int readerId) {
        libraryService.returnBook(bookId, readerId);
    }

        public void showBook (int bookId){
            Book book = bookRepository.findBooksById(bookId);
            if (book != null) {
                book.display();
            } else {
                System.out.println("Book not found.");
            }
        }
    }

