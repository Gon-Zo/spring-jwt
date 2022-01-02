package com.example.jwt.config.security;

import com.example.jwt.domain.AuthenticationExceptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component("authenticationFailureHandler")
public class DomainAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        exception.printStackTrace();
        writePrintErrorResponse(response, exception);
    }

    private void writePrintErrorResponse(HttpServletResponse response, AuthenticationException exception) {
        try {

//            response.reset();

            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, Object> responseMap = new HashMap<>();

            String message = getExceptionMessage(exception);

//            responseMap.put("status", 401);
//
//            responseMap.put("message", message);
//
//            response.getOutputStream().println(objectMapper.writeValueAsString(responseMap));

//            response.ad

            response.sendRedirect("/error.html");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getExceptionMessage(AuthenticationException exception) {
        AuthenticationExceptions authenticationTypes = AuthenticationExceptions.findOf(exception.getClass().getSimpleName());
        return authenticationTypes.getValue();
    }

}
