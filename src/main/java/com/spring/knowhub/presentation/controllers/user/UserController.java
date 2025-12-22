package com.spring.knowhub.presentation.controllers.user;

import com.spring.knowhub.application.buses.CommandBus;
import com.spring.knowhub.application.buses.QueryBus;
import com.spring.knowhub.application.commands.user.user.CreateUserCommand;
import com.spring.knowhub.application.commands.user.user.DeleteUserCommand;
import com.spring.knowhub.application.commands.user.user.UpdateUserCommand;
import com.spring.knowhub.application.queries.user.user.GetUserByIdQuery;
import com.spring.knowhub.application.queries.user.user.GetUserByUsernameQuery;
import com.spring.knowhub.application.queries.user.user.GetUsersPagedQuery;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.presentation.requests.user.CreateUserRequest;
import com.spring.knowhub.presentation.requests.user.UpdateUserRequest;
import com.spring.knowhub.presentation.response.ApiResponse;
import com.spring.knowhub.presentation.response.PaginatedResponse;
import com.spring.knowhub.presentation.response.PaginationInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createUser(@RequestBody CreateUserRequest request) {
        log.info("POST /api/users - username={}", request.getUsername());

        Long userId = commandBus.execute(new CreateUserCommand(
                request.getUsername(),
                request.getEmail(),
                request.getPassword(),
                request.getFullName(),
                request.getBio(),
                request.getAvatarUrl()
        ));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        "SUCCESS",
                        "Tạo user thành công",
                        userId
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getUserById(@PathVariable Long id) {
        log.info("GET /api/users/{}", id);

        User user = queryBus.execute(new GetUserByIdQuery(id));

        return ResponseEntity.ok(new ApiResponse<>(
                "SUCCESS",
                "Lấy user thành công",
                user
        ));
    }

    @GetMapping("/search/username/{username}")
    public ResponseEntity<ApiResponse<?>> getUserByUsername(@PathVariable String username) {
        log.info("GET /api/users/search/username/{}", username);

        User user = queryBus.execute(new GetUserByUsernameQuery(username));

        return ResponseEntity.ok(new ApiResponse<>(
                "SUCCESS",
                "Lấy user thành công",
                user
        ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getUsers(Pageable pageable) {
        log.info(
                "GET /api/users?page={}&size={}",
                pageable.getPageNumber(),
                pageable.getPageSize()
        );

        Page<User> users = queryBus.execute(new GetUsersPagedQuery(pageable));

        PaginatedResponse<User> paginatedResponse = new PaginatedResponse<>(
                users.getContent(),
                new PaginationInfo(
                        users.getTotalElements(),
                        users.getTotalPages(),
                        users.getNumber(),
                        users.getSize()
                )
        );

        return ResponseEntity.ok(new ApiResponse<>(
                "SUCCESS",
                "Lấy danh sách user thành công",
                paginatedResponse
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request) {

        log.info("PUT /api/users/{}", id);

        Long userId = commandBus.execute(new UpdateUserCommand(
                id,
                request.getFullName(),
                request.getBio(),
                request.getAvatarUrl()
        ));

        return ResponseEntity.ok(new ApiResponse<>(
                "SUCCESS",
                "Cập nhật user thành công",
                userId
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteUser(@PathVariable Long id) {
        log.info("DELETE /api/users/{}", id);

        Long userId = commandBus.execute(new DeleteUserCommand(id));

        return ResponseEntity.ok(new ApiResponse<>(
                "SUCCESS",
                "Xóa user thành công",
                userId
        ));
    }
}


