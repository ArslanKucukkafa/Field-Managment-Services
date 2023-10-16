package com.example.identitymanagment.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@Getter
@Setter
@Document(collection = "permissions")
public class Permission {
    String endpoint_url;
    String http_method;
}
