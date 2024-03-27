package com.example.mongo_db_practice.repository;

import com.example.mongo_db_practice.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Employee findByName(String name);

}
