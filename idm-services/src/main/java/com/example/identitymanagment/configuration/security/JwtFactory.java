package com.example.identitymanagment.configuration.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

@Service
public class JwtFactory implements Serializable {

    private final String secret;
    private final long tokenValidty;

    public JwtFactory(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.token.validity}") long tokenValidty) {
        this.secret = secret;
        this.tokenValidty = tokenValidty;
    }

    public String generateToken(Authentication authentication){
        var role = authentication.getAuthorities();
        return Jwts.builder().setSubject(authentication.getName()).setExpiration(
                        new Date(System.currentTimeMillis()+1000*tokenValidty))
                .setIssuer("Arslan19")
                .setHeaderParam("role",role.stream().findFirst().get().toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String getRoleNameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getHeader().get("role").toString();
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
