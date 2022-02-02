package com.example.jwt.web;

import com.example.jwt.service.TokenService;
import com.example.jwt.service.dto.LoginDTO;
import com.example.jwt.web.docs.ViewResourceDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ViewResource implements ViewResourceDocs {

  private final TokenService tokenService;

  @PostMapping("/login")
  public void login(@Valid @RequestBody LoginDTO dto) {}

  @PostMapping("/logout/{userId}")
  public void logout(@PathVariable Long userId) {
    tokenService.removeToken(userId);
  }
}
