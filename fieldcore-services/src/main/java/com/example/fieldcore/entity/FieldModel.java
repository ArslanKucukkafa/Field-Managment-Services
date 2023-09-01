package com.example.fieldcore.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FieldModel {

    private String type;
    private Geometry geometry;
    private Properties properties;

    public String ffff (){
        return properties.getIlAd();
    }
}


class Geometry{
    private String type;
    private int [][] coordinates;
}

