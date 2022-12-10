package com.binder.server.controller;

import com.binder.server.model.User;
import com.binder.server.repository.AuthorizationRepository;
import com.binder.server.repository.UserRepository;
import com.binder.server.service.AuthorizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")

public class AuthorizationController {
    private final AuthorizationRepository authorizationRepository;
    private final UserRepository userRepository;
    final AuthorizationService authorizationService;
    public AuthorizationController (AuthorizationRepository repository,UserRepository userRepository, AuthorizationService authorizationService) {
        this.authorizationRepository = repository;
        this.userRepository = userRepository;
        this.authorizationService = authorizationService;
    }
    @PostMapping("/login")
    public ResponseEntity<Double> login(@Validated @RequestBody User userDetails) {
        User targetUser = userRepository.findUserByUsername(userDetails.getUsername());
        if (targetUser == null) {
            return ResponseEntity.badRequest().body(0.12);
        }
        if (targetUser.getId() == null) {
            return ResponseEntity.badRequest().body(0.10);
        }
        Long targetUserId = targetUser.getId();

        if ( targetUserId != null) {
            Double userToken = getToken(userDetails.getUsername());
            authorizationService.insertAuthID(targetUserId, userDetails.getUsername(), userToken);

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

