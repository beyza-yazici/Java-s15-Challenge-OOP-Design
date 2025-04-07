package repository;

import models.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookRepository {
    private Map<Integer, Book> bookStorage = new HashMap<>();

    public boolean save(Book book) {

        if (bookStorage.containsKey(book.getId())) {
            System.out.println("Error: Book with ID " + book.getId() + " already exists!");
            return false;
        }

        bookStorage.put(book.getId(), book);
        System.out.println("Book saved successfully: " + book);
        return true;
    }

    public boolean delete(Book book) {
        if (book == null) {
            System.out.println("Error: Book cannot be null.");
            return false;
        }

        Book removedBook = bookStorage.remove(book.getId());
        if (removedBook != null) {
            System.out.println("Book deleted: " + book);
            return true;
        } else {
            System.out.println("Book not found with ID: " + book.getId());
            return false;
        }
    }

    public Book findBooksById(int id){
        return bookStorage.get(id);
    }

    public List<Book> findBooksByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return bookStorage.values().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByTitle(String title) {
        return bookStorage.values().stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return bookStorage.values().stream()
                .filter(book -> book.getGenre().toLowerCase().contains(genre.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean updateBook(int id, String title, String author, String genre, double price, LocalDate dateOfPurchase, String edition) {
        Book book = findBooksById(id);
        if (book == null) {
            System.out.println("Book not found.");
            return false;
        }

        if (price <= 0) {
            System.out.println("Price must be greater than 0.");
            return false;
        }

        if (title.trim().isEmpty() || author.trim().isEmpty() || genre.trim().isEmpty() || edition.trim().isEmpty()) {
            System.out.println("All fields must be filled.");
            return false;
        }

        bookStorage.put(id, new Book(id, title, author, genre, price, dateOfPurchase, edition));
        System.out.println("Book updated successfully.");
        return true;
    }

    public List<Book> findAll(){
        return bookStorage.values().stream().collect(Collectors.toList());
    }
}
