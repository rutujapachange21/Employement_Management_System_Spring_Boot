package com.emp.service;

import com.emp.entity.Employee;
import com.emp.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1L);
        employee.setName("Rutuja Pachange");
        employee.setEmail("rutujap@gmail.com");
        employee.setDepartment("IT");
        employee.setPosition("Software Engineer");
        employee.setSalary(65000.0);
        employee.setDateOfJoining(LocalDate.of(2026, 05, 15));
    }

    @Test
    void testCreateEmployee() {
        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);

        Employee savedEmployee = employeeService.createEmployee(employee);

        assertNotNull(savedEmployee);
        assertEquals("Rutuja Pachange", savedEmployee.getName());
        assertEquals("IT", savedEmployee.getDepartment());
        assertEquals("Software Engineer", savedEmployee.getPosition());
        assertEquals(65000.0, savedEmployee.getSalary());

        verify(employeeRepository, times(1))
                .save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee() {
        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1))
                .delete(employee);
    }
}