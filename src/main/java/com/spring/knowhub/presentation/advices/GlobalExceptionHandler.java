package com.spring.knowhub.presentation.advices;

import com.spring.knowhub.application.exceptions.user.permission.PermissionApplicationException;
import com.spring.knowhub.application.exceptions.user.user.UserApplicationException;
import com.spring.knowhub.domain.exceptions.user.user.UserDomainException;
import com.spring.knowhub.infrastructure.exceptions.user.permission.PermissionInfrastructureException;
import com.spring.knowhub.infrastructure.exceptions.user.user.UserInfrastructureException;
import com.spring.knowhub.infrastructure.exceptions.user.user.UserRepositoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.knowhub.domain.exceptions.user.permission.PermissionDomainException;
import com.spring.knowhub.infrastructure.exceptions.user.permission.PermissionRepositoryException;
import com.spring.knowhub.presentation.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // user
    @ExceptionHandler(UserDomainException.class)
    public ResponseEntity<ApiResponse<?>> handleUserDomainException(UserDomainException ex) {
        log.error("Lỗi nghiệp vụ user tầng domain: {} [{}]", ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ tầng domain: " + ex.getMessage(), null));

    }

    @ExceptionHandler(UserInfrastructureException.class)
    public ResponseEntity<ApiResponse<?>> handleUserRepositoryException(UserRepositoryException ex){
        log.error("Lỗi repository user tầng hạ tầng: {} [{}]", ex.getMessage(), ex.getErrorCode(), ex.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ tầng infrastructure: " + ex.getMessage(), null));
    }

    @ExceptionHandler(UserApplicationException.class)
    public ResponseEntity<ApiResponse<?>> handleUserApplicationException(Exception ex){
        log.error("Lỗi ứng dụng user tầng application: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>("USER_APPLICATION_ERROR", "Lỗi nghiệp vụ tầng application: " + ex.getMessage(), null));
    }

    // permission
    @ExceptionHandler(PermissionDomainException.class)
    public ResponseEntity<ApiResponse<?>> handlePermissionDomainException(PermissionDomainException ex) {
        log.error("Lỗi nghiệp vụ permission tầng domain: {} [{}]", ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ tầng domain: " + ex.getMessage(), null));
    }

    @ExceptionHandler(PermissionInfrastructureException.class)
    public ResponseEntity<ApiResponse<?>> handlePermissionRepositoryException(PermissionRepositoryException ex){
        log.error("Lỗi repository permission tầng hạ tầng: {} [{}]", ex.getMessage(), ex.getErrorCode(), ex.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ tầng infrasstructure: " + ex.getMessage(), null));
    }

    @ExceptionHandler(PermissionApplicationException.class)
    public ResponseEntity<ApiResponse<?>> handlePermissionApplicationException(Exception ex){
        log.error("Lỗi permission tầng application: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>("PERMISSION_APPLICATION_ERROR", "Lỗi nghiệp vụ tầng application: " + ex.getMessage(), null));
    }

    // chung
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleUnknown(Exception ex) {
        log.error("Lỗi không xác định", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(
                        "INTERNAL_ERROR",
                        "Hệ thống gặp lỗi : " + ex.getMessage(),
                        null
                ));
    }

}
