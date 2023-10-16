package com.example.identitymanagment.service;

import com.example.identitymanagment.configuration.security.JwtFactory;
import com.example.identitymanagment.entity.User;
import com.example.identitymanagment.entity.dto.UserLoginResponse;
import com.example.identitymanagment.entity.dto.UserRegisterResponse;
import com.example.identitymanagment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtFactory jwtFactory;


    public void saveUser(UserRegisterResponse registerResponse){
        registerResponse.setPassword(passwordEncoder.encode(registerResponse.getPassword()));
        User user = registerResponse.registerUserDto(registerResponse);
        userRepository.save(user);
    }

    public String loginUser(UserLoginResponse loginResponse){
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
