package com.proyecto.reusa.services.users.security;

import com.proyecto.reusa.models.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private Integer jwtExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private Integer refreshExpiration;

    public String generateToken(final Usuario user){
        return buildToken(user, jwtExpiration);
    }

    public String generateRefreshToken(final Usuario user){
        return buildToken(user, refreshExpiration);
    }

    private String buildToken(final Usuario user, final Integer expiration){
        return Jwts.builder()
                .id(user.getId().toString())
                .claims(Map.of("nombre", user.getNombre()))
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
