package com.example.fieldcore.rest;

import com.example.fieldcore.entity.CoordinateModel;
import com.example.fieldcore.entity.CriterResponse;
import com.example.fieldcore.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/field")
public class FieldController {

    @Autowired
    FieldService fieldService;

    @PostMapping("/saveField")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveField(@RequestBody CoordinateModel coordinateModel){
        fieldService.saveField(coordinateModel);
        return "Field properties is saved";
    }
    @GetMapping("/{userId}")
    public List<String>getFields(@PathVariable Long userId){
        return List.of("field1","field2");
    }

    @GetMapping("/fieldQueryResponse")
    public List<String>getFieldQueryResponse(@RequestBody CriterResponse criteries){
        return List.of("field1","field2");
    }

    @PostMapping("/delete/{fieldId}")
    public void deleteField(@PathVariable Long fieldId){
        System.out.println("fieldId: "+fieldId);
    }
}
