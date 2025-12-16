package com.spring.knowhub.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Error Response - Alias cho ApiResponse khi lỗi
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String message;
}
