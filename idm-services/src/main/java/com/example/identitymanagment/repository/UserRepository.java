package com.example.identitymanagment.repository;

import com.example.identitymanagment.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User, Integer> {
    Optional<User> findByUserId(String username);
}
