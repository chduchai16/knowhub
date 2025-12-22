package com.spring.knowhub.presentation.controllers.user;

import com.spring.knowhub.application.buses.CommandBus;
import com.spring.knowhub.application.buses.QueryBus;
import com.spring.knowhub.application.commands.user.role.CreateRoleCommand;
import com.spring.knowhub.application.queries.user.role.GetRoleByIdQuery;
import com.spring.knowhub.domain.models.user.Role;
import com.spring.knowhub.presentation.requests.user.CreateRoleRequest;
import com.spring.knowhub.presentation.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@Slf4j
public class RoleController {

    private final CommandBus commandBus ;
    private final QueryBus queryBus;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getRoleById(
            @RequestParam Long id
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
    public ResponseEntity<ApiResponse<?>> createRole(CreateRoleRequest request) {
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

}
