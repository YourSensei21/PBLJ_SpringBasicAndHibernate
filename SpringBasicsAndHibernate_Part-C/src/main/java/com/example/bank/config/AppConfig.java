package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration tells Spring that this class contains bean definitions.
@Configuration
public class AppConfig {

    // @Bean tells Spring that this method creates an object (a "bean")
    // that Spring should manage.
    // The method name "course" will be the default bean ID.
    @Bean
    public Course course() {
        Course course = new Course();
        course.setCourseName("CS101: Introduction to Spring");
        return course;
    }

    // This method also defines a bean.
    // The method name "student" will be the bean ID.
    @Bean
    public Student student() {
        // Here is the Dependency Injection!
        // We are manually "injecting" the 'course' bean by calling
        // the course() method. Spring is smart enough to intercept
        // this call and provide the *same* 'course' bean instance it
        // already created.
        Student student = new Student(course());
        student.setName("Alice");
        return student;
    }
}