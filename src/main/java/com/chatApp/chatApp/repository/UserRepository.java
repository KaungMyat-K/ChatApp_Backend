package com.chatApp.chatApp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.chatApp.chatApp.model.Status;
import com.chatApp.chatApp.model.User;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    
    List<User> findAllByStatus(Status status);
}
