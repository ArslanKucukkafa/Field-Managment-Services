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
    public String signUp(@RequestBody UserRegisterResponse userRegisterResponse) {
        userDetailsService.saveUser(userRegisterResponse);
        return  "sign-up success";
    }
    @PostMapping("/sign-in")
    public String signIn(@RequestBody UserLoginResponse userLoginResponse) {
        return "sign-in success";
    }

    @PostMapping("/enable/{userId}")
    public String locked(@PathVariable int userId) {
        return "Locked Account";
    }

}
