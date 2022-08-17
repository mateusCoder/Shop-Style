package com.mateus.services;

import com.mateus.entities.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenService {

    @Value("${security.jwt.expiration}")
    String expiration;

    @Value("${security.jwt.secret}")
    String secret;

    public String generateToken(Authentication authentication){

        Customer customerLogged = (Customer) authentication.getPrincipal();

        LocalDateTime actualDatePlusMinutes = LocalDateTime.now().plusMinutes(Long.parseLong(expiration));

        Date expirationDate = Date.from(actualDatePlusMinutes.atZone(ZoneId.systemDefault()).toInstant());
        Date now = new Date();

        return Jwts.builder()
                .setIssuer("API customers and address")
                .setSubject(customerLogged.getId().toString())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Long getIdFromToken(String token){
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.valueOf(body.getSubject());
    }

    public boolean isTokenValid(String token){
        try{
            Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
            return true;

        }catch(Exception ex){
            return false;
        }
    }
}
