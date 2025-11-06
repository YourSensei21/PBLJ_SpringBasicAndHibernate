package com.example.demo;

// This class "depends on" a Course.
// We are using "Constructor Injection" to provide this dependency.
public class Student {

    private String name;
    private Course course; // The dependency

    // The constructor is the point of injection.
    // The Student MUST be created with a Course.
    public Student(Course course) {
        this.course = course;
    }

    public void setName(String name) {
        this.name = name;
    }

    // This method will prove that the dependency was injected correctly.
    public void displayStudentInfo() {
        System.out.println("Student Name: " + this.name);
        System.out.println("Enrolled in: " + course.getCourseDetails());
    }
}