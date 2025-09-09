package com.example.employee.controller;

import com.example.employee.entity.Employee;
import com.example.employee.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class EmployeeViewController {
    private final EmployeeService service;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeViewController.class);

    public EmployeeViewController(EmployeeService service) { this.service = service; }

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        logger.info("Received request to list all employees (UI)  ");
        List<Employee> employees = service.getAllEmployees();
        logger.info("Returning {} employees to UI Page", employees.size());
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees/{id}")
    public String viewEmployee(@PathVariable Long id, Model model) {
        logger.info("Received request to view employee with ID: {} ", id);
        Employee employee = service.getEmployeeById(id);
        logger.info("Returning employee to UI: {}", employee);
        model.addAttribute("employee", employee);
        return "employee-detail";
    }

    @GetMapping("/employees/search")
    public String searchEmployees(@RequestParam(name = "query", required = false) String query, Model model) {
        logger.info("Received search request in UI with query: {}", query);
        List<Employee> employees = service.searchEmployees(query);
        logger.info("Returning {} search results to UI", employees.size());
        model.addAttribute("employees", employees);
        model.addAttribute("searchQuery", query);
        return "employees";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String email,
                              @RequestParam(required = false) String department) {
        logger.info("Received request to add employee in UI: firstName={}, lastName={}, email={}, department={}", firstName, lastName, email, department);
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setDepartment(department);
        service.createEmployee(employee);
        logger.info("Employee added in UI: {}", employee);
        return "redirect:/employees";
    }

    @PostMapping("/employees/delete")
    public String deleteEmployee(@RequestParam Long id) {
        logger.info("Received request to delete employee in UI with ID: {}", id);
        service.deleteEmployee(id);
        logger.info("Employee deleted in UI with ID: {}", id);
        return "redirect:/employees";
    }
}
