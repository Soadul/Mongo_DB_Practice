package com.example.mongo_db_practice.service;

import com.example.mongo_db_practice.model.Attendance;
import com.example.mongo_db_practice.model.Employee;
import com.example.mongo_db_practice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

/**
 * EmployeeService class.
 * This class provides services related to Employee operations.
 */

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    /**
     * EmployeeService constructor.
     * @param employeeRepository the repository to interact with the database
     */
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Method to create a new Employee.
     * @param employee the Employee object to be saved
     * @return the saved Employee object
     */
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Method to get all Employees.
     * @return a list of all Employee objects
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByName(String name) {
        return employeeRepository.findByName(name);
    }




    public Duration calculateTotalDutyTime(Employee employee) {
        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }
        List<Attendance> attendances = employee.getAttendances();
        if (attendances == null) {
            throw new RuntimeException("No attendances found for the employee");
        }
        Duration totalDutyTime = Duration.ZERO;
        for (Attendance attendance : attendances) {
            Duration dutyTime = Duration.between(attendance.getIn_time(), attendance.getOut_time());
            totalDutyTime = totalDutyTime.plus(dutyTime);
        }
        return totalDutyTime;
    }
}