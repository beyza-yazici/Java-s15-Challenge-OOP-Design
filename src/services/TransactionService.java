package services;

import models.Invoice;
import models.Transaction;
import repository.TransactionRepository;

public class TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void recordTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }


}