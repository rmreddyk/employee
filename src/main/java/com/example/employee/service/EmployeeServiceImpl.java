package com.example.employee.service;

import com.example.employee.entity.Employee;
import com.example.employee.exception.EmployeeNotFoundException;
import com.example.employee.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        logger.info("Creating employee: {}", employee);
        Employee saved = repository.save(employee);
        logger.info("Employee created with ID: {}", saved.getId());
        return saved;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        logger.info("Fetching employee by ID: {}", id);
        Employee employee = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found: " + id));
        logger.info("Found employee: {}", employee);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees");
        List<Employee> employees = repository.findAll();
        logger.info("Found {} employees", employees.size());
        return employees;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        logger.info("Updating employee with ID: {}. Payload: {}", id, employee);
        Employee existing = getEmployeeById(id);
        logger.info("Existing employee before update: {}", existing);
        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setAddress(employee.getAddress());
        existing.setDob(employee.getDob());
        existing.setDoj(employee.getDoj());
        existing.setDesignation(employee.getDesignation());
        Employee updated = repository.save(existing);
        logger.info("Employee updated: {}", updated);
        return updated;
    }

    @Override
    public void deleteEmployee(Long id) {
        logger.info("Deleting employee with ID: {}", id);
        repository.delete(getEmployeeById(id));
        logger.info("Employee deleted with ID: {}", id);
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        logger.info("Fetching employee by email: {}", email);
        return repository.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with email: " + email));
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String department) {
        logger.info("Fetching employees by department: {}", department);
        List<Employee> employees = repository.findByDepartment(department);
        logger.info("Found {} employees in department: {}", employees.size(), department);
        return employees;
    }

    @Override
    public List<Employee> searchEmployees(String query) {
        logger.info("Searching employees with query: {}", query);
        if (query == null || query.trim().isEmpty()) {
            logger.info("Query is empty, returning all employees");
            return repository.findAll();
        }
        List<Employee> results = repository.searchByNameOrEmail(query);
        logger.info("Found {} employees matching query: {}", results.size(), query);
        return results;
    }
}