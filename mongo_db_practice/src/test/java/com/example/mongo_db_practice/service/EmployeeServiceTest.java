package com.example.mongo_db_practice.service;

import com.example.mongo_db_practice.model.Attendance;
import com.example.mongo_db_practice.model.Employee;
import com.example.mongo_db_practice.repository.AttendanceRepository;
import com.example.mongo_db_practice.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    AttendanceRepository attendanceRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create employee successfully")
    public void shouldCreateEmployeeSuccessfully() {
        Employee newEmployee = new Employee("", "John Doe", "IT", "Developer");

        when(employeeRepository.save(newEmployee)).thenReturn(newEmployee);

        Employee result = employeeService.createEmployee(newEmployee);

        assertEquals(newEmployee, result);
    }

    @Test
    @DisplayName("Should return all employees")
    public void shouldReturnAllEmployees() {
        List<Employee> employees = Arrays.asList(
                new Employee("1", "John Doe", "IT", "Developer"),
                new Employee("2", "Jane Doe", "HR", "Manager")
        );

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(employees, result);
    }

    @Test
    @DisplayName("Should throw exception when no attendances found for the employee")
    public void shouldThrowExceptionWhenNoAttendancesFound() {
        String employeeId = "1";

        when(attendanceRepository.findByEmployeeId(employeeId)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> employeeService.calculateTotalDutyTime(employeeId));
    }

    @Test
    @DisplayName("Should calculate total duty time correctly")
    public void shouldCalculateTotalDutyTimeCorrectly() {
        String employeeId = "1";
        ZonedDateTime inTime = ZonedDateTime.now();
        ZonedDateTime outTime = inTime.plusHours(8);
        List<Attendance> attendances = Arrays.asList(
                new Attendance(employeeId, inTime.toLocalDate(), inTime.toLocalTime(), outTime.toLocalTime(), employeeId),
                new Attendance(employeeId, inTime.plusDays(1).toLocalDate(), inTime.plusDays(1).toLocalTime(), outTime.plusDays(1).toLocalTime(), employeeId)
        );

        when(attendanceRepository.findByEmployeeId(employeeId)).thenReturn(attendances);

        Duration result = employeeService.calculateTotalDutyTime(employeeId);

        assertEquals(Duration.ofHours(16), result);
    }

    @Test
    @DisplayName("Should delete employee successfully")
    public void shouldDeleteEmployeeSuccessfully() {
        String id = "1";

        doNothing().when(employeeRepository).deleteById(id);

        assertDoesNotThrow(() -> {
            employeeService.deleteEmployee(id);
        });
    }
}