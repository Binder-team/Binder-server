package com.example.binderserver.repository;

import com.example.binderserver.model.user_table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

//@Configuration
@EnableJpaRepositories(basePackages = "com.example.binderserver.repository")
@Repository
public interface UserTableRepository extends JpaRepository<user_table, Long> {


}
