package com.example.mongo_db_practice.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Admin {


    @Id
    private  String id;
    private String username;

    private String password;
}