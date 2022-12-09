package com.binder.server.repository;

import com.binder.server.model.User;
//import com.binder.server.service.UserService;
//import com.binder.server.controller.AuthorizationController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

//import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {
    //User findByUsername(String username);
    User findByUsername(String username);


}


//public interface UserRepositoryCustom
//    public void customMethod();
//    public class findUserByUsername implements UserRepositoryCustom
//    Optional<User> findUserByUsername(String username);