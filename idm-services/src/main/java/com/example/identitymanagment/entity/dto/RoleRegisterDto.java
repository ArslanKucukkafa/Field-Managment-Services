package com.example.identitymanagment.entity.dto;

import com.example.identitymanagment.entity.Permission;
import com.example.identitymanagment.entity.Role;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RoleRegisterDto {
    String name;

    public Role roleRegisterDtoToRoleEntity (RoleRegisterDto roleRegisterDto) {
        Role role = new Role();
        role.setName(roleRegisterDto.name);
        return role;
    }
}
