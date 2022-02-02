package com.example.jwt.config.security;

import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      FilterChain filterChain)
      throws ServletException, IOException {

    ContentCachingRequestWrapper request = new ContentCachingRequestWrapper(httpServletRequest);

    ContentCachingResponseWrapper response = new ContentCachingResponseWrapper(httpServletResponse);

    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader(
        "Access-Control-Allow-Headers",
        "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    response.setHeader("Access-Control-Allow-Credentials", "false");

    if (CorsUtils.isPreFlightRequest(request)) {
      return;
    }

    filterChain.doFilter(request, response);

    response.copyBodyToResponse();
  }
}
