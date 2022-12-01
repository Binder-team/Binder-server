package com.example.binderserver.repository;

import com.example.binderserver.model.UserTable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserTableRepository extends MongoRepository<UserTable, String> {

}
