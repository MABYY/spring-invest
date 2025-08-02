package com.investmentsportal.portal.services;

import com.investmentsportal.portal.entities.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${JWT_SECRET}")
    private String secret;

    @Value("${ACCESS_TOKEN_EXP}")
    private String tokenExpiration;

    public String generateToken(Users user) {
        return Jwts.builder()
                .subject(user.getId().toString()) // pass id
                .claim("email", user.getEmail()) // pass email
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Long.valueOf(tokenExpiration)  * 1000))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();

    }

    public boolean validateToken(String token) {
        try{
            var claims = getClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (JwtException ex) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        var claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims;
    }

    //    public String getEmailFromToken(String token){
//        return getClaims(token).getSubject();
//    }

    public Long getUserIdFromToken(String token){
        return Long.valueOf(getClaims(token).getSubject());
    };

}
