package com.itudy.api.jwt;

import com.google.gson.JsonObject;
import com.itudy.api.exception.ExceptionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", ExceptionEnum.EXPIRED_TOKEN_EXCEPTION.getStatus().toString());
        jsonObject.addProperty("code", ExceptionEnum.EXPIRED_TOKEN_EXCEPTION.getCode());
        jsonObject.addProperty("message", ExceptionEnum.EXPIRED_TOKEN_EXCEPTION.getMessage());

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().print(jsonObject);

//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "expired");
    }
}