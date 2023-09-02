package com.example.identitymanagment.entity.dto;

import com.example.identitymanagment.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserRegisterResponse {
    private String name;
    private String surname;
    private String username;
    private String password;

    public User registerUserDto(UserRegisterResponse userRegisterResponse){
        User user = new User();
        user.setName(userRegisterResponse.getName());
        user.setSurname(userRegisterResponse.getSurname());
        user.setUsername(userRegisterResponse.getUsername());
        user.setPassword(userRegisterResponse.getPassword());
        user.setEnabled(true);
        return user;
    }
}
