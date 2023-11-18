package com.example.identitymanagment.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@Data
@Getter
@Setter
@Document(collection = "permissions")
@NoArgsConstructor
public class Permission {
    @Id
    String id;
    String endpoint_url;
    String http_method;

    public Permission(String endpoint_url, String http_method) {
        this.endpoint_url = endpoint_url;
        this.http_method = http_method;
    }

}
