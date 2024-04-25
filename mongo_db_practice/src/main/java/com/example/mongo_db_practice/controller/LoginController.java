package com.example.mongo_db_practice.controller;

import com.example.mongo_db_practice.model.Admin;
import com.example.mongo_db_practice.repository.LoginRepository;
import com.example.mongo_db_practice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    LoginRepository loginRepository;
    @Autowired
    LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Admin admin){
        Optional<Admin> username = loginService.getByUsername(admin.getUsername());
        Map<String, String> response = new HashMap<>();
        if(admin.getUsername().equals(username.get().getUsername()) && admin.getPassword().equals(username.get().getPassword())){
            response.put("result", "Login Successful [ this message is from backend ]");
            return ResponseEntity.ok(response);
        } else if (admin.getUsername().equals("") || admin.getPassword().equals("")) {
            response.put("message", "Please Enter Your Username and Password");
        }
        response.put("message", "Login Failed [ from backend ]");
        return ResponseEntity.ok(response);
    }


}
