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

@Controller
public class EmployeeViewController {
    private final EmployeeService service;
    public EmployeeViewController(EmployeeService service) { this.service = service; }

    @GetMapping("/employees")
    public String listEmployees(Model model) {
        List<Employee> employees = service.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employees/{id}")
    public String viewEmployee(@PathVariable Long id, Model model) {
        Employee employee = service.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee-detail";
    }

    @GetMapping("/employees/search")
    public String searchEmployees(@RequestParam(name = "query", required = false) String query, Model model) {
        List<Employee> employees = service.searchEmployees(query);
        model.addAttribute("employees", employees);
        model.addAttribute("searchQuery", query);
        return "employees";
    }

    @PostMapping("/employees/add")
    public String addEmployee(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String email,
                              @RequestParam(required = false) String department) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setDepartment(department);
        service.createEmployee(employee);
        return "redirect:/employees";
    }

    @PostMapping("/employees/delete")
    public String deleteEmployee(@RequestParam Long id) {
        service.deleteEmployee(id);
        return "redirect:/employees";
    }
}
