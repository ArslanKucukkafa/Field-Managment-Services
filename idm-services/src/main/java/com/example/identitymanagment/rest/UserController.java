package com.example.identitymanagment.rest;

import com.example.identitymanagment.entity.dto.UserLoginResponse;
import com.example.identitymanagment.entity.dto.UserRegisterResponse;
import com.example.identitymanagment.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/idm")
@RestController
public class UserController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @PostMapping("/sign-up")
    public void signUp(@RequestBody UserRegisterResponse userRegisterResponse) {
        userDetailsService.saveUser(userRegisterResponse);
    }
    @PostMapping("/sign-in")
    public void signIn(@RequestBody UserLoginResponse userLoginResponse) {
        // TODO document why this method is empty
    }

    @PostMapping("/enable/{userId}")
    public void locked(@PathVariable int userId) {
        // TODO document why this method is empty
    }

}
