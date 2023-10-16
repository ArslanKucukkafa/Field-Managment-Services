package com.example.fieldcore.rest;

import com.example.fieldcore.service.FieldService;
import com.example.fieldcore.tkgm.entity.Properties;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/field")
public class FieldController {
    @Autowired
    FieldService fieldService;



    @GetMapping
    public void getField() {
        // TODO THIS METHOD NOT YET FOR IMPLEMENTATION
    }

    @GetMapping("/{userId}")
    public List<String> getFields(@PathVariable Long userId) {
        return List.of("field1", "field2");
    }

    @GetMapping("/fieldQueryResponse")
    public List<String> getFieldQueryResponse() {
        return List.of("field1", "field2");
    }

    @GetMapping("/fieldSampleData")
    public Properties getSapmpleData() throws JsonProcessingException {
        return fieldService.getFieldProps("41.027118162811576", "34.88223016262055");
    }
}