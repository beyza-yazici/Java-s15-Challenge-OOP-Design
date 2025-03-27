package models;

import java.time.LocalDate;

public class StudyBook extends Book {
    private String subject;

    public StudyBook(int id, String title, String author, String genre, double price, LocalDate dateOfPurchase, String edition, String subject) {
        super(id, title, author, genre, price, dateOfPurchase, edition);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "StudyBook{" +
                super.toString() +
                ", subject='" + subject + '\'' +
                '}';
    }
}