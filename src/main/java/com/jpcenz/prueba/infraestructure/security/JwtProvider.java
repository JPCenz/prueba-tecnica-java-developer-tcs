package com.jpcenz.prueba.infraestructure.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Slf4j
@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + ( expiration * 1000L)))
                .signWith(getKey(secret))
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getKey(secret))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String getSubject(String token) {
        return Jwts.parser().setSigningKey(getKey(secret)).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(getKey(secret)).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.error(e.toString());
            return false;
        } catch (UnsupportedJwtException e) {
            log.error(e.toString());
            return false;
        }catch (MalformedJwtException e) {
            log.error(e.toString());
            return false;
        }catch (SignatureException e) {
            log.error(e.toString());
            return false;
        } catch (IllegalArgumentException e) {
            log.error(e.toString());
            return false;
        }
    }

    private Key getKey( String secret) {
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
