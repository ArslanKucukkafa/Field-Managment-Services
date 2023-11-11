package com.example.identitymanagment.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;
    private static final String[] LogRegisterControllerEndpoints = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/laboratories/save",
            "/api/v1/laboratories/login",
            "/idm/auth/sign-up",
            "/idm/auth/sign-in",
            "/idm/role/get-permission",
            "/idm/role/create",
            "/idm/role/getRoles",
            "/idm/role/addPermission/**",
            "/idm/role/create",
            "/idm/role/removePermission/**",
            "/idm/role/removeRole/**",
            "/idm/role/hello",
            "/actuator/**",
            "/actuator/health",
            "/actuator/info",
            "/actuator",
            "/idm-services/**",
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors((cors)-> cors.disable());
        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(LogRegisterControllerEndpoints).permitAll()
                );
//        http.sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
