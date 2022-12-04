package com.example.binderserver.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.binderserver.repository.UserTableRepository;
import com.example.binderserver.resource.UserTableRequest;

import java.util.List;

@RestController
@RequestMapping("/user_table")

public class user_table {
    private final UserTableRepository userTableRepository;

    public user_table(UserTableRepository userTableRepository) {
        this.userTableRepository = userTableRepository;
    }

    //    @Autowired
//    private UserTableRepository userTableRepository;
//    public UserTableController(UserTableRepository userTableRepository) {
//        this.userTableRepository = userTableRepository;
//    }
    @GetMapping("/UserTable")
    public ResponseEntity<List<com.example.binderserver.model.user_table>> getAllUsers() {

        return ResponseEntity.ok(this.userTableRepository.findAll());
    }

    @PostMapping("/UserTable")
    public ResponseEntity<com.example.binderserver.model.user_table>createUserTable(@RequestBody UserTableRequest userTableRequest ) {

        com.example.binderserver.model.user_table usertable = new com.example.binderserver.model.user_table();
        usertable.setUsername(userTableRequest.getUsername());
        usertable.setCity(userTableRequest.getCity());
        usertable.setPostal_code(userTableRequest.getPostal_code());
        usertable.setPhone_number(userTableRequest.getPhone_number());
        usertable.setReputation(userTableRequest.getReputation());
        usertable.setIs_banned(userTableRequest.getIs_banned());


       return ResponseEntity.status(201).body(this.userTableRepository.save(usertable));
    }

}

