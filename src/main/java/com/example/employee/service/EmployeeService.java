package com.example.employee.service;

import com.example.employee.entity.Employee;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
    Employee getEmployeeByEmail(String email);
    List<Employee> getEmployeesByDepartment(String department);
    List<Employee> searchEmployees(String query);
}