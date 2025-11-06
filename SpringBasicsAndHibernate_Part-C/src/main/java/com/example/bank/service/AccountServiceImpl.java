package com.example.bank.service;

import com.example.bank.dao.AccountDao;
import com.example.bank.dao.TransactionDao;
import com.example.bank.entity.Account;
import com.example.bank.entity.Transaction;
import com.example.bank.exception.InsufficientFundsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import Spring's annotation

import java.sql.Timestamp;

@Service // Marks this as a Service bean for Spring
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TransactionDao transactionDao;

    @Override
    @Transactional // All database work in this method is wrapped in one transaction
    public void createAccount(String name, double initialBalance) {
        Account account = new Account(name, initialBalance);
        accountDao.saveAccount(account);
    }

    @Override
    @Transactional(readOnly = true) // A read-only transaction is more efficient
    public Account getAccount(int id) {
        return accountDao.getAccountById(id);
    }

    @Override
    @Transactional // This is the key!
    public void transferMoney(int fromAccountId, int toAccountId, double amount) {
        // 1. Get both accounts
        Account fromAccount = accountDao.getAccountById(fromAccountId);
        Account toAccount = accountDao.getAccountById(toAccountId);

        // 2. Check for sufficient funds
        if (fromAccount.getBalance() < amount) {
            // This exception will trigger a rollback
            throw new InsufficientFundsException(
                    "Insufficient funds in account " + fromAccountId
            );
        }

        // 3. Perform the deduction
        fromAccount.setBalance(fromAccount.getBalance() - amount);

        // 4. Perform the addition
        toAccount.setBalance(toAccount.getBalance() + amount);

        // 5. Update both accounts in the database
        accountDao.updateAccount(fromAccount);
        accountDao.updateAccount(toAccount);

        // 6. Log the transaction
        Transaction tx = new Transaction(
                fromAccountId, toAccountId, amount, new Timestamp(System.currentTimeMillis())
        );
        transactionDao.saveTransaction(tx);

        System.out.println("Transfer successful!");

        // If the method completes without error, Spring commits the transaction.
        // If an error (like InsufficientFundsException) occurs,
        // Spring rolls back all 3 operations (update, update, save).
    }
}