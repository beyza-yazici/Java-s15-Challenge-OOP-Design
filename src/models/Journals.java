package models;

import java.time.LocalDate;

public class Journals extends Book {
    private String journalType;

    public Journals(int id, String title, String author, String genre, double price, LocalDate dateOfPurchase, String edition, String journalType) {
        super(id, title, author, genre, price, dateOfPurchase, edition);
        this.journalType = journalType;
    }

    public String getJournalType() {
        return journalType;
    }

    @Override
    public String toString() {
        return "Journals{" +
                super.toString() +
                ", journalType='" + journalType + '\'' +
                '}';
    }
}