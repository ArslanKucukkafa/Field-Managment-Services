package com.example.identitymanagment.rest;

import com.example.identitymanagment.configuration.SpringConfig;
import com.example.identitymanagment.entity.dto.UserLoginResponse;
import com.example.identitymanagment.entity.dto.UserRegisterResponse;
import com.example.identitymanagment.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RequestMapping("/idm")
@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserServiceImpl userDetailsService;

    @Autowired
    SpringConfig springConfig;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserRegisterResponse userRegisterResponse) {
        userDetailsService.saveUser(userRegisterResponse);
        return  "sign-up success";
    }
    @PostMapping("/sign-in")
    public String signIn(@RequestBody UserLoginResponse userLoginResponse) {
        return userService.loginUser(userLoginResponse);
    }

    @PostMapping("/enable/{userId}")
    public String locked(@PathVariable int userId) {
        return "Locked Account";
    }

    // TODO : implement this method
    @GetMapping("/get-token")
    public String getToken(){return "token";}

    @GetMapping("/get-permission")
    public List<String> getPermission(){
        return springConfig.getScanedEndpoints();}

}
