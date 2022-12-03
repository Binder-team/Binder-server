package com.example.binderserver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import com.example.binderserver.repository.UserTableRepository;
import com.example.binderserver.model.UserTable;
import com.example.binderserver.resource.UserTableRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/resource/")
public class UserTableController {
    @Autowired
    private UserTableRepository userTableRepository;
//    public UserTableController(UserTableRepository userTableRepository) {
//        this.userTableRepository = userTableRepository;
//    }
    @GetMapping("/UserTable")
    public ResponseEntity<List<UserTable>> getAllUsers() {

        return ResponseEntity.ok(this.userTableRepository.findAll());
    }

    @PostMapping("/UserTable")
    public ResponseEntity<UserTable>createUserTable(@RequestBody UserTableRequest userTableRequest ) {

        UserTable userTable = new UserTable();
        userTable.setUsername(userTableRequest.getUsername());
        userTable.setCity(userTableRequest.getCity());
        userTable.setPostal_code(userTableRequest.getPostal_code());
        userTable.setPhone_number(userTableRequest.getPhone_number());
        userTable.setReputation(userTableRequest.getReputation());
        userTable.setIs_banned(userTableRequest.getIs_banned());


       return ResponseEntity.status(201).body(this.userTableRepository.save(userTable));
    }

}

