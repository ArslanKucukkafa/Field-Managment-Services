package com.example.identitymanagment.service;

import com.example.identitymanagment.entity.Permission;
import com.example.identitymanagment.entity.Role;
import com.example.identitymanagment.entity.dto.RoleRegisterDto;
import com.example.identitymanagment.repository.PermissionRepository;
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
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;

    Logger LOGGER = Logger.getLogger(RoleService.class.getName());

    public void addPermissionToRole(String roleId, String permission){
        Optional<Role> role = roleRepository.findById(roleId);
        Optional<Permission> optionalPermission = permissionRepository.findById(permission);
        if(role.isPresent()){
            if (optionalPermission.isPresent()){
                role.get().getPermission().add(optionalPermission.get());
                roleRepository.save(role.get());
            }
//            permissions.forEach(permissionId -> {
//                Optional<Permission> permission = permissionRepository.findById(permissionId);
//                if(permission.isPresent()){
//                    role.get().getPermission().add(permission.get());
//                }else {
//                    LOGGER.warning("Permission not found with id : "+permissionId);
//                }
//            });
        } else {
            LOGGER.warning("Role not found with id : "+roleId);
        }
    }

    public void removePermissionFromRole(String roleId, String permissionId){
        Optional<Role> role = roleRepository.findById(roleId);
        Optional<Permission> optionalPermission = permissionRepository.findById(permissionId);
        if(role.isPresent()){
            if(optionalPermission.isPresent()){
                role.get().getPermission().remove(optionalPermission.get());
                roleRepository.save(role.get());
            }
            else {
                LOGGER.warning("Permission not found with id : "+permissionId);
            }
        }else {
            LOGGER.warning("Role not found with id : "+roleId);
        }
    }

    public List<Permission> getPermission(){
        return permissionRepository.findAll();
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

    public void removeRole(String roleId){
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent()){
            roleRepository.delete(role.get());
        }else {
            LOGGER.warning("Role not found with id : "+roleId);
        }
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }
}
