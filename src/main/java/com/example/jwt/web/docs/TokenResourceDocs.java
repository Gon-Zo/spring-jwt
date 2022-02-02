package com.example.jwt.web.docs;

import com.example.jwt.service.dto.MessageDTO;
import com.example.jwt.service.dto.TokenDTO;
import io.swagger.v3.oas.annotations.Operation;

public interface TokenResourceDocs {

  @Operation(
      summary = "토큰 재발급 API",
      description =
          "access 토큰이 시간초과시 API를 불러주세요. \n ex: \n {'userId' : 0 , 'refreshToken' : xxxx } ")
  MessageDTO.Login issueToken(TokenDTO dto);
}
