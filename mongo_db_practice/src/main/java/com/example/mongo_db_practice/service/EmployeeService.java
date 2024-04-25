package com.example.mongo_db_practice.service;

import com.example.mongo_db_practice.model.Attendance;
import com.example.mongo_db_practice.model.Employee;
import com.example.mongo_db_practice.repository.AttendanceRepository;
import com.example.mongo_db_practice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;

    public EmployeeService(EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public String getEmployeeByName(String name) {
        return employeeRepository.findByName(name).getEmployee_id();
    }

    public Duration calculateTotalDutyTime(String employeeId) {
        List<Attendance> attendances = attendanceRepository.findByEmployeeId(employeeId);
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


    public Duration calculateMonthlyDutyTime(String employeeId, int month, int year) {

        List<Attendance> attendances = attendanceRepository.findByEmployeeId(employeeId);
        if (attendances == null) {
            throw new RuntimeException("No attendances found for the employee");
        }
        YearMonth yearMonth = YearMonth.of(year, month);
        Duration totalDutyTime = Duration.ZERO;
        for (Attendance attendance : attendances) {
            if (attendance.getDate().atStartOfDay(ZoneId.systemDefault()).toLocalDate().getMonthValue() == month &&
                    attendance.getDate().atStartOfDay(ZoneId.systemDefault()).toLocalDate().getYear() == year) {
                Duration dutyTime = Duration.between(attendance.getIn_time(), attendance.getOut_time());
                totalDutyTime = totalDutyTime.plus(dutyTime);
            }
        }
        return totalDutyTime;
    }

}