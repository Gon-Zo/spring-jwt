package com.example.jwt.config.security;

import com.example.jwt.enums.ErrorType;

public class GlobalException extends RuntimeException {

  private ErrorType errorType;

  public GlobalException(ErrorType errorType) {
    this.errorType = errorType;
  }

  public ErrorType getErrorType() {
    return errorType;
  }
}
