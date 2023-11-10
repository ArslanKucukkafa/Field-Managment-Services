package com.example.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("idm-services", r -> r.path("/idm/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://idm-service"))
                .route("fieldcore-services", r -> r.path("/field/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://fieldcore-services"))
                .route("job-services", r -> r.path("/job/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://job-services"))
                .build();
    }
}
