package com.example.jwt.config.security;

import com.example.jwt.domain.RefreshToken;
import com.example.jwt.repository.RefreshTokenRepository;
import com.example.jwt.service.dto.MessageDTO;
import com.example.jwt.utils.HttpUtils;
import com.example.jwt.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component("authenticationSuccessHandler")
@RequiredArgsConstructor
public class DomainSuccessHandler implements AuthenticationSuccessHandler {

  @Value("${jwt.key}")
  private String jwtKey;

  private final RefreshTokenRepository refreshTokenRepository;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {

    DomainUser user = (DomainUser) authentication.getPrincipal();

    Long id = user.getId();

    String email = user.getUsername();

    String name = user.getName();

    List<String> authorities = user.getAuthoritiesToStringList();

    String accessToken = JwtUtils.createAccessToken(jwtKey, id, email, name, authorities);

    String refreshToken = JwtUtils.createRefreshToken(jwtKey, id, email, name, authorities);

    MessageDTO.Login loginMessage =
        MessageDTO.Login.builder().accessToken(accessToken).refreshToken(refreshToken).build();

    refreshTokenRepository.save(
        RefreshToken.builder().userId(id).refreshToken(refreshToken).build());

    HttpUtils.createMessage(HttpStatus.OK, response, loginMessage);
  }
}
