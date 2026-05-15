package com.emp.controller;

import com.emp.entity.Employee;
import com.emp.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeUIController {

    private final EmployeeService employeeService;

    public EmployeeUIController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees-ui")
    public String showEmployees(Model model) {
        List<Employee> employees = employeeService
                .getAllEmployees(0, 100, "name", "asc")
                .getContent();

        model.addAttribute("employees", employees);
        return "employee-list";
    }

    @GetMapping("/employees-ui/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    @PostMapping("/employees-ui/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {

        if (employee.getId() == null) {
            employeeService.createEmployee(employee);
        } else {
            employeeService.updateEmployee(employee.getId(), employee);
        }

        return "redirect:/employees-ui";
    }

    @GetMapping("/employees-ui/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @GetMapping("/employees-ui/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees-ui";
    }
}