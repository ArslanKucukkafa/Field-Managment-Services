package com.example.identitymanagment.repository;

import com.example.identitymanagment.entity.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PermissionRepository extends MongoRepository<Permission, String> {
    Optional<Permission> findById(String id);
}
