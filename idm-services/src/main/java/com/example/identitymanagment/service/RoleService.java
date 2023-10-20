package com.example.identitymanagment.service;

import com.example.identitymanagment.configuration.SpringConfig;
import com.example.identitymanagment.entity.Permission;
import com.example.identitymanagment.entity.Role;
import com.example.identitymanagment.entity.dto.RoleRegisterDto;
import com.example.identitymanagment.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class RoleService {
    @Autowired
    SpringConfig config;

    Logger LOGGER = Logger.getLogger(RoleService.class.getName());
    @Autowired
    RoleRepository roleRepository;
    public void addPermissionToRole(Role role, Permission permission){
       Optional<Role> optionalRolerole = roleRepository.findByName(role.getName());
       if(optionalRolerole.isPresent()){
           optionalRolerole.get().getPermission().add(permission);
            roleRepository.save(optionalRolerole.get());
       }else {
           LOGGER.warning("Role not found with name : "+role.getName()+"");
       }
    }

    public void removePermissionFromRole(String roleName, Permission permission){
        Optional<Role> role = roleRepository.findByName(roleName);
        if(role.isPresent()){
            role.get().getPermission().remove(permission);
            roleRepository.save(role.get());
        }else {
            LOGGER.warning("Role not found with name : "+roleName+"");
        }
    }

    public List<String> getPermission(){
        return config.getScanedEndpoints();
    }

    public ResponseEntity<String> addRole(RoleRegisterDto roleRegisterDto){
        Optional<Role> currentRole = roleRepository.findByName(roleRegisterDto.getName());
        if(currentRole.isPresent()){
            return new ResponseEntity<>("Role already exist with name : "+roleRegisterDto.getName(), HttpStatus.ALREADY_REPORTED);
        }else {
            Role role = roleRegisterDto.roleRegisterDtoToRoleEntity(roleRegisterDto);
            roleRepository.save(role);
            return new ResponseEntity<>("Role created with name : "+roleRegisterDto.getName(), HttpStatus.CREATED);
        }
  }

    public void removeRole(String roleName){
        Optional<Role> role = roleRepository.findByName("ROLE_ADMIN");
        if (role.isPresent()){
            roleRepository.delete(role.get());}
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }
}
