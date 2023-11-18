package com.example.identitymanagment.configuration;

import com.example.identitymanagment.entity.Permission;
import com.example.identitymanagment.service.UserDetailsServiceImpl;
import com.example.identitymanagment.service.pulsar.PulsarConsumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
public class SpringConfig implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger LOGGER = LoggerFactory.getLogger("EndpointsListener.class");

    public Map<RequestMappingInfo, HandlerMethod> scanedEndpoints = null;

    List<Permission> endpoints = new ArrayList<>();

    @Autowired
    PulsarConsumer pulsarConsumer;


    @Override
    @Order(1)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext
                .getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        scanedEndpoints = requestMappingHandlerMapping.getHandlerMethods();
        for (RequestMappingInfo info:scanedEndpoints.keySet()) {
              {
                  if(info.getMethodsCondition().getMethods().stream().findFirst().isPresent()){
                      var a = info.getPathPatternsCondition().getPatterns().stream().findFirst().get().getPatternString();
                      var b = info.getMethodsCondition().getMethods().stream().findFirst().get().name();
                      endpoints.add(new Permission(a, b));
                  }
              }
        }
    }

    public List<Permission> getScanedEndpoints() {
        return endpoints;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public UserDetailsService userDetailsService() {return new UserDetailsServiceImpl();}
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
