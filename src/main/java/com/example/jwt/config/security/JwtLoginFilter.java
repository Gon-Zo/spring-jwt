package com.example.jwt.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

  public JwtLoginFilter(
      String defaultFilterProcessesUrl,
      AuthenticationManager authenticationManager,
      AuthenticationSuccessHandler authenticationSuccessHandler,
      AuthenticationFailureHandler authenticationFailureHandler) {
    super(defaultFilterProcessesUrl);
    setAuthenticationManager(authenticationManager);
    setAuthenticationSuccessHandler(authenticationSuccessHandler);
    setAuthenticationFailureHandler(authenticationFailureHandler);
  }

  protected JwtLoginFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
    super(requiresAuthenticationRequestMatcher);
  }

  protected JwtLoginFilter(
      String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
    super(defaultFilterProcessesUrl, authenticationManager);
  }

  protected JwtLoginFilter(
      RequestMatcher requiresAuthenticationRequestMatcher,
      AuthenticationManager authenticationManager) {
    super(requiresAuthenticationRequestMatcher, authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {

    String httpMethod = request.getMethod();

    if (!HttpMethod.POST.name().equals(httpMethod)) {
      throw new NullPointerException();
    }

    Map<String, Object> requestBody = getRequestBodyToMap(request);

    String email = (String) requestBody.get("email");

    String password = (String) requestBody.get("password");

    UsernamePasswordAuthenticationToken authenticate =
        new UsernamePasswordAuthenticationToken(email, password);

    return this.getAuthenticationManager().authenticate(authenticate);
  }

  private Map<String, Object> getRequestBodyToMap(HttpServletRequest request) throws IOException {

    ObjectMapper objectMapper = new ObjectMapper();

    String requestBody =
        request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

    return objectMapper.readValue(requestBody, Map.class);
  }
}
