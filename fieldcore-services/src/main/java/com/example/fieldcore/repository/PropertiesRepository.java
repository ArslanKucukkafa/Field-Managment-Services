package com.example.fieldcore.repository;

import com.example.fieldcore.tkgm.entity.Properties;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropertiesRepository extends MongoRepository<Properties, Integer>{
}
