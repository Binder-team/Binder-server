package com.binder.server.controller;

import com.binder.server.exception.ResourceNotFoundException;
import com.binder.server.model.User;
import com.binder.server.model.Authorization;
import com.binder.server.repository.AuthorizationRepository;
import com.binder.server.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

//@RestController
//@RequestMapping("/api/")
//public class AuthorizationController {
//    private final AuthorizationRepository authorizationRepository;
//
//    @PostMapping("/login")
//    public ResponseEntity<Authorization>
//
//}

