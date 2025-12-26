package com.spring.knowhub.presentation.controllers.auth;

import com.spring.knowhub.application.buses.CommandBus;
import com.spring.knowhub.application.buses.QueryBus;
import com.spring.knowhub.application.commands.auth.LoginCommand;
import com.spring.knowhub.application.commands.auth.RegisterCommand;
import com.spring.knowhub.application.queries.user.user.GetUserByUsernameQuery;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.presentation.mappers.user.UserResponseMapper;
import com.spring.knowhub.presentation.requests.auth.LoginRequest;
import com.spring.knowhub.presentation.requests.auth.RegisterRequest;
import com.spring.knowhub.presentation.response.ApiResponse;
import com.spring.knowhub.presentation.response.user.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/auth")
public class AuthController {

    private final CommandBus commandBus ;
    private final QueryBus queryBus ;
    private final UserResponseMapper userResponseMapper ;

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

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<?>> profile(
            @AuthenticationPrincipal UserDetails userDetails
    ){
        log.info("GET /api/auth - profile");

        GetUserByUsernameQuery query = new GetUserByUsernameQuery(
                userDetails.getUsername()
        ) ;
        User user = queryBus.execute(query) ;
        UserResponse userResponse = userResponseMapper.fromUserToUserResponse(user) ;

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "SUCCESS",
                        "Lấy thông tin profile thành công",
                        userResponse
                )
        );
    }
}
