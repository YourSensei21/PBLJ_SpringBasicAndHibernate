package com.example.bank.dao;

import com.example.bank.entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // Marks this as a DAO bean for Spring
public class AccountDaoImpl implements AccountDao {

    // Inject the SessionFactory created in AppConfig
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Account getAccountById(int id) {
        // Get the current session managed by Spring
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Account.class, id);
    }

    @Override
    public void saveAccount(Account account) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(account);
    }

    @Override
    public void updateAccount(Account account) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(account);
    }
}