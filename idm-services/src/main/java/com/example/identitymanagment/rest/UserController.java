package com.example.identitymanagment.rest;

import com.example.identitymanagment.configuration.security.JwtFactory;
import com.example.identitymanagment.entity.dto.UserLoginDto;
import com.example.identitymanagment.entity.dto.UserRegisterDto;
import com.example.identitymanagment.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/idm/auth")
@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserServiceImpl userDetailsService;

    @Autowired
    JwtFactory jwtTokenUtil;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserRegisterDto userRegisterResponse) {
        userDetailsService.saveUser(userRegisterResponse);
        return  "sign-up success";
    }
    @PostMapping("/sign-in")
    public String signIn(@RequestBody UserLoginDto userLoginResponse) {
        return userService.loginUser(userLoginResponse);
    }

    @PostMapping("/enable/{userId}")
    public String locked(@PathVariable int userId) {
        return "Locked Account";
    }

    // TODO : implement this method
    @GetMapping("/get-token")
    public String getToken(){return "token";}


}
