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

    @GetMapping("/fieldSampleData")
    public Properties getSapmpleData() throws JsonProcessingException {
        return fieldService.getFieldProps("41.027118162811576", "34.88223016262055");
    }

    @PostMapping("/saveField/{lat}/{lng}")
    public void saveField(@PathVariable String lat, @PathVariable String lng) {
        fieldService.saveField(lat, lng);
    }

    @PostMapping("/updateField")
    public void updateField(@RequestBody Properties properties) {
        fieldService.updateField(properties);
    }

    @GetMapping("/getFields1")
    public String getFieldsWithOwner() {
        //TODO implement this method
        return "Hello Gateway Kazım";
    }

    @GetMapping("/getFields2")
    public List<Properties> getFieldsWithPublishStatus() {
        //TODO implement this method
        return null;
    }

    @GetMapping("/getFields3")
    public List<Properties> getFieldsWithCriteria() {
        //TODO implement this method
        return null;
    }
}