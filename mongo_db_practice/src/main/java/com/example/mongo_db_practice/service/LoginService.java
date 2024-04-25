package com.example.mongo_db_practice.service;

import com.example.mongo_db_practice.model.Admin;
import com.example.mongo_db_practice.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;


    public Optional<Admin> getByUsername(String username) {
        System.out.println("Username: " + username);
        Optional<Admin> admin = loginRepository.findByUsername(username);
        if(admin == null) {
            System.out.println("No admin found with this username");
        } else {
            System.out.println("Admin found: " + admin.get().getUsername());
        }
        return admin;
    }
//    public String getByPassword(String password) {
//        return loginRepo.findByPassword(password).getPassword();
//    }
}
