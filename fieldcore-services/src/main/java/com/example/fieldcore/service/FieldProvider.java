package com.example.fieldcore.service;

import com.example.fieldcore.tkgm.entity.Properties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public interface FieldProvider {
    public static final Logger LOG = LoggerFactory.getLogger(FieldProvider.class);
    default Properties getFieldProps(String lat, String lng) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        String baseUrl="https://cbsapi.tkgm.gov.tr/megsiswebapi.v3/api/parsel/";
        String coordinateUrl = baseUrl+lat+"/"+lng+"/";
        var field = restTemplate.getForObject(coordinateUrl, String.class);
        JSONObject jsonObject = new JSONObject(field);
        JSONObject properties = jsonObject.getJSONObject("properties");
        Properties properties1 = new Properties(properties);
        return properties1;
    }

}
