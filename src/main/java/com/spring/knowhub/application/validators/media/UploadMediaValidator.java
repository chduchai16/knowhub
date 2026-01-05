package com.spring.knowhub.application.validators.media;

import org.springframework.web.multipart.MultipartFile;

public class UploadMediaValidator {
    public static void validate(MultipartFile file, long maxSize) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File bị trống");
        }

        if (file.getSize() > maxSize) {
            throw new IllegalArgumentException("File quá lớn. Kích thước tối đa là " + maxSize + " bytes");
        }

        String contentType = file.getContentType();
        if (contentType == null ||
                (!contentType.startsWith("image/")
                        && !contentType.startsWith("video/"))) {
            throw new IllegalArgumentException("Định dạng file không hợp lệ. Chỉ chấp nhận hình ảnh và video.");
        }
    }
}
