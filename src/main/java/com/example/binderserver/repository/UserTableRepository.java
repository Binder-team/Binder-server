package com.example.binderserver.repository;

import com.example.binderserver.model.UserTable;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

//@Configuration
@EnableJpaRepositories(basePackages = "com.example.binderserver.repository")
@Repository
public interface UserTableRepository extends JpaRepository<UserTable, Long> {


}
