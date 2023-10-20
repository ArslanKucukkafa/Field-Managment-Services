package com.example.identitymanagment.rest;

import com.example.identitymanagment.configuration.SpringConfig;
import com.example.identitymanagment.entity.Permission;
import com.example.identitymanagment.entity.Role;
import com.example.identitymanagment.entity.dto.RoleRegisterDto;
import com.example.identitymanagment.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    SpringConfig springConfig;
    @Autowired
    RoleService roleService;

    @PostMapping("/addPermission")
    public void addPermission(@RequestBody Permission permission, @RequestBody Role role){
        roleService.addPermissionToRole(role,permission);
    }
    @PostMapping("/removePermission")
    public void removePermission(@RequestBody Permission permission ,String roleName){
        roleService.removePermissionFromRole(roleName,permission);
    }
    @PostMapping("/create")
    public ResponseEntity<String> addRole(@RequestBody RoleRegisterDto roleRegisterDto){
        return roleService.addRole(roleRegisterDto);
    }
    @PostMapping("/removeRole")
    public void removeRole(@RequestBody Role role){
       roleService.removeRole(role.getName());
    }
    @GetMapping("/getRoles")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }
    @GetMapping("/get-permission")
    public List<String> getPermission(){
        return roleService.getPermission();}
}
