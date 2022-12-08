package com.binder.server.controller;

import com.binder.server.model.Authorization;
import com.binder.server.model.User;
import com.binder.server.repository.AuthorizationRepository;


//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
import com.binder.server.repository.UserRepository;
import com.binder.server.service.AuthorizationService;
import com.binder.server.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
@RestController
@RequestMapping("/api/")

public class AuthorizationController {
    private final AuthorizationRepository authorizationRepository;
    private final UserRepository userRepository;
    final AuthorizationService authorizationService;
    final UserService userService;
    public AuthorizationController (AuthorizationRepository repository,UserRepository userRepository, UserService userService, AuthorizationService authorizationService) {
        this.authorizationRepository = repository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.authorizationService = authorizationService;
    }
    // Get Username and Password
    // Check username and password against User Table
    // If exists, create new token
    // Token should be given a randomly generated token string
    // Go to Authorization table make new entry.
    // Entry should have userID, username and new token.
    // Send response with token.

    //, @RequestBody("password") String password
    @PostMapping("/login")
    public ResponseEntity<Double> login(@RequestBody String username) {
        User targetUser = userService.findUserByUsername(username);

        Long targetUserId = targetUser.getId();

        if ( targetUserId != null) {
            Double userToken = getToken(username);
            authorizationService.insertAuthID(targetUserId, username, userToken);

            return ResponseEntity.ok().body(userToken);
        }
        return ResponseEntity.badRequest().body(0.00);

    }

    private double getToken(String username) {
        double userToken = username.length() + Math.random();
        return userToken;
    }
}



//        String token = getJWTToken(username);
//        User user = new User();
//        user.setUser(username);
//        user.setToken(token);
//        return user;


//    private String getJWTToken(String username) {
//        String secretKey = "mySecretKey";
//        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//                .commaSeparatedStringToAuthorityList("ROLE_USER");
//
//        String token = Jwts
//                .builder()
//                .setId("softtekJWT")
//                .setSubject(username)
//                .claim("authorities",
//                        grantedAuthorities.stream()
//                                .map(GrantedAuthority::getAuthority)
//                                .collect(Collectors.toList()))
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 600000))
//                .signWith(SignatureAlgorithm.HS512,
//                        secretKey.getBytes()).compact();
//
//        return "Bearer " + token;
//    }

//    public ResponseEntity<Authorization>

