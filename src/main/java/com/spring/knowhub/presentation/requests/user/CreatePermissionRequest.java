package com.spring.knowhub.presentation.requests.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePermissionRequest {
    private String code ;
    private String description ;
}
