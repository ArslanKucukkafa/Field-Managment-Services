package com.example.identitymanagment.rest;

import com.example.identitymanagment.entity.Permission;
import com.example.identitymanagment.entity.Role;
import com.example.identitymanagment.entity.dto.RoleRegisterDto;
import com.example.identitymanagment.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping("/addPermission")
    public void addPermission(@RequestBody Permission permission, @RequestBody Role role){
        roleService.addPermission(role,permission);
    }
    @PostMapping("/removePermission")
    public void removePermission(@RequestBody Permission permission ,Role role){
        roleService.removePermission(role.getName(),permission);
    }
    @PostMapping("/create")
    public void addRole(@RequestBody RoleRegisterDto roleRegisterDto){
        roleService.addRole(roleRegisterDto);
    }
    @PostMapping("/removeRole")
    public void removeRole(@RequestBody Role role){
       roleService.removeRole(role.getName());
    }
    @GetMapping("/getRoles")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }

}
