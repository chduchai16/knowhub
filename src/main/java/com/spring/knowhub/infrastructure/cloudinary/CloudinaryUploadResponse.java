package com.spring.knowhub.infrastructure.cloudinary;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CloudinaryUploadResponse {
    private String publicId;
    private String url;
    private String format;
    private Long bytes;
    private String resourceType;
}
