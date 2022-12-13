package com.binder.server.repository;

import com.binder.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findUserByUsername(String username);
}


//public interface UserRepositoryCustom
//    public void customMethod();
//    public class findUserByUsername implements UserRepositoryCustom
//    Optional<User> findUserByUsername(String username);