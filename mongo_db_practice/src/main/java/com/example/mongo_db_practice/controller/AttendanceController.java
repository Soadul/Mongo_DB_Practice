package com.example.mongo_db_practice.controller;

import com.example.mongo_db_practice.model.Attendance;
import com.example.mongo_db_practice.model.Employee;
import com.example.mongo_db_practice.repository.AttendanceRepository;
import com.example.mongo_db_practice.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@RestController
public class AttendanceController {
    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceController(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/attendance")
    public Attendance createAttendance(@RequestBody Map<String, Object> body) {
        String employeeId = ((Map<String, String>) body.get("employee")).get("$id");
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Attendance attendance = new Attendance();
        attendance.setDate(LocalDate.parse((String) body.get("date")));
        attendance.setIn_time(LocalTime.parse((String) body.get("in_time")));
        attendance.setOut_time(LocalTime.parse((String) body.get("out_time")));
        attendance.setEmployee(employee);

        return attendanceRepository.save(attendance);
    }

    @GetMapping("/attendance")
    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }
}