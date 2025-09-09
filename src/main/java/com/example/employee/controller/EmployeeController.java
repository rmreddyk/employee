package com.example.employee.controller;

import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService service;
    public EmployeeController(EmployeeService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Employee employee) {
        logger.info("Received request to create employee: {}", employee);
        try {
            Employee created = service.createEmployee(employee);
            logger.info("Employee created with ID: {}", created.getId());
            return ResponseEntity.created(URI.create("/api/employees/" + created.getId())).body(created);
        } catch (Exception ex) {
            logger.error("Error creating employee: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("Error creating employee: " + ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        logger.info("Received request to get employee with ID: {}", id);
        try {
            Employee employee = service.getEmployeeById(id);
            logger.info("Returning employee: {}", employee);
            return ResponseEntity.ok(employee);
        } catch (Exception ex) {
            logger.error("Error fetching employee: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("Error fetching employee: " + ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> list() {
        logger.info("Received request to list all employees");
        try {
            List<Employee> employees = service.getAllEmployees();
            logger.info("Returning {} employees", employees.size());
            return ResponseEntity.ok(employees);
        } catch (Exception ex) {
            logger.error("Error listing employees: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("Error listing employees: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        logger.info("Received request to update employee with ID: {}. Payload: {}", id, employee);
        try {
            Employee updated = service.updateEmployee(id, employee);
            logger.info("Employee updated: {}", updated);
            return ResponseEntity.ok(updated);
        } catch (Exception ex) {
            logger.error("Error updating employee: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("Error updating employee: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        logger.info("Received request to delete employee with ID: {}", id);
        try {
            service.deleteEmployee(id);
            logger.info("Employee deleted with ID: {}", id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            logger.error("Error deleting employee: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("Error deleting employee: " + ex.getMessage());
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        logger.info("Received request to get employee by email: {}", email);
        try {
            Employee employee = service.getEmployeeByEmail(email);
            logger.info("Returning employee: {}", employee);
            return ResponseEntity.ok(employee);
        } catch (Exception ex) {
            logger.error("Error fetching employee by email: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("Error fetching employee by email: " + ex.getMessage());
        }
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<?> getByDepartment(@PathVariable String department) {
        logger.info("Received request to get employees by department: {}", department);
        try {
            List<Employee> employees = service.getEmployeesByDepartment(department);
            logger.info("Returning {} employees for department: {}", employees.size(), department);
            return ResponseEntity.ok(employees);
        } catch (Exception ex) {
            logger.error("Error fetching employees by department: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().body("Error fetching employees by department: " + ex.getMessage());
        }
    }
}