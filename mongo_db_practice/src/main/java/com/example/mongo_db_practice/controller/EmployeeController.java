package com.example.mongo_db_practice.controller;

import com.example.mongo_db_practice.model.Attendance;
import com.example.mongo_db_practice.model.Employee;
import com.example.mongo_db_practice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

@RequestMapping("/employees")

public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

//    @GetMapping("/get")
//    public List<Employee> getAllEmployees() {
//        return employeeService.getAllEmployees();
//
//
//    }
    @GetMapping("/get")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Assuming you have a method in EmployeeService to get an employee by name
    @GetMapping("/search/{name}")
    public String getEmployeeByName(@PathVariable String name) {
        return employeeService.getEmployeeByName(name);

    }

    @GetMapping("/employees/{name}/totalDutyTime")
    public ResponseEntity<Duration> getTotalDutyTime(@PathVariable String name) {

        Duration totalDutyTime = employeeService.calculateTotalDutyTime(employeeService.getEmployeeByName(name));
        return ResponseEntity.ok(totalDutyTime);
    }

    @GetMapping("/employees/{employeeId}/duty-time/{year}/{month}")
    public ResponseEntity<Duration> getMonthlyDutyTime(@PathVariable String employeeId, @PathVariable int year, @PathVariable int month) {
        Duration dutyTime = employeeService.calculateMonthlyDutyTime(employeeId, month, year);
        return ResponseEntity.ok(dutyTime);
    }
}