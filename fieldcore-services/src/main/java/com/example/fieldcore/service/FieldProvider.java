package com.example.fieldcore.service;

import com.example.fieldcore.entity.FieldModel;
import com.example.fieldcore.entity.Properties;
import com.example.fieldcore.entity.TokenModel;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.logging.Logger;

public interface FieldProvider {

    Logger logger = Logger.getLogger(FieldProvider.class.getName());

    default TokenModel getToken() {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "67e3846244fad760bf17b1811c95eedcea109449ebd780c22736d91f8ac5b230U2FsdGVkX1%2BCz457xDV8FrKn9EbMM48mLJuJy2Qp%2FHAcKt0UOZ1PwHCF3DAS4jv%2FR%2BHjpGTaWl6o0bUzyTzTjA%3D%3D";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set("host", "backbone.tuvimer.com");
        headers.set("referer", "https://tapusor.com/");
        headers.setContentLength(164);
        HttpEntity<String> entity = new HttpEntity<String>(uri, headers);
        TokenModel object = restTemplate.exchange("https://backbone.tuvimer.com/v1/micro/getMicroToken", HttpMethod.POST, entity, TokenModel.class).getBody();
        logger.info("Token Alındı");
        return object;
    }

    default Properties getFieldProperties(String lat, String lng) {
        RestTemplate restTemplate = new RestTemplate();
        String url="https://micro.tuvimer.com/tkgm/v1/getTKGMInfoCoordinate";
        String token = getToken().getToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization",token);
        headers.set("host","micro.tuvimer.com");
        headers.set("referer","https://tapusor.com/");
        HashMap<String,Object> map = new HashMap<>();
        map.put("lat",lat);
        map.put("lng",lng);
        HashMap<String,String>map1= new HashMap<>();
        map1.put("deviceID",null);
        map1.put("deviceIP","");
        map1.put("i_user","");
        map1.put("i_userID","");
        map1.put("pid","1");
        map1.put("plid","3");
        map1.put("ver","web");
        map.put("base_params",map1);

        HttpEntity<HashMap> entity = new HttpEntity<>(map,headers);
        FieldModel model = restTemplate.exchange(url,HttpMethod.POST,entity, FieldModel.class).getBody();
        return model.getProperties();
    }
}
