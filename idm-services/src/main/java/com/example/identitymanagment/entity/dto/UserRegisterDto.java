package com.example.identitymanagment.entity.dto;

import com.example.identitymanagment.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.Length;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserRegisterDto {

    @Size(min = 3)
    @Schema(example = "Tuncay")
    private String name;
    @Schema(example = "Deve")
    private String surname;
    @Schema(example = "@devetuncay19")
    private String username;
    @Size(min = 8)
    @Schema(example = "1arslan_docker_ps")
    private String password;

    public User registerUserDto(UserRegisterDto userRegisterResponse){
        User user = new User();
        user.setName(userRegisterResponse.getName());
        user.setSurname(userRegisterResponse.getSurname());
        user.setUsername(userRegisterResponse.getUsername());
        user.setPassword(userRegisterResponse.getPassword());
        user.setEnabled(true);
        return user;
    }
}
