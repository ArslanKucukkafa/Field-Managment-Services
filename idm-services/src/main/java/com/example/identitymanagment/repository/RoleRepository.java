package com.example.identitymanagment.repository;

import com.example.identitymanagment.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role,Integer> {
}
