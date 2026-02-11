package com.spring.knowhub.presentation.controllers.user;

import com.spring.knowhub.application.commands.user.permission.CreatePermissionCommand;
import com.spring.knowhub.application.commands.user.permission.DeletePermissionCommand;
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
import com.spring.knowhub.application.commands.user.permission.UpdatePermissionCommand;
import com.spring.knowhub.application.queries.user.permission.GetPagedPermissionsQuery;
import com.spring.knowhub.application.queries.user.permission.GetPermissionByIdQuery;
import com.spring.knowhub.domain.models.user.Permission;
import com.spring.knowhub.presentation.mappers.user.PermissionResponseMapper;
import com.spring.knowhub.presentation.requests.user.CreatePermissionRequest;
import com.spring.knowhub.presentation.requests.user.UpdatePermissionRequest;
import com.spring.knowhub.presentation.response.ApiResponse;
import com.spring.knowhub.presentation.response.PaginatedResponse;
import com.spring.knowhub.presentation.response.PaginationInfo;
import com.spring.knowhub.presentation.response.user.PermissionResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController {

        private final QueryBus queryBus;
        private final CommandBus commandBus;
        private final PermissionResponseMapper permissionResponseMapper;

        @GetMapping
        public ResponseEntity<ApiResponse<PaginatedResponse<PermissionResponse>>> getAllPermissions(
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int limit) {
                log.info("GET /api/permissions - page: {}, limit: {}", page, limit);

                Page<Permission> permissionPage = queryBus.execute(new GetPagedPermissionsQuery(page, limit));

                PaginatedResponse<PermissionResponse> paginatedResponse = new PaginatedResponse<>(
                                permissionPage.getContent().stream()
                                                .map(permissionResponseMapper::fromDomainToResponse)
                                                .toList(),
                                new PaginationInfo(
                                                permissionPage.getTotalElements(),
                                                permissionPage.getTotalPages(),
                                                permissionPage.getNumber(),
                                                permissionPage.getSize()));

                return ResponseEntity.ok(new ApiResponse<>(
                                "SUCCESS",
                                "Lấy danh sách quyền phân trang thành công",
                                paginatedResponse));
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<?>> getPermissionById(
                        @PathVariable Long id) {
                log.info("GET /api/permissions/{}", id);

                Permission permission = queryBus.execute(new GetPermissionByIdQuery(id));

                return ResponseEntity.ok(new ApiResponse<>(
                                "SUCCESS",
                                "Lấy quyền thành công",
                                permission));
        }

        @PostMapping
        public ResponseEntity<ApiResponse<?>> createPermission(
                        @RequestBody CreatePermissionRequest request) {
                log.info("POST /api/permissions - code={}", request.getCode());

                Long id = commandBus.execute(
                                new CreatePermissionCommand(request.getCode(), request.getDescription()));

                return ResponseEntity.ok(new ApiResponse<>(
                                "SUCCESS",
                                "Tạo quyền thành công",
                                id));
        }

        @PutMapping()
        public ResponseEntity<ApiResponse<?>> updatePermission(
                        @RequestBody UpdatePermissionRequest request) {
                log.info("PUT /api/permissions/{}", request.getId());

                Long updatedId = commandBus.execute(
                                new UpdatePermissionCommand(request.getId(), request.getCode(),
                                                request.getDescription()));

                return ResponseEntity.ok(new ApiResponse<>(
                                "SUCCESS",
                                "Cập nhật quyền thành công",
                                updatedId));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<?>> deletePermission(
                        @PathVariable Long id) {
                log.info("DELETE /api/permissions/{}", id);

                Long deletedId = commandBus.execute(
                                new DeletePermissionCommand(id));

                return ResponseEntity.ok(new ApiResponse<>(
                                "SUCCESS",
                                "Xóa quyền thành công",
                                deletedId));
        }
}
