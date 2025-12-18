package com.spring.knowhub.presentation.advices;

import com.spring.knowhub.application.exceptions.user.user.UserApplicationException;
import com.spring.knowhub.domain.exceptions.user.user.DuplicateUserException;
import com.spring.knowhub.domain.exceptions.user.user.InvalidUserException;
import com.spring.knowhub.domain.exceptions.user.user.UserDomainException;
import com.spring.knowhub.domain.exceptions.user.user.UserNotFoundException;
import com.spring.knowhub.infrastructure.exceptions.user.user.UserInfrastructureException;
import com.spring.knowhub.presentation.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidUser(InvalidUserException ex) {
        log.warn("Người dùng không hợp lệ: {} [{}]", ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(
                        ex.getErrorCode(),
                        ex.getMessage(),
                        null
                ));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleUserNotFound(UserNotFoundException ex) {
        log.warn("Không tìm thấy người dùng: {} [{}]", ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(
                        ex.getErrorCode(),
                        ex.getMessage(),
                        null
                ));
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<ApiResponse<?>> handleDuplicateUser(DuplicateUserException ex) {
        log.warn("Người dùng trùng lặp: {} [{}]", ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiResponse<>(
                        ex.getErrorCode(),
                        ex.getMessage(),
                        null
                ));
    }

    @ExceptionHandler(UserDomainException.class)
    public ResponseEntity<ApiResponse<?>> handleUserDomainException(UserDomainException ex) {
        log.warn("Lỗi miền nghiệp vụ: {} [{}]", ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(
                        ex.getErrorCode(),
                        ex.getMessage(),
                        null
                ));
    }

    @ExceptionHandler(UserApplicationException.class)
    public ResponseEntity<ApiResponse<?>> handleUserApplicationException(UserApplicationException ex) {
        log.warn("Lỗi ứng dụng: {} [{}]", ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(
                        ex.getErrorCode(),
                        ex.getMessage(),
                        null
                ));
    }

    @ExceptionHandler(UserInfrastructureException.class)
    public ResponseEntity<ApiResponse<?>> handleUserInfrastructureException(UserInfrastructureException ex) {
        log.error("Lỗi hạ tầng: {} [{}]", ex.getMessage(), ex.getErrorCode(), ex.getCause());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(
                        ex.getErrorCode(),
                        "Lỗi hệ thống, vui lòng thử lại sau",
                        null
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenericException(Exception ex) {
        log.error("Lỗi không mong muốn", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(
                        "LOI_NOI_BO",
                        "Đã xảy ra lỗi, vui lòng liên hệ quản trị viên",
                        null
                ));
    }
}
