package com.example.jwt.utils;

import com.example.jwt.enums.ErrorType;
import com.example.jwt.service.dto.MessageDTO;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpUtils {

  public static void createErrorMessage(
      HttpStatus status, HttpServletResponse response, Exception ex, ErrorType errorType)
      throws IOException {
    MessageDTO.Error message = setMessage(ex, errorType);
    createMessage(status, response, message);
  }

  public static MessageDTO.Error setMessage(Exception ex, ErrorType errorType) {
    ex.printStackTrace();

    String detailMessage = ExceptionUtils.getStackTrace(ex);

    return MessageDTO.Error.builder()
        .code(errorType.getCode())
        .message(errorType.getMessage())
        .detailMessage(detailMessage)
        .build();
  }

  public static void createMessage(HttpStatus status, HttpServletResponse response, Object message)
      throws IOException {
    response.reset();

    response.setStatus(status.value());

    response.setContentType("application/json");

    response.getWriter().write(JsonUtils.convertObjectToJson(message));
  }
}
