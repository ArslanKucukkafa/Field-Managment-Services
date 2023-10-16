package com.example.fieldcore.service;

import com.example.fieldcore.entity.CoordinateModel;
import com.example.fieldcore.entity.CriterResponse;
import com.example.fieldcore.entity.Properties;
import com.example.fieldcore.repository.FieldRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FieldService implements FieldProvider{
    @Autowired
    private FieldRepository fieldRepository;
    private final Logger logger = LoggerFactory.getLogger(FieldService.class);

//    public ResponseEntity<Properties> saveField(CoordinateModel coordinateModel){
//        Properties properties = getFieldProperties(coordinateModel.getLat(),coordinateModel.getLng());
//        fieldRepository.save(properties);
//        return new ResponseEntity(HttpStatus.OK);
//    }

//    public List<Properties> listFields(){
//        List<Properties> properties = fieldRepository.findAll();
//        return properties;
//    }

    public String updateField(){
        return "Not supported yet";
    }

//    public ResponseEntity deleteField(int propertiesId){
//        Properties properties = fieldRepository.findById(propertiesId).orElseThrow();
//        fieldRepository.delete(properties);
//        return new ResponseEntity(HttpStatus.OK);
//    }

//    public ResponseEntity getFieldsWithQriterias(CriterResponse criteries){
//
//        return new ResponseEntity(HttpStatus.OK);
//    }
}
