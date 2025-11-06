package com.example.hibernate;

import com.example.hibernate.dao.StudentDao;
import com.example.hibernate.entity.Student;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();

        // --- CREATE ---
        System.out.println("--- Creating new students... ---");
        Student student1 = new Student("Alice", "Wonderland", "alice@wonder.land");
        Student student2 = new Student("Bob", "Marley", "bob@marley.jam");
        studentDao.saveStudent(student1);
        studentDao.saveStudent(student2);
        System.out.println("Students created.");

        // --- READ ALL ---
        System.out.println("\n--- All Students ---");
        List<Student> students = studentDao.getAllStudents();
        // Use a lambda expression to print each student
        students.forEach(s -> System.out.println(s));

        // --- READ ONE ---
        // We'll grab the student with ID 1 (which should be Alice)
        System.out.println("\n--- Reading Student with ID 1 ---");
        Student studentToUpdate = studentDao.getStudent(1);
        System.out.println("Found student: " + studentToUpdate);

        // --- UPDATE ---
        if (studentToUpdate != null) {
            System.out.println("\n--- Updating Student with ID 1 ---");
            studentToUpdate.setEmail("alice.in.wonderland@example.com");
            studentDao.updateStudent(studentToUpdate);
            System.out.println("Student updated: " + studentDao.getStudent(1));
        }

        // --- DELETE ---
        // We'll delete the student with ID 2 (which should be Bob)
        System.out.println("\n--- Deleting Student with ID 2 ---");
        studentDao.deleteStudent(2);

        // --- READ ALL (to confirm delete) ---
        System.out.println("\n--- All Students After Delete ---");
        students = studentDao.getAllStudents();
        students.forEach(s -> System.out.println(s));

        // --- Clean up ---
        System.out.println("\nShutting down Hibernate...");
        // This closes the SessionFactory and releases all resources.
        com.example.hibernate.util.HibernateUtil.shutdown();
    }
}