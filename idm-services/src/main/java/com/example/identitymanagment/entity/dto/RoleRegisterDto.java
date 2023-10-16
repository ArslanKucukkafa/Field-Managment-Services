package com.example.identitymanagment.entity.dto;

import com.example.identitymanagment.entity.Permission;
import com.example.identitymanagment.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleRegisterDto {
    String name;
    List<Permission> permissions;

    public Role roleRegisterDtoToRoleEntity (RoleRegisterDto roleRegisterDto) {
        Role role = new Role();
        role.setName(roleRegisterDto.name);
        role.setPermission(roleRegisterDto.permissions);
        return role;
    }
}
