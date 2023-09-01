package com.example.fieldcore.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FieldModel {

    private String type;
    private Geometry geometry;
    private Properties properties;
}


class Geometry{
    private String type;
    private int [][] coordinates;
}

