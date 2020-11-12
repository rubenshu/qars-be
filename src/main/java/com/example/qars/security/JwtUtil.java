package com.example.qars.security;

import com.example.qars.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** Json Web Token Utility **/
@Service
public class JwtUtil {

    // Todo: find something that can generate a key
    private final String key = "vindietshiervoor";

    // Get username from token String
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Get expiration date time
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract claims with a generic function
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from a token string
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody();
    }

    // Check if token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Create token from user details
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(user);
    }

    // Create token with jwts
    private String createToken(User user) {
        // Use Jwts builder pattern to create token
        return Jwts.builder()
                // Set subject (owner of this token)
                .setSubject(user.getUsername())
                .claim("role", user.getRole())
                .claim("id", user.getId())
                // Set date time this token is generated
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // Token is valid current time + 10 hours
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                // Use a HS256 and our private key to generate the token
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    // Validates token by comparing issued at & user details
    public boolean validateToken(String token, User user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername())) && !isTokenExpired(token);
    }
}