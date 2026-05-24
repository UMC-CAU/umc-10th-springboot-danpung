package com.example.umc10th.global.security.handler;

import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.code.GeneralErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

public class OAuthFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(GeneralErrorCode.UNAUTHORIZED.getStatus().value());

        ApiResponse<String> responseBody = ApiResponse.onFailure(
                GeneralErrorCode.UNAUTHORIZED,
                exception.getMessage()
        );

        objectMapper.writeValue(response.getOutputStream(), responseBody);
    }
}
