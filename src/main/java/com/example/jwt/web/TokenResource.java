package com.example.jwt.web;

import com.example.jwt.service.TokenService;
import com.example.jwt.service.dto.MessageDTO;
import com.example.jwt.service.dto.TokenDTO;
import com.example.jwt.web.docs.TokenResourceDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class TokenResource implements TokenResourceDocs {

  private final TokenService tokenService;

  @PutMapping("")
  public MessageDTO.Login issueToken(@RequestBody TokenDTO dto) {
    return tokenService.issueToken(dto);
  }
}
