package com.example.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.*;

@Configuration
public class AppConfig {

    private final Logger LOGGER = LoggerFactory.getLogger("EndpointsListener.class");
    private final List<HashMap> endpoints = new ArrayList<>();
    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }


    @Autowired
    RouteDefinitionLocator locator;

    @Bean
    public List<GroupedOpenApi> apis() {
        List<GroupedOpenApi> groups = new ArrayList<>();
        List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
        assert definitions != null;
        definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-services")).forEach(routeDefinition -> {
            String name = routeDefinition.getId().replaceAll("-services", "");
            groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build());
        });
        return groups;
    }


    public List<HashMap> scanEndpoints (){
        if (endpoints.isEmpty()){
            List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
            definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-services")).forEach(routeDefinition -> {
                var paths  = template().getForEntity(routeDefinition.getUri().toString()+"/"+routeDefinition.getId() + "/v3/api-docs", Map.class).getBody().get("paths");

                for (Map.Entry<String, Object> entry : ((LinkedHashMap<String, Object>) paths).entrySet()) {
                    HashMap<String,String> endpoint = new HashMap<>();
                    if(entry.getKey().contains("/")){
                        endpoint.put("url",entry.getKey());
                    }
                    for (var s : ((LinkedHashMap<String, Object>) entry.getValue()).entrySet()) {
                        endpoint.put("http_type",s.getKey());
                    }
                    endpoints.add(endpoint);
                }

            });
                LOGGER.info("Endpoints scanned");
                return endpoints;
        } else {
            LOGGER.info("Endpoints already scanned");
            return endpoints;
        }
    }

}
