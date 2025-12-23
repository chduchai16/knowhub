package com.spring.knowhub.presentation.controllers.user;

import com.spring.knowhub.application.buses.CommandBus;
import com.spring.knowhub.application.buses.QueryBus;
import com.spring.knowhub.application.commands.user.role.CreateRoleCommand;
import com.spring.knowhub.application.commands.user.role.DeleteRoleCommand;
import com.spring.knowhub.application.commands.user.role.UpdateRoleCommand;
import com.spring.knowhub.application.queries.user.role.GetPagedRoleQuery;
import com.spring.knowhub.application.queries.user.role.GetRoleByIdQuery;
import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.presentation.requests.user.CreateRoleRequest;
import com.spring.knowhub.presentation.requests.user.UpdateRoleRequest;
import com.spring.knowhub.presentation.response.ApiResponse;
import com.spring.knowhub.presentation.response.PaginatedResponse;
import com.spring.knowhub.presentation.response.PaginationInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@Slf4j
public class RoleController {

    private final CommandBus commandBus ;
    private final QueryBus queryBus;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPagedRoles (
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size" , defaultValue = "10") int size,
            @RequestParam(value = "keyword" , required = false) String keyword
    ){
        log.info("GET /api/roles - page={}, size={}, keyword={}", page, size, keyword);
        Page<Role> rolePage = queryBus.execute(new GetPagedRoleQuery(
                page,
                size,
                keyword
        )) ;

        PaginationInfo paginationInfo = new PaginationInfo(
                rolePage.getTotalElements(),
                rolePage.getTotalPages(),
                rolePage.getNumber(),
                rolePage.getSize()
        ) ;

        PaginatedResponse<Role> paginatedResponse = new PaginatedResponse<>(
                rolePage.getContent(),
                paginationInfo
        ) ;

        return ResponseEntity.ok(new ApiResponse<>(
                "SUCCESS",
                "Lấy danh sách role phân trang thành công",
                paginatedResponse
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getRoleById(
            @PathVariable Long id
    ) {
        log.info("GET /api/roles/{}", id );
        GetRoleByIdQuery query = new GetRoleByIdQuery(id);
        Role role = queryBus.execute(query);
        return ResponseEntity.ok(new ApiResponse<>(
                "SUCCESS",
                "Lấy role thành công",
                role
        ));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createRole( @RequestBody CreateRoleRequest request) {
        log.info("POST /api/roles - roleName={}", request.getName());
        Long roleId = commandBus.execute(new CreateRoleCommand(
                request.getName(),
                request.getPermissionIds()
        ));

        return ResponseEntity.ok(new ApiResponse<>(
                "SUCCESS",
                "Tạo role thành công",
                roleId
        ));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<?>> updateRole(@RequestBody UpdateRoleRequest request) {
        log.info("PUT /api/roles - roleId={}", request.getId());
        Long roleId = commandBus.execute(new UpdateRoleCommand(
                request.getId(),
                request.getName(),
                request.getPermissionIds()
        )) ;
        return ResponseEntity.ok(new ApiResponse<>(
                "SUCCESS",
                "Cập nhật role thành công",
                roleId
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteRole(
            @RequestParam Long id
    ){
        log.info("DELETE /api/roles/{}" , id);
        commandBus.execute(new DeleteRoleCommand(
                id
        )) ;
        return ResponseEntity.ok(new ApiResponse<>(
                "SUCCESS",
                "Xóa role thành công",
                null
        ));
    }

}
