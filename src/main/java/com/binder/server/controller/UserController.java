package com.binder.server.controller;

import com.binder.server.exception.ResourceNotFoundException;
import com.binder.server.model.User;
import com.binder.server.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("users")
    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("users/info")
    public ResponseEntity<Object> findUserByUsername(@Validated @RequestBody User userDetails) {
        User user = userRepository.findUserByUsername(userDetails.getUsername());
        user.setId(null);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping("users")
    public User createUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                           @Validated @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        user.setUsername(userDetails.getUsername());
        user.setCity(userDetails.getCity());
        user.setEmail(userDetails.getEmail());
        user.setPostal_code(userDetails.getPostal_code());
        user.setPhone_number(userDetails.getPhone_number());
        user.setReputation(userDetails.getReputation());
        user.setIs_banned(userDetails.getIs_banned());

        return ResponseEntity.ok(this.userRepository.save(user));
    }

    @DeleteMapping("users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        this.userRepository.delete(user);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
