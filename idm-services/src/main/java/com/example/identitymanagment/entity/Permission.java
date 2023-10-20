package com.example.identitymanagment.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@Data
@Getter
@Setter
@Document(collection = "permissions")
public class Permission {
    @Id
    String id;
    String endpoint_url;
    String http_method;
}
