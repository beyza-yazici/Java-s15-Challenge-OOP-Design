package models;

import java.util.*;

public class Library {
    private Map<Integer, Book> books;
    private Set<Reader> readers;

    public Library() {
        this.books = new HashMap<>();
        this.readers = new HashSet<>();
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
        System.out.println("Book added: " + book);
    }

    public Book findBookById(int id) {
        return books.get(id);
    }

    public List<Book> findBooksByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .toList();
    }

    public List<Book> findBooksByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .toList();
    }

    public List<Book> findBooksByGenre(String genre) {
        return books.values().stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .toList();
    }

    public void updateBook(int id, String title, String author, String genre) {
        Book book = books.get(id);
        if (book != null) {
            books.put(id, new Book(id, title, author, genre, book.getPrice(), book.getDateOfPurchase(), book.getEdition()));
            System.out.println("Book updated: " + books.get(id));
        } else {
            System.out.println("Book not found.");
        }
    }

    public void removeBook(int id){
        if(books.remove(id) != null){
            System.out.println("Book removed. ID: " + id);
        } else {
            System.out.println("Book could not be found.");
        }
    }

    public void listAllBooks() {
        books.values().forEach(System.out::println);
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public Reader findReaderById(int id) {
        return readers.stream()
                .filter(reader -> reader.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Book> getBooks() {
        return List.copyOf(books.values());
    }

    public Set<Reader> getReaders() {
        return Set.copyOf(readers);
    }

    public void newBook(Book book) {
        addBook(book);
    }

    public boolean lendBook(int bookId, int readerId) {
        Book book = findBookById(bookId);
        Reader reader = findReaderById(readerId);
        if (book != null && reader != null && book.isAvailable()) {
            if (reader.borrowBook(book)) {
                Invoice invoice = new Invoice(book.getId(), reader, book, book.getPrice());
                invoice.generateInvoice();
                return true;
            }
        }
        return false;
    }

    public void takeBackBook(int bookId, int readerId) {
        Book book = findBookById(bookId);
        Reader reader = findReaderById(readerId);
        if (book != null && reader != null) {
            reader.returnBook(book);
            Invoice invoice = new Invoice(book.getId(), reader, book, book.getPrice());
            invoice.refundInvoice();
        }
    }

    public void showBook(int bookId) {
        Book book = findBookById(bookId);
        if (book != null) {
            book.display();
        } else {
            System.out.println("Book not found.");
        }
    }
}
