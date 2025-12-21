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
        log.info("Nhận yêu cầu tạo user với username: {}", request.getUsername());

        CreateUserCommand command = new CreateUserCommand(
            request.getUsername(),
            request.getEmail(),
            request.getPassword(),
            request.getFullName(),
            request.getBio(),
            request.getAvatarUrl()
        );

        Long userId = commandBus.execute(command);

        log.info("Tạo user thành công với ID: {}", userId);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(new ApiResponse<>(
                "SUCCESS",
                "Tạo user thành công",
                userId
            ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getUserById(@PathVariable Long id) {
        log.info("Lấy thông tin user với ID: {}", id);
        User user = queryBus.execute(new GetUserByIdQuery(id));
        log.info("Lấy thông tin user thành công với ID: {}", id);
        return ResponseEntity.ok(new ApiResponse<>(
            "SUCCESS",
            "Lấy user thành công",
            user
        ));
    }

    @GetMapping("/search/username/{username}")
    public ResponseEntity<ApiResponse<?>> getUserByUsername(@PathVariable String username) {
        log.info("Lấy thông tin user với username: {}", username);

        User user = queryBus.execute(new GetUserByUsernameQuery(username));
        log.info("Lấy thông tin user thành công với username: {}", username);
        return ResponseEntity.ok(new ApiResponse<>(
            "SUCCESS",
            "Lấy user thành công",
            user
        ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getUsers(Pageable pageable) {
        log.info("Lấy danh sách user với page: {}, size: {}",
                pageable.getPageNumber(),
                pageable.getPageSize());

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
        log.info("Lấy danh sách user thành công, total: {}", users.getTotalElements());
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
        log.info("Nhận yêu cầu cập nhật user với ID: {}", id);

        UpdateUserCommand command = new UpdateUserCommand(
            id,
            request.getFullName(),
            request.getBio(),
            request.getAvatarUrl()
        );
        Long userId = commandBus.execute(command);
        log.info("Cập nhật user thành công với ID: {}", userId);
        return ResponseEntity.ok(new ApiResponse<>(
            "SUCCESS",
            "Cập nhật user thành công",
            userId
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteUser(@PathVariable Long id) {
        log.info("Nhận yêu cầu xóa user với ID: {}", id);
        Long userId = commandBus.execute(new DeleteUserCommand(id));
        log.info("Xóa user thành công với ID: {}", userId);
        return ResponseEntity.ok(new ApiResponse<>(
            "SUCCESS",
            "Xóa user thành công",
            userId
        ));
    }
}

