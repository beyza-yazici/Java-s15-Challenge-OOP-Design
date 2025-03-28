package repository;

import models.Transaction;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    private List<Transaction> transactions = new ArrayList<>();

    public void save(Transaction transaction) {
        transactions.add(transaction);
        System.out.println("Transaction saved: " + transaction);
    }

    public List<Transaction> findAll() {
        return new ArrayList<>(transactions);
    }
}