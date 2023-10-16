package com.example.identitymanagment.service;

import com.example.identitymanagment.entity.Role;
import com.example.identitymanagment.entity.dto.RoleRegisterDto;
import com.example.identitymanagment.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;
    public void addPermission(){
        // TODO : implement this method
    }

    public void removePermission(){
        // TODO : implement this method
    }

    public void getPermission(){
        // TODO : implement this method
    }

    public void addRole(RoleRegisterDto roleRegisterDto){
        Role role = roleRegisterDto.roleRegisterDtoToRoleEntity(roleRegisterDto);
        roleRepository.save(role);    }

    public void removeRole(){
        // TODO : implement this method
    }

    public void getRole(){
        // TODO : implement this method
    }
}
