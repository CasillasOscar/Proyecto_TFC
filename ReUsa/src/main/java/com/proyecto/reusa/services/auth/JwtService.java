package com.proyecto.reusa.services.auth;

import com.proyecto.reusa.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    public String extractUsername(final String token){
        final Claims jwtToken = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return jwtToken.getSubject();
    }

    public String generateToken(final Usuario user){return buildToken(user, jwtExpiration);}

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

    public boolean isTokenValid(final String token, final Usuario user){
        final String email = extractUsername(token);
        return (email.equals(user.getEmail())) && !isTokenExpirated(token);
    }

    private boolean isTokenExpirated(final String token){
        System.out.println(new Date());
        return extractLocalDateTimeExpiration(token).isBefore(LocalDateTime.now(ZoneId.of("UTC")));
    }

    private Date extractExpiration(final String token){
        final Claims jwtToken = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return jwtToken.getExpiration();
    }

    public LocalDateTime extractLocalDateTimeExpiration(final String token) {
        Date date = extractExpiration(token);
        if (date != null) {
            return date.toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
        }
        return null;
    }

    private SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
