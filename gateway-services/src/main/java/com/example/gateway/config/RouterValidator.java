package com.example.gateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
@Component
public class RouterValidator {



    public static final List<String> openEndpoints = List.of(
            "/idm/auth/sign-up",
            "/idm/auth/sign-in",
            "/field/getFields1",
            "/idm/role/get-permissions",
            "field-services",
            "job-services",
            "idm-services"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndpoints.stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
