package com.example.identitymanagment.service;

import com.example.identitymanagment.configuration.security.JwtFactory;
import com.example.identitymanagment.entity.Role;
import com.example.identitymanagment.entity.User;
import com.example.identitymanagment.entity.dto.UserLoginDto;
import com.example.identitymanagment.entity.dto.UserRegisterDto;
import com.example.identitymanagment.repository.RoleRepository;
import com.example.identitymanagment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtFactory jwtFactory;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    public void saveUser(UserRegisterDto registerResponse){
        try {
            Optional<User> isConfirmed = userRepository.findByUsername(registerResponse.getUsername());
            if(isConfirmed.isPresent()){
                logger.error("User is already exist with username : "+registerResponse.getUsername());
            }else{
                Optional<Role> baseRole = roleRepository.findByName("BASE_ROLE");
                User user = registerResponse.registerUserDto(registerResponse);
                user.setRole(new HashSet<>());
                user.getRole().add(baseRole.get());
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepository.save(user);
            }

        }catch (Exception e){
            logger.error(e.toString());
            throw new  RuntimeException("User registration failed",e);
        }
    }

    public String loginUser(UserLoginDto loginResponse){
        Optional<User> isConfirmed = userRepository.findByUsername(loginResponse.getUsername());
        if(isConfirmed.isPresent() && isConfirmed.get().isEnabled() && passwordEncoder.matches(loginResponse.getPassword(),isConfirmed.get().getPassword())){
                try {
                    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginResponse.getUsername(),loginResponse.getPassword()));
                    String token = jwtFactory.generateToken(authentication);
                    return token;
                }catch (Exception e){
                    return e.toString();
                }
        }
        else {
            return "Kullanıcı bulunamadı";
        }
    }

}
