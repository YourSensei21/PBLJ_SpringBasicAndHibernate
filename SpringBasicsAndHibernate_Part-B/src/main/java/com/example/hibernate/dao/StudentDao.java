package com.example.hibernate.dao;

import com.example.hibernate.entity.Student;
import com.example.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDao {

    /**
     * CREATE Student
     * Saves a new student to the database.
     */
    public void saveStudent(Student student) {
        Transaction transaction = null;
        // A Session is a short-lived object used for a single unit of work.
        // The try-with-resources block ensures the session is always closed.
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            // Save the student object (Hibernate generates the INSERT SQL)
            session.save(student);
            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                // Roll back in case of error
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * READ Student by ID
     * Fetches a single student from the database by their primary key.
     */
    public Student getStudent(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // session.get() fetches the object by its ID.
            return session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * READ All Students
     * Fetches all student records from the database.
     */
    public List<Student> getAllStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // HQL (Hibernate Query Language) is used to query objects.
            // "from Student" means "from the Student entity" (not the table).
            return session.createQuery("from Student", Student.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * UPDATE Student
     * Updates an existing student's information in the database.
     */
    public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // update() will update the record in the DB that matches the student's ID.
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * DELETE Student
     * Removes a student from the database by their ID.
     */
    public void deleteStudent(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // First, get the student object to delete
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.delete(student);
                System.out.println("Student with id " + id + " was deleted.");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}