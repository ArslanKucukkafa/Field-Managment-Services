package com.example.identitymanagment.entity.dto;

import com.example.identitymanagment.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserLoginResponse {
    private String username;
    private String password;
}
