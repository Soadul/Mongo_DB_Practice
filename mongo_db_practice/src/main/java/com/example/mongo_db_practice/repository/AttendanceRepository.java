package com.example.mongo_db_practice.repository;

import com.example.mongo_db_practice.model.Attendance;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttendanceRepository extends MongoRepository<Attendance, String> {
}
