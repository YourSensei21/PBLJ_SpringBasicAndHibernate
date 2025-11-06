package com.example.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    // The SessionFactory is a heavy object, so we create it only once (singleton).
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            // The .configure() method finds and loads "hibernate.cfg.xml" by default.
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception to understand what went wrong.
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Public method to get the one-and-only SessionFactory.
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Call this method at the end of your application to clean up.
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}