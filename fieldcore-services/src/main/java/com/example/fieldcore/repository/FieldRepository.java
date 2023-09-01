package com.example.fieldcore.repository;

import com.example.fieldcore.entity.Properties;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FieldRepository extends MongoRepository<Properties, Integer>{

}
