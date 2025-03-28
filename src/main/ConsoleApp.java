package main;

import models.*;
import repository.BookRepository;
import repository.ReaderRepository;
import repository.TransactionRepository;
import services.LibraryManagementSystem;
import services.LibraryService;
import services.TransactionService;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleApp {

    private static BookRepository bookRepository = new BookRepository();
    private static ReaderRepository readerRepository = new ReaderRepository();
    private static LibraryService libraryService = new LibraryService(bookRepository, readerRepository);
    private static TransactionService transactionService = new TransactionService(new TransactionRepository());
    private static Scanner scanner = new Scanner(System.in);
    private static LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem(bookRepository, readerRepository);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Library Automation");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Lend Book");
            System.out.println("4. Return Book");
            System.out.println("5. List All Books");
            System.out.println("6. Add Reader");
            System.out.println("7. List All Readers");
            System.out.println("8. View Invoices");
            System.out.println("9. Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> searchBook();
                case 3 -> lendBook();
                case 4 -> returnBook();
                case 5 -> listAllBooks();
                case 6 -> addReader();
                case 7 -> listAllReaders();
                case 8 -> viewInvoices();
                case 9 -> exit = true;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Edition: ");
        String edition = scanner.nextLine();

        Book book = new Book(id, title, author, genre, price, LocalDate.now(), edition);
        LibraryManagementSystem.addBook(book);
    }

    private static void searchBook() {
        System.out.println("Select Search Criteria:");
        System.out.println("1. By Title");
        System.out.println("2. By Author");
        System.out.println("3. By Genre");
        System.out.print("Your choice: ");

        int searchChoice = scanner.nextInt();
        scanner.nextLine();

        switch (searchChoice) {
            case 1 -> {
                System.out.print("Title: ");
                String title = scanner.nextLine();
                bookRepository.findBooksByTitle(title).forEach(System.out::println);
            }
            case 2 -> {
                System.out.print("Author: ");
                String author = scanner.nextLine();
                bookRepository.findBooksByAuthor(author).forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("Genre: ");
                String genre = scanner.nextLine();
                bookRepository.findBooksByGenre(genre).forEach(System.out::println);
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void lendBook() {
        System.out.print("Book ID: ");
        int bookId = scanner.nextInt();
        System.out.print("Reader ID: ");
        int readerId = scanner.nextInt();
        if (libraryService.borrowBook(bookId, readerId)) {
            System.out.println("Book lent successfully.");
            Transaction transaction = new Transaction(bookId, bookRepository.findBooksById(bookId), readerRepository.findReaderById(readerId), LocalDate.now());
            transactionService.recordTransaction(transaction);
        } else {
            System.out.println("Failed to lend book.");
        }
    }

    private static void returnBook() {
        System.out.print("Book ID: ");
        int bookId = scanner.nextInt();
        System.out.print("Reader ID: ");
        int readerId = scanner.nextInt();
        libraryService.returnBook(bookId, readerId);
        System.out.println("Book returned successfully.");
    }

    private static void listAllBooks() {
        bookRepository.findAll().forEach(System.out::println);
    }

    private static void addReader() {
        System.out.print("Reader ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Reader reader = new Reader(id, name, email);
        LibraryManagementSystem.addReader(reader);
        System.out.println("Reader added: " + reader);
    }

    private static void listAllReaders() {
        readerRepository.findAll().forEach(System.out::println);
    }

    private static void viewInvoices() {
        System.out.print("Reader ID: ");
        int readerId = scanner.nextInt();
        Reader reader = readerRepository.findReaderById(readerId);
        if (reader != null) {
            Invoice.getInvoicesByReader(reader).forEach(System.out::println);
        } else {
            System.out.println("Reader not found.");
        }
    }
}