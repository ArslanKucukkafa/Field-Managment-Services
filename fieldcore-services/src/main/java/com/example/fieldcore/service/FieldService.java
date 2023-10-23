package com.example.fieldcore.service;

import com.example.fieldcore.repository.PropertiesRepository;
import com.example.fieldcore.tkgm.entity.Properties;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldService implements FieldProvider {
    @Autowired
    private PropertiesRepository propertiesRepository;
    private final Logger logger = LoggerFactory.getLogger(FieldService.class);

    public void saveField(String lat, String lng) {
        try {
            var field = getFieldProps(lat, lng);
            propertiesRepository.save(field);
        } catch (JsonProcessingException e) {
            logger.error("Error while saving field", e);
        }
    }


    public void updateField(Properties properties) {
        propertiesRepository.save(properties);
    }

    public void getFields1() {
        // TODO İmplement this method ----> get field list with owner id
    }

    public void getFields2(){
        // TODO İmplement this method ----> get field list with publish status
    }

    public void getFields3(){
        // TODO İmplement this method ----> get field list with criteria
    }
}
