package com.example.bank.dao;

import com.example.bank.entity.Account;

public interface AccountDao {
    Account getAccountById(int id);
    void saveAccount(Account account);
    void updateAccount(Account account);
}