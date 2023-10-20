package com.example.identitymanagment.repository;

import com.example.identitymanagment.entity.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRepository extends MongoRepository<Permission, String> {
}
