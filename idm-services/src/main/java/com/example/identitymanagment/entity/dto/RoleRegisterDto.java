package com.example.identitymanagment.entity.dto;

import com.example.identitymanagment.entity.Permission;
import com.example.identitymanagment.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RoleRegisterDto {
    @Schema(example = "BASE_ROLE",description = "The creating role will have null permission !! you should add according to your needs permission.")
    String name;

    public Role roleRegisterDtoToRoleEntity (RoleRegisterDto roleRegisterDto) {
        Role role = new Role();
        role.setName(roleRegisterDto.name);
        return role;
    }
}
