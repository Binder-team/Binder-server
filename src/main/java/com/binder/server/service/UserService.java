package com.binder.server.service;

import com.binder.server.model.User;
import com.binder.server.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User findUserByUsername(String username){
        User targetUser = userRepository.findUserByUsername(username);
        return targetUser;

    }


}
