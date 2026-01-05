package com.spring.knowhub.presentation.requests.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePermissionRequest {
    private Long id ;
    private String code ;
    private String description ;
}
