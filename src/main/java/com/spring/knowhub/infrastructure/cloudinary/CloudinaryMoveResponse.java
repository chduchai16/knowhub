package com.spring.knowhub.infrastructure.cloudinary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CloudinaryMoveResponse {
    private String publicId;
    private String url;
}
