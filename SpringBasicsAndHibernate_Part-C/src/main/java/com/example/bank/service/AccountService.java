package com.example.bank.service;

import com.example.bank.entity.Account;

public interface AccountService {
    void createAccount(String name, double initialBalance);
    Account getAccount(int id);
    void transferMoney(int fromAccountId, int toAccountId, double amount);
}