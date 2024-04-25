package com.example.mongo_db_practice.repository;

import com.example.mongo_db_practice.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends MongoRepository<Admin, String>{

    Optional<Admin> findByUsername(String username);

    //Admin findByPassword(String password);
}
