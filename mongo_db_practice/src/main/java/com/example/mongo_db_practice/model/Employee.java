package com.example.mongo_db_practice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * This class represents an Employee in the system.
 * It is a model class that will be used to store data in the MongoDB database.
 * Each Employee has a name, an employee_id, a department, and a position.
 *
 * @Document annotation indicates that this class will be used to create a MongoDB document.
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    String name;

    @Id
    String employee_id;

    String department;
    String position;
}
