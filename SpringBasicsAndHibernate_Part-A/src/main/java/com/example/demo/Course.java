package com.example.demo;

// This is a simple POJO (Plain Old Java Object).
// It has no knowledge of Spring.
public class Course {
    private String courseName;

    public Course() {
        this.courseName = "Default Course";
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDetails() {
        return "Course: " + this.courseName;
    }
}