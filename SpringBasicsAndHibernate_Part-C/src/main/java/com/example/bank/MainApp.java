package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        // 1. Initialize the Spring Context
        // We pass our @Configuration class to the constructor.
        ApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. Retrieve the "student" bean from the Spring container.
        // Spring has already created the Student and injected the Course.
        // We are asking for the bean by its method name ("student").
        Student student = context.getBean("student", Student.class);

        // 3. Call the method to prove it works.
        student.displayStudentInfo();

        // 4. (Optional) Close the context
        ((AnnotationConfigApplicationContext) context).close();
    }
}