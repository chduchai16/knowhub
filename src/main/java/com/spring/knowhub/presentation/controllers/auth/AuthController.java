package com.spring.knowhub.presentation.controllers.auth;

import com.spring.knowhub.application.buses.CommandBus;
import com.spring.knowhub.application.commands.auth.LoginCommand;
import com.spring.knowhub.application.commands.auth.RegisterCommand;
import com.spring.knowhub.presentation.requests.auth.LoginRequest;
import com.spring.knowhub.presentation.requests.auth.RegisterRequest;
import com.spring.knowhub.presentation.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/auth")
public class AuthController {

    private final CommandBus commandBus ;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login (
            @RequestBody LoginRequest request
    ){
        log.info("POST /api/auth/login - username={}" , request.getUsername());
        String token = commandBus.execute(
                new LoginCommand(
                        request.getUsername(),
                        request.getPassword(),
                        request.getRememberMe()
                )
        );
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "SUCCESS",
                        "Đăng nhập thành công",
                        token
                )
        );
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(
            @RequestBody RegisterRequest request
    ){
        log.info("POST /api/auth/register - username={}" , request.getUsername());
        RegisterCommand command = new RegisterCommand(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        Long userId = commandBus.execute(command) ;
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "SUCCESS",
                        "Đăng ký thành công",
                        userId
                )
        );
    }
}
