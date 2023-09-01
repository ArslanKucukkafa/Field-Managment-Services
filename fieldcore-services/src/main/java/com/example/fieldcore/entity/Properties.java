package com.example.fieldcore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "properties")
public class Properties {
    @Id
    private int propertiesId;
    private String ilceAd;
    private String mevkii;
    private String parselNo;
    private String nitelik;
    private String mahalleAd;
    private String alan;
    private String adaNo;
    private String ilAd;
    private String pafta;
}
