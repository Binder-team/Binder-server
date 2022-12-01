package com.example.binderserver.controller;

import com.example.binderserver.model.UserTable;
import com.example.binderserver.repository.UserTableRepository;
import com.example.binderserver.resource.UserTableRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserTableController {

    private final UserTableRepository userTableRepository;

    public UserTableController(UserTableRepository userTableRepository) {
        this.userTableRepository = userTableRepository;
    }

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

