package com.tien4112004.auth_service.service;

import com.tien4112004.auth_service.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    @Value(value = "${jwt.secret}")
    private String jwtSecret;

    private String encodedSecret;

    @Value(value = "${jwt.expiration}")
    private long jwtExpirationInSecs;

    @PostConstruct
    private void init() {
        this.encodedSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }

    public String generateToken(User user) {
        Date now = new Date();
        var expiredTime = new Date(now.getTime() + jwtExpirationInSecs * 1000);

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(now)
                .setExpiration(expiredTime)
                .signWith(SignatureAlgorithm.HS512, encodedSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(Base64.getDecoder().decode(encodedSecret))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getDecoder().decode(encodedSecret))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
