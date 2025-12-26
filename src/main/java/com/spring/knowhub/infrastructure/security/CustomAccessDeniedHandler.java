package com.spring.knowhub.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.knowhub.presentation.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ApiResponse<?> apiResponse = new ApiResponse<>("FAIL", "Bạn không có quyền truy cập", null);

        try {
            response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
