package com.example.identitymanagment.service;

import com.example.identitymanagment.entity.User;
import com.example.identitymanagment.entity.dto.UserLoginResponse;
import com.example.identitymanagment.entity.dto.UserRegisterResponse;
import com.example.identitymanagment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    public void saveUser(UserRegisterResponse registerResponse){
        User user = registerResponse.registerUserDto(registerResponse);
        userRepository.save(user);
    }

    public void loginUser(UserLoginResponse loginResponse){
        // TODO document why this method is empty
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username).orElseThrow(()->new UsernameNotFoundException("User not found with user_id: \"+username"));
        return user;
    }
}
