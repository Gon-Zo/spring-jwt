package com.example.jwt.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class TokenDTO {

  @NotNull
  @ApiModelProperty(value = "유저 아이디")
  private Long userId;

  @NotNull
  @ApiModelProperty(value = "리플래쉬 토큰")
  private String refreshToken;
}
