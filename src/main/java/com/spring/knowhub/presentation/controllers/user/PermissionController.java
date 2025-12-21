package com.spring.knowhub.presentation.controllers.user;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.knowhub.application.buses.CommandBus;
import com.spring.knowhub.application.buses.QueryBus;
import com.spring.knowhub.application.commands.user.permission.CreatePermissionCommand;
import com.spring.knowhub.application.commands.user.permission.UpdatePermissionCommand;
import com.spring.knowhub.application.queries.user.permission.GetAllPermissionQuery;
import com.spring.knowhub.application.queries.user.permission.GetPermissionByIdQuery;
import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.presentation.requests.user.CreatePermissionRequest;
import com.spring.knowhub.presentation.requests.user.UpdatePermissionRequest;
import com.spring.knowhub.presentation.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final QueryBus queryBus;
    private final CommandBus commandBus;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllPermissions() {
        log.info("Nhận yêu cầu lấy tất cả quyền");
        List<Permission> permissions = queryBus.execute(new GetAllPermissionQuery()) ;
        ApiResponse<List<Permission>> response = new ApiResponse<>(
            "SUCCESS",
            "Lấy tất cả quyền thành công",
            permissions
        );
        log.info("Trả về danh sách quyền với tổng số: {}", permissions.size());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getPermissionById(
            @PathVariable Long id
    ) {
        log.info("Nhận yêu cầu lấy quyền theo ID");
        Permission permission = queryBus.execute(new GetPermissionByIdQuery(id)) ;
        ApiResponse<Permission> response = new ApiResponse<>(
            "SUCCESS",
            "Lấy quyền thành công",
            permission
        );
        log.info("Trả về quyền với ID: {}", id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createPermission(
            @RequestBody CreatePermissionRequest request
    ) {
        CreatePermissionCommand command = new CreatePermissionCommand(
            request.getCode()
        );
        Long id = commandBus.execute( command ) ;
        ApiResponse<Long> response = new ApiResponse<>(
                "SUCCESS",
                "Tạo quyền thành công",
                id
        );
        return ResponseEntity.ok( response ) ;
    }

    @PutMapping
    public ResponseEntity<ApiResponse<?>> updatePermission(
            @RequestBody UpdatePermissionRequest request
    ) {
        Long id = commandBus.execute(new UpdatePermissionCommand(request.getId() , request.getCode())) ;
        ApiResponse<Long> response = new ApiResponse<>(
                "SUCCESS",
                "Cập nhật quyền thành công",
                id
        );
        return ResponseEntity.ok( response ) ;
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<?>> deletePermission(
            @PathVariable Long id
    ) {
        Long deletedId = commandBus.execute(new UpdatePermissionCommand(id , null)) ;
        ApiResponse<Long> response = new ApiResponse<>("SUCCESS" , "Xóa quyền thành công" , deletedId) ;
        return ResponseEntity.ok(response) ;
    }

}
