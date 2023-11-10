package com.example.identitymanagment.rest;

import com.example.identitymanagment.configuration.SpringConfig;
import com.example.identitymanagment.entity.Permission;
import com.example.identitymanagment.entity.Role;
import com.example.identitymanagment.entity.dto.RoleRegisterDto;
import com.example.identitymanagment.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/idm/role")
public class RoleController {
    @Autowired
    SpringConfig springConfig;
    @Autowired
    RoleService roleService;

    @PostMapping("/addPermission/{roleId}/{permissionId}")
    public void addPermission(@PathVariable String roleId, @PathVariable String permissionId){
        roleService.addPermissionToRole(roleId,permissionId);
    }
    @PostMapping("/removePermission/{roleId}/{permissionId}")
    public void removePermission(@PathVariable String roleId ,@PathVariable String permissionId){
        roleService.removePermissionFromRole(roleId,permissionId);
    }
    @PostMapping("/create")
    public ResponseEntity<String> addRole(@RequestBody RoleRegisterDto roleRegisterDto){
        return roleService.addRole(roleRegisterDto);
    }
    @PostMapping("/removeRole/{roleId}")
    public void removeRole(@PathVariable String roleId){
       roleService.removeRole(roleId);
    }
    @GetMapping("/getRoles")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }
    @GetMapping("/get-permission")
    public List<Permission> getPermission(){
        return roleService.getPermission();}

    @GetMapping("/hello")
    public List<String> retrunHello(){
        List <String> list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            list.add("Hello");
        }
        return list;
    }
}
