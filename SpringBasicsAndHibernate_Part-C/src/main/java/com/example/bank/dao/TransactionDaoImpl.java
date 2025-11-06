package com.example.bank.dao;

import com.example.bank.entity.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDaoImpl implements TransactionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveTransaction(Transaction transaction) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(transaction);
    }
}