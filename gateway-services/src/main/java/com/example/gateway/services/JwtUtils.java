package com.example.gateway.services;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtils {

    @Value("${app.jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean tokenValidate(String token){
        Boolean exprationStatus = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                .getBody().getExpiration().after(new Date(System.currentTimeMillis()));
        if (exprationStatus && getUsernameFromToken(token) != null) {
            return true;
        }
        return false;
    }

}
