package com.binder.server.repository;

import com.binder.server.model.UserBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBooksRepository extends JpaRepository<UserBooks, Long> {

    List<UserBooks> findByUserId(Long userId);
    List<UserBooks> findByUserIdNot(Long userId);
}
