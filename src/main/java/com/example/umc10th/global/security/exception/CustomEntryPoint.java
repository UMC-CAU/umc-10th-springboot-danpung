package com.example.umc10th.global.security.exception;

import com.example.umc10th.global.code.BaseErrorCode;
import com.example.umc10th.global.code.GeneralErrorCode;
import com.example.umc10th.global.security.util.SecurityResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        BaseErrorCode code = GeneralErrorCode.UNAUTHORIZED;
        SecurityResponseUtil.writeErrorResponse(response, code);
    }
}
