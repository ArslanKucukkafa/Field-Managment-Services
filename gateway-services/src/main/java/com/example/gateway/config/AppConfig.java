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

import java.util.*;

@Configuration
public class AppConfig {

    private final Logger LOGGER = LoggerFactory.getLogger("EndpointsListener.class");
    private final List<Set> endpoints = new ArrayList<>();
    @Bean
    public RestTemplate template(){
        return new RestTemplate();
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


    public List<Set> scanEndpoints (){
        if (endpoints.isEmpty()){
            List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
            definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-services")).forEach(routeDefinition -> {
                var paths  = template().getForEntity(routeDefinition.getUri().toString()+"/"+routeDefinition.getId() + "/v3/api-docs", Map.class).getBody().get("paths");

                for (Map.Entry<String, Object> entry : ((LinkedHashMap<String, Object>) paths).entrySet()) {
                    Set<String> endpoint = new HashSet<>();
                    endpoint.add(entry.getKey());
                    if(entry.getKey().contains("/")){
                        endpoint.add(entry.getKey());
                    }
                    for (var s : ((LinkedHashMap<String, Object>) entry.getValue()).entrySet()) {
                        endpoint.add(s.getKey());
                    }
                    endpoints.add(endpoint);
                }

            });
                LOGGER.info("Endpoints scanned");
                return endpoints;
        }else {
            LOGGER.info("Endpoints already scanned");
            return endpoints;
        }
    }


}
