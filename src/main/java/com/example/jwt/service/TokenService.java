package com.example.jwt.service;

import com.example.jwt.config.security.DomainUser;
import com.example.jwt.config.security.GlobalException;
import com.example.jwt.domain.RefreshToken;
import com.example.jwt.domain.User;
import com.example.jwt.enums.ErrorType;
import com.example.jwt.repository.RefreshTokenRepository;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.service.dto.MessageDTO;
import com.example.jwt.service.dto.TokenDTO;
import com.example.jwt.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenService {

  @Value("${jwt.key}")
  private String jwtKey;

  private final RefreshTokenRepository refreshTokenRepository;

  private final UserRepository userRepository;

  @Transactional(rollbackFor = Exception.class)
  public MessageDTO.Login issueToken(TokenDTO dto) {

    Long userId = dto.getUserId();

    RefreshToken tokenData =
        refreshTokenRepository
            .findById(userId)
            .orElseThrow(() -> new GlobalException(ErrorType.EMPTY_TOKEN_DOMAIN));

    if (!tokenData.equals(dto.getRefreshToken())) {
      throw new GlobalException(ErrorType.NOT_MATCH_TOKEN);
    }

    User foundUser = userRepository.findById(dto.getUserId()).orElseThrow();

    DomainUser user = new DomainUser(foundUser);

    Long id = user.getId();

    String email = user.getUsername();

    String name = user.getName();

    String accessToken =
        JwtUtils.createAccessToken(jwtKey, id, email, name, user.getAuthoritiesToStringList());

    String refreshToken =
        JwtUtils.createRefreshToken(jwtKey, id, email, name, user.getAuthoritiesToStringList());

    tokenData.issueToken(refreshToken);

    return MessageDTO.Login.builder().accessToken(accessToken).refreshToken(refreshToken).build();
  }

  @Transactional(rollbackFor = Exception.class)
  public void removeToken(Long userId) {
    refreshTokenRepository.deleteById(userId);
  }
}
