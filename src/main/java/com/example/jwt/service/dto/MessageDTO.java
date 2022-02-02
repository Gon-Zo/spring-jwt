package com.example.jwt.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

public class MessageDTO {

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class Login {
    private String accessToken;
    private String refreshToken;

    @Builder
    public Login(String accessToken, String refreshToken) {
      this.accessToken = accessToken;
      this.refreshToken = refreshToken;
    }
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class Error {
    private String code;
    private String message;
    private String detailMessage;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Date date = new Date();

    @Builder
    public Error(String code, String message, String detailMessage) {
      this.code = code;
      this.message = message;
      this.detailMessage = detailMessage;
    }
  }
}
