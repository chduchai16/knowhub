package com.spring.knowhub.presentation.advices;

import com.spring.knowhub.application.exceptions.auth.AuthApplicationException;
import com.spring.knowhub.application.exceptions.post.post.PostApplicationException;
import com.spring.knowhub.application.exceptions.post.tag.TagApplicationException;
import com.spring.knowhub.application.exceptions.user.permission.PermissionApplicationException;
import com.spring.knowhub.application.exceptions.user.role.RoleApplicationException;
import com.spring.knowhub.application.exceptions.user.user.UserApplicationException;
import com.spring.knowhub.domain.exceptions.post.post.PostDomainException;
import com.spring.knowhub.domain.exceptions.post.tag.TagDomainException;
import com.spring.knowhub.domain.exceptions.user.role.RoleDomainException;
import com.spring.knowhub.domain.exceptions.user.user.UserDomainException;
import com.spring.knowhub.infrastructure.exceptions.post.post.PostInfrastructureException;
import com.spring.knowhub.infrastructure.exceptions.post.tag.TagInfrastructureException;
import com.spring.knowhub.infrastructure.exceptions.user.permission.PermissionInfrastructureException;
import com.spring.knowhub.infrastructure.exceptions.user.role.RoleInfrastructureException;
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
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ user tầng domain: " + ex.getMessage(), null));

    }

    @ExceptionHandler(UserInfrastructureException.class)
    public ResponseEntity<ApiResponse<?>> handleUserRepositoryException(UserRepositoryException ex){
        log.error("Lỗi repository user tầng hạ tầng: {} [{}]", ex.getMessage(), ex.getErrorCode(), ex.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ user tầng infrastructure: " + ex.getMessage(), null));
    }

    @ExceptionHandler(UserApplicationException.class)
    public ResponseEntity<ApiResponse<?>> handleUserApplicationException(UserApplicationException ex){
        log.error("Lỗi ứng dụng user tầng application: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ user tầng application: " + ex.getMessage(), null));
    }

    // permission
    @ExceptionHandler(PermissionDomainException.class)
    public ResponseEntity<ApiResponse<?>> handlePermissionDomainException(PermissionDomainException ex) {
        log.error("Lỗi nghiệp vụ permission tầng domain: {} [{}]", ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ permission tầng domain: " + ex.getMessage(), null));
    }

    @ExceptionHandler(PermissionInfrastructureException.class)
    public ResponseEntity<ApiResponse<?>> handlePermissionRepositoryException(PermissionRepositoryException ex){
        log.error("Lỗi repository permission tầng hạ tầng: {} [{}]", ex.getMessage(), ex.getErrorCode(), ex.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ permission tầng infrasstructure: " + ex.getMessage(), null));
    }

    @ExceptionHandler(PermissionApplicationException.class)
    public ResponseEntity<ApiResponse<?>> handlePermissionApplicationException(PermissionApplicationException ex){
        log.error("Lỗi permission tầng application: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ permission tầng application: " + ex.getMessage(), null));
    }

    // role
    @ExceptionHandler(RoleDomainException.class)
    public ResponseEntity<ApiResponse<?>> handleRoleDomainException(RoleDomainException ex) {
        log.error("Lỗi nghiệp vụ role tầng domain: {} [{}]", ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ role tầng domain: " + ex.getMessage(), null));
    }

    @ExceptionHandler(RoleInfrastructureException.class)
    public ResponseEntity<ApiResponse<?>> handleRoleInfrastructureException(RoleInfrastructureException ex){
        log.error("Lỗi repository role tầng hạ tầng: {} [{}]", ex.getMessage(), ex.getErrorCode(), ex.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ role tầng infrasstructure: " + ex.getMessage(), null));
    }

    @ExceptionHandler(RoleApplicationException.class)
    public ResponseEntity<ApiResponse<?>> handleRoleApplicationException(RoleApplicationException ex){
        log.error("Lỗi role tầng application: {} [{}]", ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ role tầng application: " + ex.getMessage(), null));
    }

    // auth
    @ExceptionHandler(AuthApplicationException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthApplicationException(AuthApplicationException ex){
        log.error("Lỗi authentication tầng application: {} [{}]", ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi xác thực: " + ex.getMessage(), null));
    }

    // tag
    @ExceptionHandler(TagDomainException.class)
    public ResponseEntity<ApiResponse<?>> handleTagDomainException(TagDomainException ex) {
        log.error("Lỗi nghiệp vụ tag tầng domain: {} [{}]", ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.badRequest()
                .body(
                        new ApiResponse<>(
                                ex.getErrorCode(),
                                "Lỗi nghiệp vụ tag tầng domain: " + ex.getMessage(),
                                null
                        )
                );
    }

    @ExceptionHandler(TagInfrastructureException.class)
    public ResponseEntity<ApiResponse<?>> handleTagInfrastructureException(TagInfrastructureException ex){
        log.error("Lỗi repository tag tầng hạ tầng: {} [{}]", ex.getMessage(), ex.getErrorCode(), ex.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ tag tầng infrastructure: " + ex.getMessage(), null));
    }

    @ExceptionHandler(TagApplicationException.class)
    public ResponseEntity<ApiResponse<?>> handleTagApplicationException(TagApplicationException ex){
        log.error("Lỗi tag tầng application: {} [{}]", ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ tag tầng application: " + ex.getMessage(), null));
    }

    // post
    @ExceptionHandler(PostDomainException.class)
    public ResponseEntity<ApiResponse<?>> handlePostDomainException(PostDomainException ex) {
        log.error("Lỗi nghiệp vụ post tầng domain: {} [{}]", ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ post tầng domain: " + ex.getMessage(), null));
    }

    @ExceptionHandler(PostInfrastructureException.class)
    public ResponseEntity<ApiResponse<?>> handlePostInfrastructureException(PostInfrastructureException ex){
        log.error("Lỗi repository post tầng hạ tầng: {} [{}]", ex.getMessage(), ex.getErrorCode(), ex.getCause());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ post tầng infrastructure: " + ex.getMessage(), null));
    }

    @ExceptionHandler(PostApplicationException.class)
    public ResponseEntity<ApiResponse<?>> handlePostApplicationException(PostApplicationException ex){
        log.error("Lỗi post tầng application: {} [{}]", ex.getMessage(), ex.getErrorCode());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(ex.getErrorCode(), "Lỗi nghiệp vụ post tầng application: " + ex.getMessage(), null));
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
