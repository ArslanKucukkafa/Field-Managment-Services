package com.example.identitymanagment.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "roles")
public class Role {
    @Id
    private int id;
    private String name;
}
