package com.example.identitymanagment.rest;

import com.example.identitymanagment.entity.dto.RoleRegisterDto;
import com.example.identitymanagment.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    public void addPermission(){
        // TODO : implement this method
    }
    public void removePermission(){
        // TODO : implement this method
    }

    @PostMapping("/create")
    public void addRole(@RequestBody RoleRegisterDto roleRegisterDto){
        roleService.addRole(roleRegisterDto);
    }

    public void removeRole(){
        // TODO : implement this method
    }

    public void getRole(){
        // TODO : implement this method
    }

}
