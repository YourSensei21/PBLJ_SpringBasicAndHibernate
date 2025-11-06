package com.example.bank.dao;

import com.example.bank.entity.Transaction;

public interface TransactionDao {
    void saveTransaction(Transaction transaction);
}