package com.example.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.employee.entity.Employee;
import com.example.employee.repository.EmployeeRepository;

import java.time.LocalDate;

@SpringBootApplication
public class EmployeeServiceApplication {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceApplication.class);

    public static void main(String[] args) {
        logger.info("Starting Employee Service Application...");
        SpringApplication.run(EmployeeServiceApplication.class, args);
        logger.info("Employee Service Application started successfully.");
    }

    @Bean
    public CommandLineRunner dataInitializer(EmployeeRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Employee(null, "John", "Doe", "john.doe@example.com", "IT", "123 Main St", LocalDate.of(1990, 1, 1), LocalDate.of(2020, 1, 1), "Developer"));
                repository.save(new Employee(null, "Jane", "Smith", "jane.smith@example.com", "HR", "456 Elm St", LocalDate.of(1985, 5, 15), LocalDate.of(2018, 3, 10), "Manager"));
            }
        };
    }
}