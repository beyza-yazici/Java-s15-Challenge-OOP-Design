package repository;

import models.Book;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookRepository {
    private Map<Integer, Book> bookStorage = new HashMap<>();

    public void save(Book book){
        bookStorage.put(book.getId(), book);
        System.out.println("Book saved: " + book);
    }

    public void delete(Book book){
        bookStorage.remove(book.getId());
        System.out.println("Book deleted: " + book);
    }

    public Book findBooksById(int id){
        return bookStorage.get(id);
    }

    public List<Book> findBooksByAuthor(String author){
        return bookStorage.values().stream().filter(book -> book.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
    }

    public List<Book> findBooksByTitle(String title) {
        return bookStorage.values().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByGenre(String genre) {
        return bookStorage.values().stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    public void updateBook(int id, String title, String author, String genre, double price, LocalDate dateOfPurchase, String edition) {
        Book book = findBooksById(id);
        if (book != null) {
            bookStorage.put(id, new Book(id, title, author, genre, price, dateOfPurchase, edition));
            System.out.println("Book updated: " + bookStorage.get(id));
        } else {
            System.out.println("Book not found.");
        }
    }

    public List<Book> findAll(){
        return bookStorage.values().stream().collect(Collectors.toList());
    }
}
