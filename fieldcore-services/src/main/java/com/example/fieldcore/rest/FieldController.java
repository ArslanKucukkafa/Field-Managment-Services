package com.example.fieldcore.rest;

import com.example.fieldcore.entity.CriterResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/field")
public class FieldController {

    @GetMapping("/{lang}{lat}")
    public void getField(@PathVariable Long lang, @PathVariable Long lat){
        System.out.println("lang: "+lang+" lat: "+lat);
    }
    @GetMapping("/{userId}")
    public List<String>getFields(@PathVariable Long userId){
        return List.of("field1","field2");
    }

    @GetMapping("/fieldQueryResponse")
    public List<String>getFieldQueryResponse(@RequestBody CriterResponse criteries){
        return List.of("field1","field2");
    }

}
