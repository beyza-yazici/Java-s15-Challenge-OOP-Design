package main;

import models.*;
import repository.BookRepository;
import repository.ReaderRepository;
import repository.TransactionRepository;
import services.LibraryManagementSystem;
import services.LibraryService;
import services.TransactionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleApp {

    private static BookRepository bookRepository = new BookRepository();
    private static ReaderRepository readerRepository = new ReaderRepository();
    private static TransactionRepository transactionRepository = new TransactionRepository();
    private static LibraryService libraryService = new LibraryService(bookRepository, readerRepository, transactionRepository);
    private static TransactionService transactionService = new TransactionService(new TransactionRepository());
    private static Scanner scanner = new Scanner(System.in);
    private static LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem(bookRepository, readerRepository);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            try{
            System.out.println("Library Automation");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Lend Book");
            System.out.println("4. Return Book");
            System.out.println("5. List All Books");
            System.out.println("6. Add Reader");
            System.out.println("7. List All Readers");
            System.out.println("8. View Invoices");
            System.out.println("9. Update Book");
            System.out.println("10. Delete Book");
            System.out.println("11. Exit");
            System.out.print("Your choice: ");

            String input = scanner.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1 -> addBook();
                case 2 -> searchBook();
                case 3 -> lendBook();
                case 4 -> returnBook();
                case 5 -> listAllBooks();
                case 6 -> addReader();
                case 7 -> listAllReaders();
                case 8 -> viewInvoices();
                case 9 -> updateBook();
                case 10 -> deleteBook();
                case 11 -> exit = true;
                default -> System.out.println("Invalid choice. Please enter a number between 1-9.");
            }
        } catch(Exception e){
            System.out.println("An error occurred: " + e.getMessage());
            scanner.nextLine();
        }
    }
}

    private static int getValidIntegerInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double getValidDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static boolean isValidEmail(String email) {

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);

        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        return pattern.matcher(email).matches();
    }

    private static void addBook() {
        try {
            int id = getValidIntegerInput("Book ID: ");
            if (bookRepository.findBooksById(id) != null) {
                System.out.println("Error: A book with ID " + id + " already exists.");
                return;
            }
            System.out.print("Title: ");
            String title = scanner.nextLine();

            if (title.trim().isEmpty()) {
                System.out.println("Error: Title cannot be empty.");
                return;
            }

            System.out.print("Author: ");
            String author = scanner.nextLine();

            if (author.trim().isEmpty()) {
                System.out.println("Error: Author cannot be empty.");
                return;
            }

            System.out.print("Genre: ");
            String genre = scanner.nextLine();

            if (genre.trim().isEmpty()) {
                System.out.println("Error: Genre cannot be empty.");
                return;
            }

            double price = getValidDoubleInput("Price: ");

            if (price <= 0) {
                System.out.println("Error: Price must be greater than 0.");
                return;
            }

            System.out.print("Edition: ");
            String edition = scanner.nextLine();

            if (edition.trim().isEmpty()) {
                System.out.println("Error: Edition cannot be empty.");
                return;
            }

            Book book = new Book(id, title, author, genre, price, LocalDate.now(), edition);
            if (bookRepository.save(book)) {
                System.out.println("Book added successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error while adding book: " + e.getMessage());
        }
    }

    private static void searchBook() {
        try {
            System.out.println("Select Search Criteria:");
            System.out.println("1. By Title");
            System.out.println("2. By Author");
            System.out.println("3. By Genre");
            System.out.print("Your choice: ");

            int searchChoice = getValidIntegerInput("");

            switch (searchChoice) {
                case 1 -> {
                    System.out.print("Title: ");
                    String title = scanner.nextLine();
                    List<Book> books = bookRepository.findBooksByTitle(title);
                    if (books.isEmpty()) {
                        System.out.println("No books found with this title.");
                    } else {
                        books.forEach(System.out::println);
                    }
                }
                case 2 -> {
                    System.out.print("Author: ");
                    String author = scanner.nextLine();
                    List<Book> books = bookRepository.findBooksByAuthor(author);
                    if (books.isEmpty()) {
                        System.out.println("No books found by this author.");
                    } else {
                        books.forEach(System.out::println);
                    }
                }
                case 3 -> {
                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();
                    List<Book> books = bookRepository.findBooksByGenre(genre);
                    if (books.isEmpty()) {
                        System.out.println("No books found in this genre.");
                    } else {
                        books.forEach(System.out::println);
                    }
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Error while searching book: " + e.getMessage());
        }
    }

    private static void lendBook() {
        try {
            int bookId = getValidIntegerInput("Book ID: ");
            Book book = bookRepository.findBooksById(bookId);
            if (book == null) {
                System.out.println("Error: No book found with ID: " + bookId);
                return;
            }

            int readerId = getValidIntegerInput("Reader ID: ");
            Reader reader = readerRepository.findReaderById(readerId);
            if (reader == null) {
                System.out.println("Error: No reader found with ID: " + readerId);
                return;
            }

            if (libraryService.borrowBook(bookId, readerId)) {
                System.out.println("Book '" + book.getTitle() + "' lent successfully to reader: " + reader.getName());
                Transaction transaction = new Transaction(bookId, book, reader, LocalDate.now());
                transactionService.recordTransaction(transaction);
            } else {
                System.out.println("Failed to lend book. The book might already be borrowed.");
            }
        } catch (Exception e) {
            System.out.println("Error while lending book: " + e.getMessage());
        }
    }

    private static void returnBook() {
        try {
            int bookId = getValidIntegerInput("Book ID: ");
            int readerId = getValidIntegerInput("Reader ID: ");

            libraryService.returnBook(bookId, readerId);
            System.out.println("Book returned successfully.");
        } catch (Exception e) {
            System.out.println("Error while returning book: " + e.getMessage());
        }
    }

    private static void listAllBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            if (books.isEmpty()) {
                System.out.println("No books available in the library.");
            } else {
                books.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error while listing books: " + e.getMessage());
        }
    }

    private static void addReader() {
        try {
            int id = getValidIntegerInput("Reader ID: ");

            if (readerRepository.findReaderById(id) != null) {
                System.out.println("Error: A reader with ID " + id + " already exists.");
                return;
            }

            System.out.print("Name: ");
            String name = scanner.nextLine();

            if (name.trim().isEmpty()) {
                System.out.println("Error: Name cannot be empty.");
                return;
            }

            String email;
            while (true) {
                System.out.print("Email: ");
                email = scanner.nextLine();

                if (isValidEmail(email)) {
                    break;
                } else {
                    System.out.println("Error: Please enter a valid email address (e.g., example@domain.com)");
                }
            }

            Reader reader = new Reader(id, name, email);
            LibraryManagementSystem.addReader(reader);
            System.out.println("Reader added successfully: " + reader);
        } catch (Exception e) {
            System.out.println("Error while adding reader: " + e.getMessage());
        }
    }

    private static void listAllReaders() {
        try {
            List<Reader> readers = readerRepository.findAll();
            if (readers.isEmpty()) {
                System.out.println("No readers registered in the library.");
            } else {
                readers.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Error while listing readers: " + e.getMessage());
        }
    }

    private static void viewInvoices() {
        try {
            int readerId = getValidIntegerInput("Reader ID: ");
            Reader reader = readerRepository.findReaderById(readerId);
            if (reader != null) {
                List<Invoice> invoices = Invoice.getInvoicesByReader(reader);
                if (invoices.isEmpty()) {
                    System.out.println("No invoices found for this reader.");
                } else {
                    invoices.forEach(System.out::println);
                }
            } else {
                System.out.println("Reader not found.");
            }
        } catch (Exception e) {
            System.out.println("Error while viewing invoices: " + e.getMessage());
        }
    }

    private static void updateBook() {
        try {
            int id = getValidIntegerInput("Enter Book ID to update: ");
            Book existingBook = bookRepository.findBooksById(id);

            if (existingBook == null) {
                System.out.println("Error: No book found with ID: " + id);
                return;
            }

            System.out.println("Current book details:");
            System.out.println(existingBook);
            System.out.println("Enter new details (press Enter to keep current value):");

            System.out.print("Title [" + existingBook.getTitle() + "]: ");
            String title = scanner.nextLine();
            title = title.trim().isEmpty() ? existingBook.getTitle() : title;

            System.out.print("Author [" + existingBook.getAuthor() + "]: ");
            String author = scanner.nextLine();
            author = author.trim().isEmpty() ? existingBook.getAuthor() : author;

            System.out.print("Genre [" + existingBook.getGenre() + "]: ");
            String genre = scanner.nextLine();
            genre = genre.trim().isEmpty() ? existingBook.getGenre() : genre;

            double price;
            while (true) {
                System.out.print("Price [" + existingBook.getPrice() + "]: ");
                String priceInput = scanner.nextLine();
                if (priceInput.trim().isEmpty()) {
                    price = existingBook.getPrice();
                    break;
                }
                try {
                    price = Double.parseDouble(priceInput);
                    if (price <= 0) {
                        System.out.println("Error: Price must be greater than 0.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }

            System.out.print("Edition [" + existingBook.getEdition() + "]: ");
            String edition = scanner.nextLine();
            edition = edition.trim().isEmpty() ? existingBook.getEdition() : edition;

            Book updatedBook = new Book(id, title, author, genre, price, existingBook.getDateOfPurchase(), edition);
            bookRepository.updateBook(id, title, author, genre, price, existingBook.getDateOfPurchase(), edition);

            System.out.println("Book updated successfully.");
            System.out.println("Updated book details:");
            System.out.println(updatedBook);

        } catch (Exception e) {
            System.out.println("Error while updating book: " + e.getMessage());
        }
    }

    private static void deleteBook() {
        try {
            int id = getValidIntegerInput("Enter Book ID to delete: ");
            Book bookToDelete = bookRepository.findBooksById(id);

            if (bookToDelete == null) {
                System.out.println("Error: No book found with ID: " + id);
                return;
            }

            System.out.println("Book to be deleted:");
            System.out.println(bookToDelete);

            if (!bookToDelete.isAvailable()) {
                System.out.println("Error: Cannot delete book because it is currently borrowed by: "
                        + bookToDelete.getCurrentBorrower().getName());
                return;
            }

            System.out.print("Are you sure you want to delete this book? (y/n): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("y")) {
                bookRepository.delete(bookToDelete);
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Delete operation cancelled.");
            }

        } catch (Exception e) {
            System.out.println("Error while deleting book: " + e.getMessage());
        }
    }
}