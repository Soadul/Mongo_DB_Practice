package com.example.mongo_db_practice.model;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

  //  Request Body

 /*   {

        "date": "2024-03-27",
            "in_time": "13:45:20",
            "out_time": "14:45:20",
            "employee": {
        "$id": "66011f32b0ab1242cba15463"
    }

    }
*/
    @Id
    private String attendance_id;

    private LocalDate date;
    private LocalTime in_time;
    private LocalTime out_time;

    @DBRef
    private Employee employee;
}