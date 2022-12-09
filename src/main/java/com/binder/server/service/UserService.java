package com.binder.server.service;

import com.binder.server.model.User;
import com.binder.server.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User findUser(String username){
        User targetUser = userRepository.findByUsername(username);
        return targetUser;

    }


}
