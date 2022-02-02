package com.example.jwt.web.docs;

import com.example.jwt.service.dto.LoginDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.v3.oas.annotations.Operation;

public interface ViewResourceDocs {

  @Operation(
      summary = "로그인 API",
      description = "ex = {\n" + "  \"email\": \"test1\",\n" + "  \"password\": \"admin\"\n" + "}")
  void login(LoginDTO dto);

  @Operation(summary = "로그아웃 API")
  @ApiImplicitParam(name = "userId" , value = "유저아이디")
  void logout(Long userId);
}
