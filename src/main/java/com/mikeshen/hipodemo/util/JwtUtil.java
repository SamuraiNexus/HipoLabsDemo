package com.mikeshen.hipodemo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;


@Component
public class JwtUtil {

    @Value("${jwt.secrete.key}")
    private String secreteKey;


    public String generateJwtToken(String username, String userType) {
        String token = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256,secreteKey)
                .claim("userType", userType)
                .compact();
        return token;
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secreteKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String getUserTypeFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secreteKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("userType").toString();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secreteKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Token not valid"+ token);
        }
    }
}
