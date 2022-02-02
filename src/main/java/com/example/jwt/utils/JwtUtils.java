package com.example.jwt.utils;

import com.example.jwt.config.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class JwtUtils {

  public static String createAccessToken(
      String secretKey, Long id, String email, String name, List<String> authorities) {

    return createToken(
        secretKey,
        id,
        email,
        name,
        authorities,
        new Date(new Date().getTime() + Duration.ofMinutes(30).toMillis()));
  }

  public static String createRefreshToken(
      String secretKey, Long id, String email, String name, List<String> authorities) {

    return createToken(
        secretKey,
        id,
        email,
        name,
        authorities,
        new Date(new Date().getTime() + Duration.ofMinutes(60).toMillis()));
  }

  public static Claims parseJwtToken(String authorizationHeader, String jwtKey) {
    validationAuthorizationHeader(authorizationHeader);
    String passerHeader = extractToken(authorizationHeader);
    return Jwts.parser().setSigningKey(jwtKey).parseClaimsJws(passerHeader).getBody();
  }

  private static void validationAuthorizationHeader(String header) {
    if (null == header || "" == header || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
      throw new IllegalArgumentException();
    }
  }

  private static String extractToken(String header) {
    return header.substring(SecurityConstants.TOKEN_PREFIX.length());
  }

  private static String createToken(
      String secretKey,
      Long id,
      String email,
      String name,
      List<String> authorities,
      Date expiration) {
    Date now = new Date();
    return Jwts.builder()
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        .setIssuer("fresh")
        .setIssuedAt(now)
        .setExpiration(expiration)
        .claim("id", id)
        .claim("email", email)
        .claim("name", name)
        .claim("authorities", authorities)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }
}
